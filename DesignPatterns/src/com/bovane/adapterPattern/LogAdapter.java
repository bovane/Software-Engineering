package com.bovane.adapterPattern;

public class LogAdapter implements ILog {
    private ILogAdaptee iLogAdaptee;

    public LogAdapter(ILogAdaptee iLogAdaptee) { // 构造函数，传入ILogAdaptee
        this.iLogAdaptee = iLogAdaptee;
    }
    public void write() {
        iLogAdaptee.writeLog();
    }
}
