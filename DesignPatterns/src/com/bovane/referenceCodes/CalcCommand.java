package com.bovane.referenceCodes;
public class CalcCommand implements ICommand{
	private Calculator calc;
	private char operator;
	private double value;
	
	public CalcCommand(Calculator calc,
			char operator,double value) {
		this.calc = calc;
		this.operator = operator;
		this.value = value;
	}
	
	public void execute() {
		calc.calc(operator, value);
	}
	
	public void unExecute() {
		calc.calc(undo(operator), value);
	}
	
	private char undo(char operator) {
		char unOperator=' ';
		switch(operator) {
		case '+':unOperator='-';break;
		case '-':unOperator='+';break;
		case '*':unOperator='/';break;
		case '/':unOperator='*';break;
		}
		return unOperator;
	}
}
