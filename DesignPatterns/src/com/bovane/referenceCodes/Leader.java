package com.bovane.referenceCodes;
public class Leader extends Processor{
	private Bill bill;
	
	public Leader(Bill bill) {
		this.bill = bill;
	}
	
	public void process() {
		if(bill.getAmount()<1000.0) {
			System.out.print("leader processed.");
		}
		else {
			if(this.next != null)
				this.next.process();
		}
	}
}
