package com.team.videocloud.user.from;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
@Setter
@Getter
@Accessors
public class ValidatePhoneForm implements Serializable {
    @NotNull(message = "手机不能为空")
    @Pattern(regexp="^((\\+86)|(86))?(13)\\d{9}$", message = "手机格式不正确")
    private String mobilePhone;
}
