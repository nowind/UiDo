package io.github.nowind.uido;


public class Action extends BaseHelper
{
	public static Action me()
	{
		return me(Action.class);
	}
	public BrowserEmulator Login(String acc,String pwd)
	{
		LoginPageTestSuite.Login(be, acc,pwd); //static method
		return be;
	}
}
