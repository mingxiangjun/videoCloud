package com.video.portal.user.from;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class UserEditPwdForm implements Serializable{

    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "原密码不能为空")
    private String oldPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
