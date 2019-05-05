package com.bovane.referenceCodes;
public class LogAdapter implements ILog{
	private IZJSULog log;
	
	public LogAdapter(IZJSULog log) {
		this.log = log;
	}
	
	public void writeLog(String msg) {
		log.write(msg);
	}
	
}