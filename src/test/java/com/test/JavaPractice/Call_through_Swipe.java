package Scenarios;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Call_through_Swipe {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		//Launch app
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage", "com.android.contacts");
		capabilities.setCapability("appActivity", "com.android.contacts.activities.DialtactsActivity");
		
		//appium server details
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		//wait
		Thread.sleep(4000);
		
		driver.findElementByXPath("//*[@class='android.app.ActionBar$Tab'][@index='3']").click();
		Thread.sleep(4000);
		
		
		WebElement ele_item = driver.findElementByXPath("//*[@class='android.widget.TextView']");
		
		Dimension size = driver.manage().window().getSize();
		System.out.println(size);
		
		//Vertical swipe
		int Starty=(int)(size.height*0.90);
		int Endy=(int)(size.height*0.20);
		
		int Startx=(int)(size.width*0.50);
		
		do
		{
			
			List<WebElement> ele_item2 = driver.findElementsByXPath("//*[@text='Office2'][@index='1'][@class='android.widget.TextView']");
			int size2 = ele_item2.size();
			System.out.println(size2);
			Thread.sleep(4000);
			
			if(size2>0)
			{
				
				//horizontal swipe
				int Startx1=(int)(size.width*0.10);
				int Endx1=(int)(size.width*0.90);
				
				WebElement ele_item3 = driver.findElementByXPath("//*[@text='Office2'][@index='1'][@class='android.widget.TextView']");
				
				int Starty1 = ele_item3.getLocation().getY();
				
				driver.swipe(Startx1, Starty1, Endx1, Starty1, 1000);
				break;
				
			}
			
			
			driver.swipe(Startx, Starty, Startx, Endy, 1000);
			
		}while(ele_item.isDisplayed());
		
		
		Thread.sleep(8000);
		
		String text = driver.findElementById("com.android.phone:id/callStateLabel").getText();
		System.out.println(text);
		
		if(text.contains("Dialling"))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		

	}

}
