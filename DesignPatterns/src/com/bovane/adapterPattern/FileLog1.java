package com.bovane.adapterPattern;

public class FileLog1 implements ILogAdaptee {
    public void writeLog() {
        System.out.println("write File by ILogAdaptee");
    }
}
