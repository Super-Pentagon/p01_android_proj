package com.pentagon.p01_android_proj.login.forget;

/**
 * @author: Create by huangyuanhao on 2020-07-04
 * @email: 814484626@qq.com
 */
public class ForgetResponse {


    /**
     * success : true
     * code : 20000
     * message : 成功
     * data : {}
     */

    private boolean success;
    private int code;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
