package io.github.nowind.uido;


public class Assertion extends BaseHelper {
	String desc;
	int step;
	String domBase;
	String parentDom="";
	public static Assertion me()
	{
		return me(Assertion.class);
	}
	public Assertion(String desc,String base)
	{
		super();
		this.desc=desc;
		step=0;
		domBase=base;
		parentDom=domBase;
	}
	public Assertion withTest(String desc,String base)
	{
		domBase=parentDom+" "+base;
		return this;
	}
	public Assertion(String desc)
	{
		this(desc,"");
	}
	public Assertion()
	{
		this("");
	}
	public Assertion test(String desc,String base)
	{
		this.desc=desc;
		this.domBase=base;
		this.parentDom=this.domBase;
		return this;
	}
	public Assertion test(String desc)
	{
		return test(desc,"");
	}
	public Assertion test()
	{
		return test("");
	}
	public BrowserEmulator then(){domBase="";return super.then();}
	public BrowserEmulator then(String des){return then();}
	private String fixdom(String dom)
	{
		if(dom==null)return null;
		if(domBase!=null&&!domBase.equals("")&&!dom.startsWith(domBase))dom=domBase+" "+dom;
		return dom;
	}
	public Assertion textEquals(String xpath,String s)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		  CapAssert.assertEquals(desc,s,be.getElement(xpath).getText());
		}
	  return this;
	}
	public Assertion textContains(String xpath,String s)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
			
			String exp=be.getElement(xpath).getText();
			CapAssert.assertTrue(desc+" expect "+exp+"contains "+s,exp.contains(s));
		}
	  return this;
	}
	public Assertion textContains(String xpath,String s1,String s2)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		String exp=be.getElement(xpath).getText();
	    CapAssert.assertTrue(desc+" expect "+exp+"contains "+s1+" and "+s2,exp.contains(s1)&&exp.contains(s2));
		}
	  return this;
	}
	public Assertion domExist(String dom)
	{
		return xpathExist(dom);
	}
	public Assertion xpathExist(String xpath)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		be.expectElementExistOrNot(true, xpath, 2000);
		}
		return this;
	}
	public Assertion xpathNotExist(String xpath)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		
		be.expectElementExistOrNot(false, xpath, 2000);
		}
		return this;
	}
	public Assertion xpathCount(String xpath,long count)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		long c=be.getElements(xpath).size();
		CapAssert.assertEquals(desc, count,c );
		}
		return this;
	}
	public Assertion urlEquals(String url)
	{
		if(disable)return this;
		CapAssert.assertEquals(desc, be.getBrowser().getLocation(),url);
		return this;
	}
	public Assertion urlContains(String url)
	{
		if(disable)return this;
		CapAssert.assertTrue(desc+"\n expect:"+be.getBrowser().getLocation()+" contains:"+url, be.getBrowser().getLocation().contains(url));
		return this;
	}
	
	public Assertion urlContainsAll(String[] urls)
	{
		String loc=be.getBrowser().getLocation();
			for(int i=0;i<urls.length;i++)
			CapAssert.assertTrue(desc+"\n expect:"+loc+" contains:"+urls[i], loc.contains(urls[i]));
		return this;
	}
	
	public Assertion urlNotContainsAll(String[] urls)
	{
		String loc=be.getBrowser().getLocation();
		for(int i=0;i<urls.length;i++)
			CapAssert.assertFalse(desc+"\n expect:"+loc+" not contains:"+urls[i], loc.contains(urls[i]));
		return this;
	}
	public Assertion urlContainsOne(String[] urls)
	{
		String loc=be.getBrowser().getLocation();
		int i=0;
		for(i=0;i<urls.length;i++)
			if(loc.contains(urls[i]))break;
		CapAssert.assertTrue(desc+"\n expect:"+loc+" contains one of your input",i<urls.length);
		return this;
	}
	public Assertion urlNotContains(String url)
	{
		CapAssert.assertFalse(desc+"\n expect:"+be.getBrowser().getLocation()+" not contains:"+url, be.getBrowser().getLocation().contains(url));
		return this;
	}
	public Assertion urlStart(String url)
	{
		CapAssert.assertTrue(desc+"\n expect:"+be.getBrowser().getLocation()+" startwith:"+url, be.getBrowser().getLocation().startsWith(url));
		return this;
	}
	public Assertion attrContains(String xpath,String attr,String text)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		String real=be.getElement(xpath).getAttribute(attr);
		CapAssert.assertTrue(desc+"\n expect:"+real+"contains:"+text, real.contains(text));
		}
		return this;
	}
	
	public Assertion imgContains(String xpath,String src)
	{
		return attrContains(xpath,"src",src);
	}
	public Assertion hrefContains(String xpath,String href)
	{
		return attrContains(xpath,"href",href);
	}
	
	public Assertion attrEq(String xpath,String attr,String text)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		String real=be.getElement(xpath).getAttribute(attr);
		CapAssert.assertEquals(desc,text,real);
		}
		return this;
	}
	public Assertion xpathShown(String xpath)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		CapAssert.assertTrue(desc, be.isElementPresent(xpath,-1));
		}
		return this;
	}
	public Assertion xpathHidden(String xpath)
	{
		xpath=fixdom(xpath);
		if(xpath!=null)
		{
		CapAssert.assertFalse(desc, be.isElementPresent(xpath,-1)&&be.getElement(xpath).isDisplayed());
		}
		return this;
	}
	public Assertion domSelected(String dom)
	{
		dom=fixdom(dom);
		if(dom!=null)
		{
		CapAssert.assertTrue(desc, be.getElement(dom).isSelected());
		}
		return this;
	}
	public Assertion domNotSelected(String dom)
	{
		dom=fixdom(dom);
		if(dom!=null)
		{
		CapAssert.assertFalse(desc, be.getElement(dom).isSelected());
		}
		return this;
	}
	

}
