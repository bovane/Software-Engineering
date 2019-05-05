package com.bovane.referenceCodes;
public class UpperInputStream extends FilterInputStream{
	public UpperInputStream(InputStream input) {
		super(input);
	}
	
	public void read() {
		super.read();
		// add new func
		
		System.out.println("add a upper func");
	}
}
