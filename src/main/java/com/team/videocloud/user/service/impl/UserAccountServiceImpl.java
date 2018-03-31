package com.team.videocloud.user.service.impl;


import com.team.videocloud.user.dao.UserAccountDao;
import com.team.videocloud.user.po.UserAccountPo;
import com.team.videocloud.user.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
@Service(value = "userAccountService")
public class UserAccountServiceImpl  implements UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;


    @Override
    public UserAccountPo findOne(Long userId) {
        return userAccountDao.getOne(userId);
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
        userAccountDao.deleteById(id);
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
}
