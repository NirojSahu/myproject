package Scenarios2;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Start_and_Stop_Server_through_Code {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		String Start_Server="D:\\Appium\\node.exe D:\\Appium\\node_modules\\appium\\bin\\appium.js";
		Process process = Runtime.getRuntime().exec(Start_Server);
		
		if(process != null)
		{
			System.out.println("Started the Appium Server");
		}
		else
		{
			System.out.println("NOT able to Start the Server");
		}
		
		Thread.sleep(15000);
		
		//*******************************************************************************
		
		//Device details
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage", "com.boondoggle.mortcalc");
		capabilities.setCapability("appActivity", "com.boondoggle.mortcalc.MortCalc");
		
		//Appium details
		
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		String Exp_Result="222.44";
		System.out.println("Expected Result is "+Exp_Result);
		
		Thread.sleep(5000);
		
		driver.findElementById("com.boondoggle.mortcalc:id/var_amount").sendKeys("10000");
		driver.findElementById("com.boondoggle.mortcalc:id/var_years").sendKeys("5");
		driver.findElementById("com.boondoggle.mortcalc:id/var_interest").sendKeys("12");
		
		driver.findElementById("com.boondoggle.mortcalc:id/buttoncalc").click();
		Thread.sleep(5000);
		
		String Output = driver.findElementById("com.boondoggle.mortcalc:id/var_mortgage").getText();
		System.out.println("Output is  "+Output);
		
		String Actual_Result = Output.replace("$", "");
		System.out.println("Actual Result is "+Actual_Result);
		
		if(Actual_Result.equals(Exp_Result))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		//*************************************************
		//stop the server
		Thread.sleep(4000);
		
		if(process!=null)
		{
			process.destroy();
			Thread.sleep(4000);
			System.out.println("Stopped the Appium Server");
		}
		

	}

}
