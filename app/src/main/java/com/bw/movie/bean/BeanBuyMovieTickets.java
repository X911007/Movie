package com.bw.movie.bean;

/**
 * author: Xuexiandong
 * data: 2019/10/23 16:16:29
 * function：购票下单
 */
public class BeanBuyMovieTickets {

    /**
     * orderId : 20191023161008915
     * message : 下单成功
     * status : 0000
     */

    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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
