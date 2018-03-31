package com.team.videocloud.user.service;

import com.team.videocloud.user.po.TeacherPo;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public interface TeacherService {
    /**
     * 查找用户
     * @param userId
     * @return
     */
    TeacherPo findOne(Long userId);
    /**
     * 查找用户
     * @param email
     * @return
     */
    TeacherPo findByEmail(String email);
    /**
     * 查找用户
     * @param mobilePhone
     * @return
     */
    TeacherPo findByPhone(String mobilePhone);
    /**
     * 查找用户
     * @param user
     * @return
     */
    TeacherPo findByEmailOrPhone(String user);

    /**
     * 新增/更新用户
     * @param userPo
     * @return
     */
    TeacherPo create(TeacherPo userPo);
    /**
     * 新增/更新用户
     * @param userPo
     * @return
     */
    TeacherPo save(TeacherPo userPo);
    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * 验证登录用户是否存在
     * @param email 登陆用户
     * @param password MD5加密过的密码
     * @return
     */
    TeacherPo validateUser(String email, String password);

    /**
     * 查询所有
     * @return
     */
    Iterable<TeacherPo> findAll();

    /**
     * 锁定用户
     * @param id
     * @return
     */
    TeacherPo lock(Long id);

    /**
     * 解锁用户
     * @param id
     * @return
     */
    TeacherPo unlock(Long id);

}
