package com.video.portal.bean;

import java.io.Serializable;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class SessionItem implements Serializable {
    private static final long serialVersionUID = 7810814562708348742L;

    @Deprecated
    public SessionItem() {
    }


    /**
     *
     * @param userAccountId
     * @param userAccountName
     * @param userAccountEmail
     * @param userAccountPhone
     */

    public SessionItem(Long userAccountId, String userAccountName, String userAccountEmail, String userAccountPhone) {
        this.userAccountId = userAccountId;
        this.userAccountName = userAccountName;
        this.userAccountEmail = userAccountEmail;
        this.userAccountPhone = userAccountPhone;
    }


    private Long userAccountId;
    private String userAccountName;
    private String userAccountEmail;
    private String userAccountPhone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionItem that = (SessionItem) o;

        if (userAccountId != that.userAccountId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (userAccountId ^ (userAccountId >>> 32));
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    public String getUserAccountEmail() {
        return userAccountEmail;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }

    public String getUserAccountPhone() {
        return userAccountPhone;
    }

    public void setUserAccountPhone(String userAccountPhone) {
        this.userAccountPhone = userAccountPhone;
    }
}
