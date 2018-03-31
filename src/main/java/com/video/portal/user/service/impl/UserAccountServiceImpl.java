package com.video.portal.user.service.impl;


import com.video.portal.user.dao.UserAccountDao;
import com.video.portal.user.po.UserAccountPo;
import com.video.portal.user.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
@Service
public class UserAccountServiceImpl  implements UserAccountService {

    @Inject
    private UserAccountDao userAccountDao;


    @Override
    public UserAccountPo findOne(Long userId) {
        return userAccountDao.findOne(userId);
    }

    @Override
    public UserAccountPo findByEmail(String email) {
        return userAccountDao.findByEmail(email);
    }

    @Override
    public UserAccountPo findByPhone(String mobilePhone) {
        return userAccountDao.findByPhone(mobilePhone);
    }

    @Override
    public UserAccountPo findByEmailOrPhone(String user) {
        return userAccountDao.findByEmailOrPhone(user,user);
    }


    @Override
    @Transactional
    public UserAccountPo create(UserAccountPo userPo) {
        userPo=  userAccountDao.save(userPo);
        return userPo;
    }

    @Override
    public UserAccountPo save(UserAccountPo userPo) {
        return userAccountDao.save(userPo);
    }
    @Override
    public void delete(Long id) {
        userAccountDao.delete(id);
    }

    @Override
    public UserAccountPo validateUser(String u, String password) {
        UserAccountPo user = userAccountDao.findByEmail(u);
        user=userAccountDao.findByEmailOrPhone(u,u);
        if(null!=user){
            String pwd = user.getPassword();
            if(password.equals(pwd)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserAccountPo> findAll() {
        return (List<UserAccountPo>) userAccountDao.findAll();
    }

    @Override
    public UserAccountPo lock(Long id) {
        return null;
    }

    @Override
    public UserAccountPo unlock(Long id) {
        return null;
    }


    private final Logger logger = LoggerFactory.getLogger(UserAccountService.class);

}
