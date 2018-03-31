package com.team.videocloud.user.from;

import javax.validation.constraints.NotEmpty;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class UserLoginForm {

    @NotEmpty(message = "用户名称不能为空")
    private String user;

    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
