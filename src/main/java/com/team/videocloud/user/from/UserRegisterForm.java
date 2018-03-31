package com.team.videocloud.user.from;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
@Getter
@Setter
@Accessors(chain = true)
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
}
