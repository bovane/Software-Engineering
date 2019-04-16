package com.bovane.adapterPattern;

public class DatabaseLog implements ILog {
    public void write() {
        System.out.println("write DatabaseLog by ILog");
    }
}
