package Scenarios3;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Base_Class_mort {
	
	public AppiumDriver driver;
	
	
	@BeforeTest
	@Parameters({"deviceID"})
	public void Init_App(String deviceID ) throws MalformedURLException, InterruptedException
	{
		//Device details
				DesiredCapabilities capabilities= new DesiredCapabilities();
				
				//app details
				capabilities.setCapability("appPackage", "com.boondoggle.mortcalc");
				capabilities.setCapability("appActivity", "com.boondoggle.mortcalc.MortCalc");
				
				if(deviceID.equals("d1"))
				{
					System.out.println("Executing in Device1");
					
				capabilities.setCapability("deviceName", "GT-I9300I");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("platformVersion", "4.4.4");
				
				}
				else if(deviceID.equals("d2"))
				{
					System.out.println("Executing in Device2");
					
					capabilities.setCapability("deviceName", "SM-G350E");
					capabilities.setCapability("platformName", "Android");
					capabilities.setCapability("platformVersion", "4.4.2");
					
				}
				
				//Appium details
				
				 driver= new AndroidDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				
				Thread.sleep(4000);
	}

}
