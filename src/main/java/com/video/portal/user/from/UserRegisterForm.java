package com.video.portal.user.from;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class UserRegisterForm implements Serializable {

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "重复密码不能为空")
    private String rePassword;
    @NotNull(message = "性别不能为空")
    private String sex;
    @NotNull(message = "手机不能为空")
    @Pattern(regexp="^((\\+86)|(86))?(1)\\d{10}$", message = "手机格式不正确")
    private String mobilePhone;
    private String name;
/*    @NotNull(message = "手机验证码不能为空")
    private String validatePhone;
    @NotNull(message = "验证码不能为空")
    private String verifyCode;*/

    private String contactorEmail;
    private String contactorPhone;
    private String contactor;
    private String companyName;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactorEmail() {
        return contactorEmail;
    }

    public void setContactorEmail(String contactorEmail) {
        this.contactorEmail = contactorEmail;
    }

    public String getContactorPhone() {
        return contactorPhone;
    }

    public void setContactorPhone(String contactorPhone) {
        this.contactorPhone = contactorPhone;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
