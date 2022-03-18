package Scenarios3;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Vertical_Swipe {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		//Launch filpboard app
		//class	            //reference variable= Object
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage", "flipboard.app");
		capabilities.setCapability("appActivity", "flipboard.activities.LaunchActivity");
		
		//Appium server details
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(12000);
		
		Dimension size = driver.manage().window().getSize();
		System.out.println("Size is "+size);
		
		int Starty=(int)(size.height*0.90);
		int Endy=(int)(size.height*0.20);
		
		System.out.println("Starty is "+Starty);
		System.out.println("Endy is "+Endy);
		
		int Startx=(int)(size.width*0.50);
		System.out.println("Startx is "+Startx);
		
		driver.swipe(Startx, Starty, Startx, Endy, 1000);
		Thread.sleep(4000);
		
		driver.swipe(Startx, Starty, Startx, Endy, 1000);
		Thread.sleep(4000);
		
		
		driver.swipe(Startx, Starty, Startx, Endy, 1000);
		Thread.sleep(4000);
		
		
		driver.swipe(Startx, Starty, Startx, Endy, 1000);
		Thread.sleep(4000);
		
		System.out.println("Vertical swipe from bottom to top is completed");
		
		//*************************************************************************
		
		int Starty1=(int)(size.height*0.20);
		int Endy1=(int)(size.height*0.90);
		
		System.out.println("Starty1 is "+Starty1);
		System.out.println("Endy1 is "+Endy1);
		
		int Startx1=(int)(size.width*0.50);
		System.out.println("Startx1 is "+Startx1);
		
		for(int i=1;i<=4;i++)
		{
		driver.swipe(Startx1, Starty1, Startx1, Endy1, 1000);
		Thread.sleep(4000);
		}
		
		System.out.println("Vertical swipe from Top to bottom is completed");
		
		

	}

}
