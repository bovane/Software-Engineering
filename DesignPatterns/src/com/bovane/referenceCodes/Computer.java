package com.bovane.referenceCodes;
public class Computer {
	private CPU cpu;
	
	public Computer(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void setCPU(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void work() {
		if(cpu==null)
			return;
		
		cpu.run();
		
	}
	
	public static void main(String args[]) {
		
		
		//CPU cpu = new IntelCPU();
		//CPU cpu = new AMDCPU();
		//CPU cpu = CPUFactory.createCPU(0);
		CPU cpu = null;
		
		Computer computer = new Computer(cpu);
		
		computer.work();
		
	}
}
