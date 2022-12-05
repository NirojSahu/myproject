package Scenarios;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Ebay_scenario {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		//launch app
				DesiredCapabilities capabilities= new DesiredCapabilities();
				
				//device details
				capabilities.setCapability("deviceName", "GT-I9300I");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("platformVersion", "4.4.4");
				
				//app details
				capabilities.setCapability("appPackage", "com.ebay.mobile");
				capabilities.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
				
				//server details
				AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				
				Thread.sleep(8000);
				String Exp_Result="161,484";
				System.out.println("Expected Result is "+Exp_Result);
				
				//click on the Search txtbox
				
				driver.findElementById("com.ebay.mobile:id/search_box").click();
				
				//Enter the Search item
				driver.findElementById("com.ebay.mobile:id/search_src_text").sendKeys("Shoes" +"\n");
				
				//wait
				Thread.sleep(4000);
				
				//Get the output
				
				String Output = driver.findElementById("com.ebay.mobile:id/textview_item_count").getText();
				System.out.println("Output is "+Output);
				
				String Actual_Result = Output.replace(" items", "");
				System.out.println("Actual Result is "+Actual_Result);
				
				if(Actual_Result.equals(Exp_Result))
				{
					System.out.println("Pass");
				}
				else
				{
					System.out.println("Fail");
				}
				
				//close the app
				driver.quit();

	}

}
