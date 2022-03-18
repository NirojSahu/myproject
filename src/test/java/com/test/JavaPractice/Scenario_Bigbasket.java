package Day1;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Scenario_Bigbasket {

	public static void main(String[] args) throws MalformedURLException  {
		// TODO Auto-generated method stub
		
		//Launch an app
		//Class             //reference variable= Object
		DesiredCapabilities capabilities= new DesiredCapabilities();
				
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//App details
		capabilities.setCapability("appPackage", "com.bigbasket.mobileapp");
		capabilities.setCapability("appActivity", "com.bigbasket.mobileapp.activity.SplashActivity");
		
		//Appium Server
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		
		
		

	}

}
