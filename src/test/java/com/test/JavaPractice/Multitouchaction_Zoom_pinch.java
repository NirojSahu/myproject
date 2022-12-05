package Scenarios;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Multitouchaction_Zoom_pinch {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		File app= new File("D:\\Testing\\com.davemorrissey.labs.subscaleview.sample.apk");
		
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("app", app.getAbsolutePath());
		
		
		//appium server details
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
		
		driver.findElementById("com.davemorrissey.labs.subscaleview.sample:id/basicFeatures").click();
		Thread.sleep(3000);
		
		WebElement ele_image = driver.findElementById("com.davemorrissey.labs.subscaleview.sample:id/imageView");
		
		int x1=ele_image.getLocation().getX();
		int y1=ele_image.getLocation().getY();
				
		System.out.println("x1 is "+x1 + " y1 is "+y1);
		Thread.sleep(3000);
		
		int x= ele_image.getLocation().getX()+ele_image.getSize().width/2;
		int y= ele_image.getLocation().getY()+ele_image.getSize().height/2;
		System.out.println("x is "+x+" y is "+y);
		
		Thread.sleep(3000);
		
		//zoom functionality for 200 percent
		TouchAction finger1 = new TouchAction(driver);
		finger1.press(ele_image, x, y-20).moveTo(ele_image, x, y-200);
		
		TouchAction finger2= new TouchAction(driver);
		finger2.press(ele_image, x, y+20).moveTo(ele_image, x, y+200);
		
		MultiTouchAction action= new MultiTouchAction(driver);
		action.add(finger1).add(finger2).perform();
		
		Thread.sleep(8000);
		
		
		//pinch from 200%
		
		TouchAction finger3 = new TouchAction(driver);
		finger3.press(ele_image, x, y-200).moveTo(ele_image, x, y-20);
		
		TouchAction finger4= new TouchAction(driver);
		finger4.press(ele_image, x, y+200).moveTo(ele_image, x, y+20);
		
		MultiTouchAction action2= new MultiTouchAction(driver);
		action2.add(finger3).add(finger4).perform();
		
		Thread.sleep(8000);
		
		
		
		
		
		

	}

}
