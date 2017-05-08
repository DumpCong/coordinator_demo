package com.xu.workwork.event;

/**
 * 自定义事件  （可以理解为BroardCastReceiver  ）
 * Created by xu on 2017/4/17.
 */
public class MyEvent {

    private String message;

    /**
     * post事件时用构造方法传参
     *
     * @param message
     */
    public MyEvent(String message) {
        this.message = message;
    }

    /**
     * 收到事件以后调用自定义方法处理信息
     *
     * @param msg
     */
    public void setMessage(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }


}
