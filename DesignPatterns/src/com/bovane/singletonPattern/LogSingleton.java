package com.bovane.singletonPattern;

/*单例模式实现日志处理类*/
public class LogSingleton {

    private static LogSingleton logSingleton = null; // 提供私有的静态成员变量

    private LogSingleton() { // 提供私有的构造函数
    }

    public static LogSingleton getLogSingleton() { // 提供公有的静态工厂方法
        if(logSingleton == null)
            logSingleton = new LogSingleton();
        return logSingleton;
    }

    public void getLog() {
        System.out.println("get and print log info");
    }
    public void wirteLog() {
        System.out.println("save log info");
    }
}
