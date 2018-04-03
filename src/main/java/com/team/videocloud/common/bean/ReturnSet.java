package com.team.videocloud.common.bean;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 方法返回结果集
 *
 * @author MingXiangjun
 * @create 2018-04-03 10:02
 */
public class ReturnSet extends HashMap{
    private static final String STATE_KEY = "state";
    private static final String STATE_SUCCESS = "success";
    private static final String STATE_FAIL = "fail";
    private static final String MSG_KEY = "msg";
    private static final String CODE_KEY = "code";

    public ReturnSet(){
    }

    public ReturnSet setSuccess(){
        put(STATE_KEY,STATE_SUCCESS);
        return this;
    }

    public ReturnSet setFail(){
        put(STATE_KEY,STATE_FAIL);
        return this;
    }

    public ReturnSet set(String key,Object value){
        put(key,value);
        return this;
    }
    public static ReturnSet success(){
        return new ReturnSet().setSuccess();
    }

    public static ReturnSet fail(){
        return new ReturnSet().setFail();
    }

    public static ReturnSet fail(String msg){
        return fail().set(MSG_KEY,msg);
    }

    public ReturnSet code(int code){
        set(CODE_KEY,code);
        return this;
    }

    public boolean isSuccess(){
        Object state = get(STATE_KEY);
        if (STATE_FAIL.equals(state)) {
            return false;
        }
        if (STATE_SUCCESS.equals(state)) {
            return true;
        }
        throw new IllegalStateException("调用 isFail() 之前，必须先调用 success()、fail() 或者 setSuccess()、setFail() 方法");
    }

    public boolean isFail(){
        Object state = get(STATE_KEY);
        if (STATE_FAIL.equals(state)) {
            return true;
        }
        if (STATE_SUCCESS.equals(state)) {
            return false;
        }
        throw new IllegalStateException("调用 isFail() 之前，必须先调用 success()、fail() 或者 setSuccess()、setFail() 方法");
    }
}
