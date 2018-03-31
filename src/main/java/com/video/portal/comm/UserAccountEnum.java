package com.video.portal.comm;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class UserAccountEnum {
    private State state;
    private Sex sex;
    private RegMode regMode;
    public enum State {
        /**
         * 提交状态用户尚未激活
         */
        NOT_ACTIVE,
        /**
         * 可用
         */
        ENABLE,
        /**
         * 禁用
         */
        DISABLE
    }
    public enum Sex {
        /**
         * 男士
         */
        MAN,
        /**
         * 女士
         */
        WOMAN
    }
    public enum RegMode  {
        /**
         * 自行创建
         */
        SELF_REGISTER,
        /**
         * 管理员创建
         */
        ADMIN_REGISTER
    }
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public RegMode getRegMode() {
        return regMode;
    }

    public void setRegMode(RegMode regMode) {
        this.regMode = regMode;
    }
}
