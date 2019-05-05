package com.bovane.referenceCodes;
public class Manager extends Processor{
	private Bill bill;
	
	public Manager(Bill bill) {
		this.bill = bill;
	}
	
	public void process() {
		if(bill.getAmount()<10000.0) {
			System.out.print("manager processed.");
		}
		else {
			this.next.process();
		}
	}
}

