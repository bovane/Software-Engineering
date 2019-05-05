package com.bovane.referenceCodes;
public class CPUFactory {
	
	public static CPU createCPU(int kind) {
		
		//CPU cpu = new IntelCPU();
		CPU cpu;
		
		if(kind==1) {
			cpu = (CPU) new IntelCPU();
		}
		else {
			cpu = (CPU) new AMDCPU();
		}
		return cpu;
		
	}
}
