package com.team.videocloud.user.dao;

import com.team.videocloud.user.po.TeacherPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public interface TeacherDao extends JpaRepository<TeacherPo, Long> {

    /**
     * 通过emial查找用户
     * @param email 用户email
     * @return
     */
    TeacherPo findByEmail(String email);
    /**
     * 通过emial查找用户
     * @param phone 用户手机
     * @return
     */
    TeacherPo findByPhone(String phone);

    /**
     * 通过emial 或 手机 查找用户
     * @return
     */
    @Query("select l from  TeacherPo l  where l.phone=?1 or l.email=?2 ")
    TeacherPo findByEmailOrPhone(String phone, String email);

    @Override
    TeacherPo getOne(Long aLong);
}
