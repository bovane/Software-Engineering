package com.bovane.referenceCodes;
public class VendingMachine {
	int type;
	
	IPayMethod payMethod;
	
	public VendingMachine(IPayMethod payMethod){
		this.payMethod = payMethod;
	}
	//PayMethod method = new PayMethod();
	
	//public void sale(int kind) {
	public void sale() {
		/*
		 * 
		 */
		
		pay();
		
		/*
		 * 
		 */
	}
	
	//private void pay(int kind) {
	private void pay() {	
		/*if(kind==0) {
			System.out.print("by AliPay!");
		}else if(kind==1){
			System.out.print("by cash!");
		}
		else {
			System.out.print("by WeChat Pay!");
		}
		*/
		
		//method.pay(kind);
		
		payMethod.pay();
		
		
	}
	
	public static void main(String args[]) {
		//IPayMethod payMethod = new AliPayMethod();
		
		//IPayMethod payMethod = new WeChatMethod();
		
		IPayMethod payMethod = new CreditCardMethod();
		
		VendingMachine zjgsuMachine = new VendingMachine(payMethod);
		
		//zjgsuMachine.sale(2);
		zjgsuMachine.sale();
		
	}
}
