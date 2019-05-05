package com.bovane.referenceCodes;
public abstract class FilterInputStream implements InputStream{
	protected InputStream input;
	
	public FilterInputStream(InputStream input) {
		this.input = input;
	}
	
	public void read() {
		input.read();
	}
}
