package com.bovane.referenceCodes;
public class BLOrder{
	ILog log;
	public BLOrder(ILog log) {
		this.log = log;
	}
	
	public void save() {
		log.writeLog("save start");
		
		//
		
		log.writeLog("save end");
		
	}
}