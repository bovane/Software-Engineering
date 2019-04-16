package com.bovane.adapterPattern;

public class FileLog implements ILog {
    public void write() {
        System.out.println("write FileLog by ILog");
    }
}
