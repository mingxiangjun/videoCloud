package com.video.portal.user.validate;

import com.video.portal.user.from.UserLoginForm;
import com.video.portal.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class UserValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return User.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        // TODO Auto-generated method stub
        ValidationUtils.rejectIfEmpty(errors, "username", null, "Username is empty.");
        UserLoginForm user = (UserLoginForm) obj;
        if (null == user.getPassword() || "".equals(user.getPassword()))
            errors.rejectValue("password", null, "Password is empty.");
    }

    public static void validatePhone (HttpSession session){
        //验证手机格式

        //判断手机发信息请求是否过频繁 可做
        session.getAttribute("registerPhoneTime");

        //获取上次手机请求的时间，判断若在半小时之内  则 手机发送的验证码 可不更新
        Object phone = session.getAttribute("registerPhone");
        Integer mobile_code = null;
        if (phone == null || StringUtils.isBlank(phone.toString())) {
            //生成6位数字随机数  放入session中
            mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
            session.setAttribute("registerPhone", mobile_code);
        } else {
            mobile_code = Integer.parseInt(phone.toString());
        }
//        logger.debug("-------------mobilePhone--------" + form.getMobilePhone());
//        logger.debug("-------------registerPhone--------" + mobile_code);
        //TODO 给用户手机发送短信

        //记录当次的手机请求时间
        session.setAttribute("registerPhoneTime", DateUtils.nowTimeMillis());
    }

}
