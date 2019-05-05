package com.bovane.strategyPattern;

/*该类为策略模式的环境类，负责当前algorithm运行*/
public class DataMining {
    public ICurrentAlgorithm currAlgorithm;

    public ICurrentAlgorithm getCurrAlgorithm() {
        return currAlgorithm;
    }

    public void setCurrAlgorithm(ICurrentAlgorithm currAlgorithm) {
        this.currAlgorithm = currAlgorithm;
    }
}
