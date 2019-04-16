package com.bovane.adapterPattern;

public class DatabaseLog1 implements ILogAdaptee {
    public void writeLog() {
        System.out.println("write DatabaseLog by ILogAdaptee");
    }
}
