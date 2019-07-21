package com.bovane.proxyPattern;

/*该类为智能引用代理的测试类*/
public class ImageProxyIconTest {
    public static void main(String[] args) {
        ImageProxyIcon imageProxyIcon = new ImageProxyIcon();

        imageProxyIcon.getIconHeight();
        imageProxyIcon.getIconHeight();

        System.out.println("ImageIcon was call for " + imageProxyIcon.getCount() + " times.");
    }
}
