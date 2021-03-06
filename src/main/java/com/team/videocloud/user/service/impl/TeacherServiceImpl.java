package com.team.videocloud.user.service.impl;

import com.team.videocloud.user.dao.TeacherDao;
import com.team.videocloud.user.po.TeacherPo;
import com.team.videocloud.user.service.TeacherService;
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
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;


    @Override
    public TeacherPo findOne(Long userId) {
        return teacherDao.getOne(userId);
    }

    @Override
    public TeacherPo findByEmail(String email) {
        return teacherDao.findByEmail(email);
    }

    @Override
    public TeacherPo findByPhone(String mobilePhone) {
        return teacherDao.findByPhone(mobilePhone);
    }

    @Override
    public TeacherPo findByEmailOrPhone(String user) {
        return teacherDao.findByEmailOrPhone(user,user);
    }


    @Override
    @Transactional
    public TeacherPo create(TeacherPo userPo) {
        userPo=  teacherDao.save(userPo);
        return userPo;
    }

    @Override
    public TeacherPo save(TeacherPo userPo) {
        return teacherDao.save(userPo);
    }
    @Override
    public void delete(Long id) {
        teacherDao.deleteById(id);
    }

    @Override
    public TeacherPo validateUser(String u, String password) {
        TeacherPo teacher = teacherDao.findByEmail(u);
        teacher=teacherDao.findByEmailOrPhone(u,u);
        if(null!=teacher){
            String pwd = teacher.getPassword();
            if(password.equals(pwd)){
                return teacher;
            }
        }
        return null;
    }

    @Override
    public List<TeacherPo> findAll() {
        return (List<TeacherPo>) teacherDao.findAll();
    }

    @Override
    public TeacherPo lock(Long id) {
        return null;
    }

    @Override
    public TeacherPo unlock(Long id) {
        return null;
    }


    private final Logger logger = LoggerFactory.getLogger(UserAccountService.class);

}
