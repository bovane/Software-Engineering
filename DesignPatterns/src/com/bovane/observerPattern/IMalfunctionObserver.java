package com.bovane.observerPattern;

/*观察者接口，申明错误通知方式*/
public interface IMalfunctionObserver {
    void sendErrorState();
}
