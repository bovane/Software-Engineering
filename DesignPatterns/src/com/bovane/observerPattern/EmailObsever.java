package com.bovane.observerPattern;

/*具体的通知方式实现类，该类实现以邮件方式发送错误状态*/
public class EmailObsever implements IMalfunctionObserver {
    public void sendErrorState() {
        System.out.println("send error by email");
    }
}
