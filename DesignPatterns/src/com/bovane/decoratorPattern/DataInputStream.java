package com.bovane.decoratorPattern;

public class DataInputStream implements IInputStream {
    private IInputStream iInputStream;

    public DataInputStream(IInputStream iInputStream) {
        this.iInputStream = iInputStream;
    }

    public void read(String fileName) {
        iInputStream.read(fileName);
        System.out.println("DataInputStream....");
    }
    public void data() {
        System.out.println("implements data stream");
    }
}

