package com.bovane.simpleFactory;

/*此类为简单工厂方法的测试类
* 这里用简单工厂方法设计设计对音乐播放器进行设计*/
import java.util.Scanner;

public class PlayTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next(); // 用户选择播放器
        PlayerFactory pf = new PlayerFactory(); // 获取工厂实例
        IPlayer player = pf.playMedia(type); // 获取播放器实例
        player.play();

    }
}
