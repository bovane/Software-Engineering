package com.bovane.decoratorPattern;

public class LineNumberInputStream implements IInputStream {
    private IInputStream iInputStream;

    public LineNumberInputStream(IInputStream iInputStream) {
        this.iInputStream = iInputStream;
    }

    public void read(String fileName) {
        iInputStream.read(fileName);
        System.out.println("LineNumberInputStream....");
    }
    public void lineNumber() {
        System.out.println("implements LineNumber InputStream");
    }
}

