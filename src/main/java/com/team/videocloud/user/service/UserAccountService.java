package com.team.videocloud.user.service;

import com.team.videocloud.user.po.UserAccountPo;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public interface UserAccountService {
    /**
     * 查找用户
     * @param userId
     * @return
     */
    UserAccountPo findOne(Long userId);
    /**
     * 查找用户
     * @param email
     * @return
     */
    UserAccountPo findByEmail(String email);
    /**
     * 查找用户
     * @param mobilePhone
     * @return
     */
    UserAccountPo findByPhone(String mobilePhone);
    /**
     * 查找用户
     * @param user
     * @return
     */
    UserAccountPo findByEmailOrPhone(String user);

    /**
     * 新增/更新用户
     * @param userPo
     * @return
     */
    UserAccountPo create(UserAccountPo userPo);
    /**
     * 新增/更新用户
     * @param userPo
     * @return
     */
    UserAccountPo save(UserAccountPo userPo);
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
    UserAccountPo validateUser(String email, String password);

    /**
     * 查询所有
     * @return
     */
    Iterable<UserAccountPo> findAll();

    /**
     * 锁定用户
     * @param id
     * @return
     */
    UserAccountPo lock(Long id);

    /**
     * 解锁用户
     * @param id
     * @return
     */
    UserAccountPo unlock(Long id);

}
