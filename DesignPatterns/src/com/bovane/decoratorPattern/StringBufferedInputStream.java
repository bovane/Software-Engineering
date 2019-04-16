package com.bovane.decoratorPattern;

public class StringBufferedInputStream implements IInputStream {
    public void read(String fileName) {
        System.out.println("StringBufferedInputStream....");
    }
}
