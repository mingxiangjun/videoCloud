package com.team.videocloud.user.from;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class UserLoginForm {

  //  @Size(min = 2, max = 20, message = "名称不能少于2个字符并超过20个字")
/*    @NotNull(message = "用户名称不能为空")
    private String email;*/

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
