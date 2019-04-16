package com.bovane.decoratorPattern;

public class TestInputStream {
    public static void main(String[] args) {
        IInputStream iInputStream = new ByteArrayInputStream();
        LineNumberInputStream lineNumberInputStream = new LineNumberInputStream(
                new DataInputStream(new BufferedInputStream(iInputStream)));
        lineNumberInputStream.read("abcde");
    }
}
