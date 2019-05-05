package com.bovane.referenceCodes;
public class Calculator {
	private double total=0.0;
	
	public void calc(char operator,double value) {
		switch(operator) {
		case '+':total+=value;break;
		case '-':total-=value;break;
		case '*':total*=value;break;
		case '/':total/=value;break;
		}
	}
	
	public double getTotal() {
		return total;
	}
	
}
