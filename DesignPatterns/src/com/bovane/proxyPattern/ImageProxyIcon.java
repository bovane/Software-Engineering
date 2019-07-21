package com.bovane.proxyPattern;

import javax.swing.*;
import java.awt.*;
/*该文件为智能代理的代理主题类*/
public class ImageProxyIcon implements Icon {
    private ImageIcon imageIcon = new ImageIcon();
    private int count = 0;

    @Override   // 重写paintIcon方法,每调用一次便进行计数
    public void paintIcon(Component c, Graphics g, int x, int y) {
        imageIcon.paintIcon(c,g,x,y);
        this.count++;
    }

    @Override   // 重写方法，每调用一次便进行计数
    public int getIconWidth() {
        imageIcon.getIconWidth();
        this.count++;
        return 0;
    }

    @Override   // 重写方法，每调用一次便进行计数
    public int getIconHeight() {
        imageIcon.getIconHeight();
        this.count++;
        return 0;
    }

    public int getCount() { // 返回计数器
        return this.count;
    }
}
