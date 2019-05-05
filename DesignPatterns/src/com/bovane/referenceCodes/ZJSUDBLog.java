package com.bovane.referenceCodes;
public class ZJSUDBLog implements IZJSULog{
	public void write(String msg) {
		System.out.println("write to ZJSU db:" + msg);
	}
}