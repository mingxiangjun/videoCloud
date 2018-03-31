package com.team.videocloud.user.po;


import com.team.videocloud.common.util.UIDGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
@Entity
@Table(name = "video_user_account")
public class UserAccountPo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    //UUID
    @Column(name = "open_id",unique = true, nullable = false)
    protected String openId= UIDGenerator.getUUID();

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected Sex sex = Sex.MAN;

    @Column( unique = true, nullable = false)
    protected String phone;

    @Column(unique = true, nullable = false)
    protected String email;

    protected String name;

    //证件号码
    @Column(name = "id_card")
    protected String idCard;


    //帐号注册方式，默认自行注册 0：自行注册 1：管理创建
    @Column(name = "reg_mode", nullable = false)
    protected RegMode regMode = RegMode.SELF_REGISTER;

    //创建人ID
    @Column(name = "create_manager_id")
    protected Integer createManagerId;

    @Column(nullable = false)
    protected State status = State.NOT_ACTIVE;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    protected Date createTime = new Date();

    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    protected Date updateTime = new Date();





/*    private Integer userType;

    private Integer userSubject;

    private String[] userSubjects;

    private Integer userProductId;

    private String userImage;

    private Integer schoolId;

    private String schoolName;

    private Integer gradeId;

    private String gradeName;

    private Integer gradeIden;

    private Date userJoinClassDate;

    private Integer classId;

    private String className;

    private Date userLoginDate;

    private Date createDate;

    private Date updateDate;

    private String validateCode;

    private Integer validateType; //验证类型

    private Integer type; //

    private Integer artType;  //文理类型  0文   1理科  2无

    private String equipmentType;

    private Integer isBind;//是否绑定答题器*/



    public enum State {
        NOT_ACTIVE,//提交状态用户尚未激活
        ENABLE,//可用
        DISABLE//禁用
    }

    public enum Sex {
        MAN,
        WOMAN
    }

    public enum RegMode {
        SELF_REGISTER,//自行创建
        ADMIN_REGISTER//管理员创建
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public RegMode getRegMode() {
        return regMode;
    }

    public void setRegMode(RegMode regMode) {
        this.regMode = regMode;
    }

    public Integer getCreateManagerId() {
        return createManagerId;
    }

    public void setCreateManagerId(Integer createManagerId) {
        this.createManagerId = createManagerId;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
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


/*    //公司信息
    @OneToOne
   // @JoinTable(name = "user_enterprice_info")
    @JoinColumn(name = "user_enterprice_id", referencedColumnName = "id")
    protected UserEnterpricePo userEnterpricePo;*/
}
