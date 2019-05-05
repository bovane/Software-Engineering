package com.bovane.referenceCodes;
import java.util.*;

public class User {
	private ArrayList<ICommand> lst = 
			new ArrayList<ICommand>();
	
	public void compute(Calculator calc,
						char operator,double value) {
		CalcCommand command = new CalcCommand(calc,operator,value);
		command.execute();
		
		lst.add(command);
		
	}
	
	public void redo(int levels) {
		for(int i=0;i<levels;i++) {
			if(levels<lst.size()) {
				lst.get(i).execute();
			}
		}
	}
	
	public void undo(int levels) {
		
	}
}
