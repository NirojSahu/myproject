package Scenarios2;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Base_Class_mort {
	
	public static Process process;
	public static AndroidDriver driver;
	
	public static void Start_Server() throws IOException, InterruptedException
	{
		String Start_Server="D:\\Appium\\node.exe D:\\Appium\\node_modules\\appium\\bin\\appium.js";
		 process = Runtime.getRuntime().exec(Start_Server);
		
		if(process != null)
		{
			System.out.println("Started the Appium Server");
		}
		else
		{
			System.out.println("NOT able to Start the Server");
		}
		
		Thread.sleep(15000);
	}
	
	public static void Init_App() throws MalformedURLException, InterruptedException
	{
		//Device details
				DesiredCapabilities capabilities= new DesiredCapabilities();
				
				capabilities.setCapability("deviceName", "GT-I9300I");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("platformVersion", "4.4.4");
				
				//app details
				capabilities.setCapability("appPackage", "com.boondoggle.mortcalc");
				capabilities.setCapability("appActivity", "com.boondoggle.mortcalc.MortCalc");
				
				//Appium details
				
				 driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				
				Thread.sleep(4000);
	}
	
	
	public static void Stop_Server() throws InterruptedException 
	{
						
				if(process!=null)
				{
					process.destroy();
					Thread.sleep(4000);
					System.out.println("Stopped the Appium Server");
				}
	}
	

}
