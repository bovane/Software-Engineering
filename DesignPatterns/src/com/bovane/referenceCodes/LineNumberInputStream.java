package com.bovane.referenceCodes;
public class LineNumberInputStream extends FilterInputStream{
	public LineNumberInputStream(InputStream input) {
		super(input);
	}
	
	public void read() {
		super.read();
		// add new func
		
		System.out.println("add a linenumber func");
	}
}
