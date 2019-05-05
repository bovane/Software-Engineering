package com.bovane.referenceCodes;
public class PayMethod {
	public void pay(int kind) {
		if(kind==0) {
			System.out.print("by AliPay!");
		}else if(kind==1){
			System.out.print("by cash!");
		}
		else {
			System.out.print("by WeChat Pay!");
		}
	}
}
