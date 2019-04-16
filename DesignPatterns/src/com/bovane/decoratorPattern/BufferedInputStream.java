package com.bovane.decoratorPattern;

public class BufferedInputStream implements IInputStream {
    private IInputStream iInputStream;
    public BufferedInputStream(IInputStream iInputStream) {
        this.iInputStream = iInputStream;
    }

    public void read(String fileName) {
        iInputStream.read(fileName);
        System.out.println("BufferedInputStream....");
    }
    public void buffered() {
        System.out.println("implements buffered stream");
    }
}

