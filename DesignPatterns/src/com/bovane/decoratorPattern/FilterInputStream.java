package com.bovane.decoratorPattern;

public class FilterInputStream implements IInputStream {
    IInputStream iInputStream;

    public FilterInputStream(IInputStream iInputStream) {
        this.iInputStream = iInputStream;
    }

    public void read(String fileName) {
        iInputStream.read(fileName);
        System.out.println("FilterInputStream....");
    }
}

