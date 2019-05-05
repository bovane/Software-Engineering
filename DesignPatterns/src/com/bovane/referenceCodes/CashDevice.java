package com.bovane.referenceCodes;
public class CashDevice{
	private static CashDevice device = null;
	//故障标记
	private int iFlag = 0;
	
	public synchronized static CashDevice getInstance() {
		if(device==null)
			device = new CashDevice();
		
		return device;
	}
	
	public void setFlag(int iFlag) {
		this.iFlag = iFlag;
	}
	
	public void run() {
		System.out.println("Device state is " + iFlag);
	}
	
	public static void main(String args[]) {
		CashDevice device1 = CashDevice.getInstance();
		device1.run();
		
		device1.setFlag(1);
		device1.run();
		
		CashDevice device2 = CashDevice.getInstance();
		device2.run();
		
		device2.setFlag(2);
		device2.run();
		
		device1.run();
		
	}
}