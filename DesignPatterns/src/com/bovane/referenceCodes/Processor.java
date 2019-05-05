package com.bovane.referenceCodes;
public abstract class Processor {
	protected Processor next;
	
	public abstract void process();
	
	public void setSuccessor(Processor obj) {
		this.next  = obj;
	}
	
}