package com.bovane.referenceCodes;
public class ZJSUFileLog implements IZJSULog{
	public void write(String msg) {
		System.out.println("write to ZJSU file:" + msg);
	}
}