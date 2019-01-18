package io.github.nowind.uido;

import org.junit.Assert;

public class CapAssert extends Assert
{
	public static void capAndLog()
	{
		UiContext.getBrowserEmulator().capture();
	}
	public static void fail(String message)
	{
		capAndLog();
		Assert.fail(message);
	}
	 public static void assertFalse(String msg,boolean condition)
	   {
		 if(condition)capAndLog();
		 Assert.assertFalse(msg, condition);
	   }
	 public static void assertEquals(String msg,Object a,Object b)
	   {
		 if(!a.equals(b))capAndLog();
		 Assert.assertEquals(msg, a, b);
	   }
	 public static void assertEquals(String msg,String a,String b)
	   {
		 if(!a.equals(b))capAndLog();
		 Assert.assertEquals(msg, a, b);
	   }
	 public static void assertTrue(String msg,boolean condition)
	   {
		 if(!condition)capAndLog();
		 Assert.assertTrue(msg, condition);
	   }
}