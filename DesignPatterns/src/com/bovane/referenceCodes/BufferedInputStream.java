package com.bovane.referenceCodes;
public class BufferedInputStream extends FilterInputStream{
	public BufferedInputStream(InputStream input) {
		super(input);
	}
	
	public void read() {
		super.read();
		// add new func
		
		System.out.println("add a buffered func");
	}
}
