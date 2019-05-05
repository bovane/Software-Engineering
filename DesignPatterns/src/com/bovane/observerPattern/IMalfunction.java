package com.bovane.observerPattern;

import java.util.*;

/*该抽象类为被观察者类，其中实现自由添加观察者，申明错误通知方法*/
public abstract class IMalfunction {
    public ArrayList observers = new ArrayList();
    public void attach(Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observer){
        observers.remove(observer);
    }
    public abstract void notice(ArrayList observers);
}
