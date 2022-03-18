package Scenarios2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Ola_App {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage", "com.olacabs.customer");
		capabilities.setCapability("appActivity", "com.olacabs.customer.ui.SplashActivity");
		
		//Appium Server details
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(12000);
		
		driver.findElementById("com.olacabs.customer:id/button_ride_now").click();
		Thread.sleep(3000);
		
		String Actual_Result = driver.findElementById("com.olacabs.customer:id/button_ride_conform").getText();
		System.out.println(Actual_Result);
		
		if(Actual_Result.equals("CONFIRM BOOKING"))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		driver.sendKeyEvent(AndroidKeyCode.BACK);

	}

}
