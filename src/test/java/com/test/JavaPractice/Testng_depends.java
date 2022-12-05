package Scenarios3;

import org.testng.annotations.Test;

public class Testng_depends {
	
	@Test
	public void login()
	{
		int Result=4/0;
		System.out.println("login into app");
	}
	
	@Test(dependsOnMethods={"login"})
	public void sendmail()
	{
		System.out.println("Sent the mail");
	}

	@Test(dependsOnMethods={"sendmail"})
	public void closeapp()
	{
		System.out.println("Closed the app");
	}

}
