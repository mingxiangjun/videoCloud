package com.video.portal.user.dao;

import com.video.portal.user.po.UserAccountPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public interface UserAccountDao extends CrudRepository<UserAccountPo, Long> {

    /**
     * 通过emial查找用户
     * @param email 用户email
     * @return
     */
    UserAccountPo findByEmail(String email);
    /**
     * 通过emial查找用户
     * @param phone 用户手机
     * @return
     */
    UserAccountPo findByPhone(String phone);

    /**
     * 通过emial 或 手机 查找用户
     * @return
     */
    @Query("select l from  UserAccountPo l  where l.phone=?1 or l.email=?2 ")
    UserAccountPo findByEmailOrPhone(String phone, String email);
}
