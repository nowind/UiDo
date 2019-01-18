package io.github.nowind.uido;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;


public class BaseUiTest{
	protected int browserType=0;
	@AfterMethod(alwaysRun=true)
	public void testDown()
	{
		then().quit();
	}
	@BeforeMethod(alwaysRun=true)
	public void testUp()
	{
		UiContext.initContext(0);
		setUp();
	}
	protected void setUp()
	{
		
	}
	public BrowserEmulator startTest()
	{
		return EduUiContext.getBrowserEmulator();
	}
	public BrowserEmulator start()
	{
		return startTest();
	}
	public BrowserEmulator then()
	{
		return startTest();
	}
	public BrowserEmulator then(String des)
	{
		return then();
	}
	public BrowserEmulator cond()
	{
		return then();
	}
	public BrowserEmulator getBrowserEmulator()
	{
		return then();
	}
	public BrowserEmulator testStart()
	{
		return then();
	}
	public RemoteWebDriver getBrowserCore() {
		return then().getBrowserCore();
	}

	public Assertion testAssert() {
		return test();
	}
	
	public Assertion test() {
		return Assertion.me();
	}
	public Assertion test(String desc) {
		return test().test(desc);
	}
	public Assertion test(String desc,String base) {
		return test().test(desc,base);
	}
}
