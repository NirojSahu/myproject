package Scenarios;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Zoom_pinch {

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
		Thread.sleep(3000);
		
		//zoom
		driver.zoom(ele_image);
		Thread.sleep(8000);
		
		//pinch
		driver.pinch(ele_image);
		Thread.sleep(8000);
		
		
		
		
		

	}

}
