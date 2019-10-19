package com.bw.movie.bean;

/**
 * author: Xuexiandong
 * data: 2019/9/28 15:15:45
 * function：发送邮箱验证码
 */
public class BeanEmail {

    /**
     * message : 发送成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
