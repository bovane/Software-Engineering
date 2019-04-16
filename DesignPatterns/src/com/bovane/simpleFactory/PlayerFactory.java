package com.bovane.simpleFactory;

/*简单工厂方法的工厂类，实现不同的播放器实例化*/
public class PlayerFactory {

    public static IPlayer playMedia(String type) {
        if(type.equalsIgnoreCase("WindowMediaPlayer"))
            return new WindowMediaPlayer();
        else if(type.equalsIgnoreCase("RealPlayer"))
            return new RealPlayer();
        else
            return new QuickTime();
    }
}
