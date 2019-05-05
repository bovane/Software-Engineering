package com.bovane.referenceCodes;
public class FileLog implements ILog{
	public void writeLog(String msg) {
		System.out.println("write to file:" + msg);
	}
}