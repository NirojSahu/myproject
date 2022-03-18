package Scenarios2;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Webbased_App_Google_Search {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "SM-G350E");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		
		//app details
		
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		
		
		//Appium server details
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
		
		driver.get("http://www.google.com");
		Thread.sleep(4000);
		
		driver.findElementByName("q").sendKeys("Sachin");
		driver.findElementByClassName("gws-lite__searchbox-submit").click();
		Thread.sleep(4000);
		
		String Actual_Result = driver.findElementByXPath("//div[contains(text(),'Cricketer')]").getText();
		System.out.println(Actual_Result);
		
		if(Actual_Result.equals("Cricketer"))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		
		
		

	}

}
