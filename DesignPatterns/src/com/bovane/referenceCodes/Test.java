package com.bovane.referenceCodes;
public class Test {
	public static void main(String args[]) {
		
		InputStream obj = new BufferedInputStream(
							  new UpperInputStream(
								  new LineNumberInputStream(
									  new FileInputStream()
								  )
						      )
						  );
		obj.read();
	}
}
