package io.github.nowind.uido;

import java.util.HashMap;

public class BaseHelper {
	protected BrowserEmulator be;
	protected static HashMap<Class,HashMap<BrowserEmulator,BaseHelper>> pools=new HashMap<Class,HashMap<BrowserEmulator,BaseHelper>>();
	public BaseHelper()
	{
		be=EduUiContext.getBrowserEmulator();
	}
	public BrowserEmulator then()
	{
		return be;
	}
	protected  static <T extends BaseHelper> T me(Class<T> t)
	{
		BrowserEmulator ctx=EduUiContext.getBrowserEmulator();
		HashMap<BrowserEmulator,BaseHelper> pool=null;
		Class cls=t.getClass();
		if(pools.containsKey(cls))
		{
			pool=pools.get(cls);
		}
		else
		{
			pool=new HashMap<BrowserEmulator,BaseHelper>();
			pools.put(cls, pool);
		}
		if(pool.get(ctx)==null)
			try {
				pool.put(ctx,(BaseHelper)cls.newInstance());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		return pool.get(ctx);
	}
	public void end(){}
	
}
