package com.team.videocloud.user.from;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class ValidatePhoneForm implements Serializable {
    @NotNull(message = "手机不能为空")
    @Pattern(regexp="^((\\+86)|(86))?(13)\\d{9}$", message = "手机格式不正确")
    private String mobilePhone;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
