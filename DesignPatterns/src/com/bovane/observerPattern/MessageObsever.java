package com.bovane.observerPattern;

public class MessageObsever implements IMalfunctionObserver {
    public void sendErrorState() {
        System.out.println("send error by message");
    }
}
