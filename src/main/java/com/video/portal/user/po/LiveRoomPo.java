package com.video.portal.user.po;

import com.video.portal.util.UIDGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: dingran
 * Date: 2018/3/29
 * Description:
 */
@Entity
@Table(name = "video_live_root")
public class LiveRoomPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //UUID
    @Column(unique = true, nullable = false)
    private String uuid= UIDGenerator.getUUID();


    @OneToOne
    // @JoinTable(name = "teacher_po")
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    protected TeacherPo teacherPo;

    private String name;

    private String url;//直播地址

    @Column(name = "start_time")
    private Date startTime;//直播开始时间

    @Column(name = "end_time")
    private Date endTime;//直播截止时间

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    private Date createTime = new Date();

    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public TeacherPo getTeacherPo() {
        return teacherPo;
    }

    public void setTeacherPo(TeacherPo teacherPo) {
        this.teacherPo = teacherPo;
    }
}
