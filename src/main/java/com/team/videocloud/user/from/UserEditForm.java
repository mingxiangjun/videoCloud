package com.team.videocloud.user.from;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class UserEditForm implements Serializable{

    @Size(min = 2, max = 20, message = "名称不能少于2个字符并超过20个字")
    @NotNull(message = "用户名称不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
