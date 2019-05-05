package com.bovane.referenceCodes;
public class DBLog implements ILog{
	public void writeLog(String msg) {
		System.out.println("write to DB:" + msg);
	}
}