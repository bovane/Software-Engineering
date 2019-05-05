package com.bovane.referenceCodes;
public class President extends Processor{
	private Bill bill;
	
	public President(Bill bill) {
		this.bill = bill;
	}
	
	public void process() {
		System.out.print("president processed.");	
	}
}