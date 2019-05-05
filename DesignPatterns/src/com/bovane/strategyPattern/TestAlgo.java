package com.bovane.strategyPattern;

import java.util.Scanner;

/*该文件为策略模式测试类，可任意选择算法*/
public class TestAlgo {
    public static void main(String[] args) {
        DataMining dataMining = new DataMining();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入选择算法：1 - CHAID 2 - CAST 3 - QUEST");
        int algo = sc.nextInt();
        if (algo == 1)
            dataMining.setCurrAlgorithm(new Chaid());
        else if (algo == 2)
            dataMining.setCurrAlgorithm(new Cast());
        else if (algo == 3)
            dataMining.setCurrAlgorithm(new Quest());
        else
            System.out.println("Error!请输入选择算法：1 - CHAID 2 - CAST 3 - QUEST");
        // 调用算法
        dataMining.currAlgorithm.algorithm();
    }
}
