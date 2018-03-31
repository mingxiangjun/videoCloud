package com.team.videocloud.user.controller;

import com.team.videocloud.common.CommonConstants;
import com.team.videocloud.common.bean.SessionItem;
import com.team.videocloud.common.util.FormHelper;
import com.team.videocloud.common.util.RandomValidateCode;
import com.team.videocloud.common.util.ResponseCode;
import com.team.videocloud.common.util.ResponseItem;
import com.team.videocloud.user.from.*;
import com.team.videocloud.user.po.UserAccountPo;
import com.team.videocloud.user.service.UserAccountService;
import com.team.videocloud.user.validate.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountController {

    private final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private FormHelper formHelper;
    private static final String LOGIN_URL = "/login.html";

/*    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserValidator());
    }*/

    /**
     * 用户登录
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseItem login(@Valid UserLoginForm form, BindingResult result, HttpSession session, HttpServletResponse res) throws Exception {
        ResponseItem ri = formHelper.check(result);
        if (!ri.getCode().contains("Success")) {
            return ri;
        }
        try {
            // TODO 判断随机验证码

            //判断用户是否存在
            UserAccountPo userAccountPo = userAccountService.findByEmailOrPhone(form.getUser());
            if (userAccountPo == null) {
                return ResponseItem.responseWithName(ri, ResponseCode.PARAMETER_INVALID.toString(), "name or password");
            }
            //判断密码是否正确
            if (!userAccountPo.getPassword().equals(form.getPassword())) {
                return ResponseItem.responseWithName(ri, ResponseCode.PARAMETER_INVALID.toString(), "name or password");
            }
            //TODO 判断用户状体
            //实例化SessionItem 用于需要保存的用户信息
            SessionItem sessionItem = new SessionItem(userAccountPo.getId(), userAccountPo.getName() ,userAccountPo.getEmail(), userAccountPo.getPhone());
            //将用户信息保存到session中
            session.setAttribute(CommonConstants.SESSION_ID, sessionItem);

            //更新用户登录时间
            // userAccountPo.setActiveTime(new Date());
          //  res.sendRedirect("/index.jsp");
        } catch (Exception e) {
            logger.error("userLogin exception:{}", e);
            return ResponseItem.responseWithName(ri, ResponseCode.SERVICE_ERROR.toString(), "userLogin exception");
        }
        return ri;
    }

    /**
     * 用户登录
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public boolean logout(@ModelAttribute(CommonConstants.SESSION_ID) SessionItem si, SessionStatus sessionStatus, HttpServletResponse res) throws Exception {
        // long useId = si.getUserAccountId();
        //记录日志
        //TODO
        sessionStatus.setComplete();
/*        ResponseItem ri = new ResponseItem();
        ri.addMessage("成功退出登录状态！");
        ri.setOk(true);*/
        //跳转到登录界面
        res.sendRedirect(LOGIN_URL);
        return true;
    }

    /**
     * 用户注册
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public ResponseItem register(@Valid UserRegisterForm form, BindingResult result, HttpSession session) throws Exception {

        ResponseItem ri = formHelper.check(result);
        if (!ri.getCode().contains("Success")) {
            return ri;
        }
        try {

            //判断邮箱、手机 是否已注册
            UserAccountPo userAccountPo = userAccountService.findByPhone(form.getMobilePhone());
            if (userAccountPo != null) {
                return ResponseItem.responseWithName(ri, ResponseCode.RESOURCE_INUSE.toString(), "mobilePhone");
            }
            userAccountPo = userAccountService.findByEmail(form.getEmail());
            if (userAccountPo != null) {
                // 判断该邮箱是否被激活
                if (userAccountPo.getStatus() == UserAccountPo.State.NOT_ACTIVE) {
                    return ResponseItem.responseWithName(ri, ResponseCode.PARAMETER_INVALID.toString(), "该邮箱已被注册，但尚未激活，请您到注册邮箱激活账户后使用");
                }
                return ResponseItem.responseWithName(ri, ResponseCode.PARAMETER_INVALID.toString(), "该邮箱已被注册");
            }

            //验证手机验证码是否正确
/*            Object p = session.getAttribute("registerPhone");
            if (p == null) {
                return ResponseItem.responseWithName(ri, ResponseCode.PARAMETER_INVALID.toString(), "手机验证码错误");
            }
            if (!form.getValidatePhone().equals(p.toString())) {
                return ResponseItem.responseWithName(ri, ResponseCode.PARAMETER_INVALID.toString(), "手机验证码错误");
            }*/
            //创建用户
            userAccountPo = new UserAccountPo();
            userAccountPo.setEmail(form.getEmail());
            userAccountPo.setName(form.getName());
            userAccountPo.setPhone(form.getMobilePhone());
            userAccountPo.setPassword(form.getPassword());
            userAccountPo.setRegMode(UserAccountPo.RegMode.SELF_REGISTER);
            //尚未激活
            userAccountPo.setStatus(UserAccountPo.State.NOT_ACTIVE);
            if (form.getSex().equals("woman")) {
                userAccountPo.setSex(UserAccountPo.Sex.WOMAN);
            } else {
                userAccountPo.setSex(UserAccountPo.Sex.MAN);
            }

            this.userAccountService.create(userAccountPo);
            // todo 生成特定的字符串用于邮箱激活验证
            //TODO 发送激活邮件
            return ResponseItem.responseWithName(ri, ResponseCode.SUCCESS.toString(), "您已经成功注册，邮件已发送到您的注册邮箱");

        } catch (Exception e) {
            logger.error("userRegister exception:{}", e);
            return ResponseItem.responseWithName(ri, ResponseCode.SERVICE_ERROR.toString(), "userRegister exception");

        }
        //  return ri;
    }

    /**
     * 用户注册 手机验证
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/validatePhone")
    @ResponseBody
    public ResponseItem validatePhone(@Valid ValidatePhoneForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseItem ri = formHelper.check(result);
        if (!ri.getCode().contains("Success")) {
            return ri;
        }
        try {
            HttpSession session = request.getSession(true);
            UserValidator.validatePhone(session);
            return ResponseItem.responseWithName(ri, ResponseCode.SUCCESS.toString(), "成功发送手机验证码");
        } catch (Exception e) {
            logger.error("validatePhone exception:{}", e);
            return ResponseItem.responseWithName(ri, ResponseCode.SERVICE_ERROR.toString(), "validatePhone exception");
        }
    }

    /**
     * 用户注册 邮箱验证
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/validateEmail")
    @ResponseBody
    public ModelAndView validateEmail() throws Exception {

        //验证邮箱协议
        //验证用户是否成功注册（即数据库已添加）
        //验证邮箱验证码
        //验证雇员的状态 已激活
        //当未激活时，设置雇员状态为已激活
        //修改激活时间
        //生成AK/SK 存库
        //设定跳转界面
        ModelAndView modelAndView = new ModelAndView("test/index");
        modelAndView.addObject("testBean", "test");
        return modelAndView;
    }

    /**
     * 用户详情
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public ResponseItem info(@ModelAttribute(CommonConstants.SESSION_ID) SessionItem si) throws Exception {
        ResponseItem ri = new ResponseItem();
        try {
            UserAccountPo userAccountPo = userAccountService.findOne(si.getUserAccountId());
            if (userAccountPo == null) {
                return ResponseItem.responseWithName(ri, ResponseCode.RESOURCE_NOTFOUND.toString(), "user");
            }
            //TODO
            Map<String, Object> data = new HashMap<>();
            data.put("name", userAccountPo.getName());
            data.put("status", userAccountPo.getStatus());
            data.put("createTime", userAccountPo.getCreateTime());
            data.put("updateTime", userAccountPo.getUpdateTime());
            data.put("idCard", userAccountPo.getIdCard());
            data.put("mobilePhone", userAccountPo.getPhone());
            data.put("regMode", userAccountPo.getRegMode());
            data.put("sex", userAccountPo.getSex());

            //获取用户信息
            ri.setData(data);
            return ri;
        } catch (Exception e) {
            logger.error("getUserInfo exception:{}", e);
            return ResponseItem.responseWithName(ri, ResponseCode.SERVICE_ERROR.toString(), "getUserInfo exception");

        }
    }

    /**
     * 用户修改基本信息
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public ResponseItem edit(@Valid UserEditForm form, BindingResult result, @ModelAttribute(CommonConstants.SESSION_ID) SessionItem si) throws Exception {
        ResponseItem ri = formHelper.check(result);
        if (!ri.getCode().contains("Success")) {
            return ri;
        }
        try {
            UserAccountPo userAccountPo = userAccountService.findOne(si.getUserAccountId());
            if (userAccountPo == null) {
                return ResponseItem.responseWithName(ri, ResponseCode.RESOURCE_NOTFOUND.toString(), "user");
            }
            //验证参数格式
            userAccountPo.setName(form.getName());
            userAccountService.save(userAccountPo);
            return ResponseItem.responseWithName(ri, ResponseCode.SUCCESS.toString());
        } catch (Exception e) {
            logger.error("editUserInfo exception:{}", e);
            return ResponseItem.responseWithName(ri, ResponseCode.SERVICE_ERROR.toString(), "editUserInfo exception");
        }
    }

    /**
     * 用户修改基本信息
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/editPwd")
    @ResponseBody
    public ResponseItem editPwd(@Valid UserEditPwdForm form, BindingResult result, @ModelAttribute(CommonConstants.SESSION_ID) SessionItem si) throws Exception {
        ResponseItem ri = formHelper.check(result);
        if (!ri.getCode().contains("Success")) {
            return ri;
        }
        try {
            UserAccountPo userAccountPo = userAccountService.findOne(si.getUserAccountId());
            if (userAccountPo == null) {
                return ResponseItem.responseWithName(ri, ResponseCode.RESOURCE_NOTFOUND.toString(), "user");
            }
            //验证参数格式
            if (!userAccountPo.getPassword().equals(form.getOldPassword())) {
                return ResponseItem.responseWithName(ri, ResponseCode.PARAMETER_INVALID.toString(), "oldPassword");
            }
            userAccountPo.setPassword(form.getPassword());
            userAccountService.save(userAccountPo);
            return ri;
        } catch (Exception e) {
            logger.error("editUserPwd exception:{}", e);
            return ResponseItem.responseWithName(ri, ResponseCode.SERVICE_ERROR.toString(), "editUserPwd exception");

        }
    }

    /**
     * 用户获取随机验证码
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/verifyCode")
    @ResponseBody
    public ResponseItem verifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        ResponseItem ri = new ResponseItem();
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCode randomValidateCode = new RandomValidateCode();
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            logger.error("verifyCode exception:{}", e);
            return ResponseItem.responseWithName(ri, ResponseCode.SERVICE_ERROR.toString(), "verifyCode exception");
        }
        return ri;
    }
    //TODO 密码找回
    //TODO 忘记手机或邮箱
    //TODO 修改密码
    //TODO 重发手机或邮箱验证


    /**
     * 用户主界面
     *
     * @return ResponseItem
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    @ResponseBody
/*    public boolean index( @ModelAttribute(CommonConstants.SESSION_ID) SessionItem si,HttpSession session, HttpServletResponse res) throws Exception {
        try {
            res.sendRedirect(LOGIN_URL);
            // TODO 判断随机验证码

        } catch (Exception e) {
            logger.error("userIndex exception:{}", e);
            return ResponseItem.responseWithName(new ResponseItem(), ResponseCode.SERVICE_ERROR.toString(), "userIndex exception");
        }
        return true;
    }*/
    public ModelAndView index(@ModelAttribute(CommonConstants.SESSION_ID) SessionItem si, HttpSession session, HttpServletResponse res) throws Exception {
        ModelAndView mav = new ModelAndView("index");
        try {
            // TODO 判断随机验证码
            mav.addObject("accountName", si.getUserAccountName());
            mav.addObject("accountId", si.getUserAccountId());
            mav.addObject("accountPhone", si.getUserAccountPhone());
            mav.addObject("accountEmail", si.getUserAccountEmail());
        } catch (Exception e) {
            logger.error("userIndex exception:{}", e);
            return ResponseItem.responseWithName(new ResponseItem(), ResponseCode.SERVICE_ERROR.toString(), "userIndex exception");
        }
        return mav;
    }

}
