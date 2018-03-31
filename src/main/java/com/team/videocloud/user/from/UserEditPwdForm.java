package com.team.videocloud.user.from;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
@Getter
@Setter
@Accessors
public class UserEditPwdForm implements Serializable{

    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "原密码不能为空")
    private String oldPassword;
}
