package com.bovane.referenceCodes;
public class App{
	public static void main(String args[]) {
		//ILog log = new FileLog();
		// DBLog log = new DBLog();
		//ZJSUDBLog zjsulog = new ZJSUDBLog();
		
		/*ZJSUFileLog zjsulog = new ZJSUFileLog();
		
		ILog log = new LogAdapter(zjsulog);
		
		
		BLOrder order = new BLOrder(log);
		order.save();*/
		
		
		/*Bill bill = new Bill();
		bill.setAmount(1500.0);
		
		President president = new President(bill);
		
		Manager manager = new Manager(bill);
		manager.setSuccessor(president);
		
		
		Leader leader  = new Leader(bill);
		leader.setSuccessor(manager);
		
		leader.process();*/
		
		Calculator obj = new Calculator();
		/*obj.calc('+',10.0);
		obj.calc('-',5.0);
		obj.calc('*',20.0);
		obj.calc('/',2.0);*/
		
		User user = new User();
		user.compute(obj, '+', 10.0);
		user.compute(obj, '-', 5.0);
		user.compute(obj, '*', 20.0);
		user.compute(obj, '/', 2.0);
		user.redo(3);
		
		System.out.println(obj.getTotal());
	}
}