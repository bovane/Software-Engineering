package com.bovane.decoratorPattern;

public class FileInputStream implements IInputStream {
    public void read(String fileName) {
        System.out.println("File Input Stream....");
    }
}
