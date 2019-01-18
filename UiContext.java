package io.github.nowind.uido;

import org.junit.Assert;

public class EduUiContext {
	 private static ThreadLocal<BrowserEmulator> threadLocalBE = new ThreadLocal<BrowserEmulator>();  
	 public static void initContext(int type)
	 {
		 if(type!=-1)
			 threadLocalBE.set(new BrowserEmulator(type));
		 else
			 threadLocalBE.set(new BrowserEmulator());
	 }
	 public static void initContext()
	 {
		 initContext(-1);
	 }
	 public static BrowserEmulator getBrowserEmulator()
	 {
		Assert.assertNotNull(threadLocalBE.get());
		return  threadLocalBE.get();
		 
	 }

	 
}
