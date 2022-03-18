package Scenarios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Whats_app {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		capabilities.setCapability("deviceName","SM-G350E");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("platformVersion","4.4.2");
		
		capabilities.setCapability("appPackage","com.whatsapp");
		capabilities.setCapability("appActivity","com.whatsapp.Main");
		
		
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(5000);
		
		String Exp_Result="Happy New Year";
		System.out.println("Expected Result is "+Exp_Result);
		
		driver.findElementByXPath("//*[@class='android.widget.TextView'][@text='CONTACTS'][@index='0']").click();
		Thread.sleep(3000);
		
		driver.findElementByXPath("//*[@resource-id='com.whatsapp:id/contactpicker_row_name'][@text='Raj'][@index='0']").click();
		Thread.sleep(3000);
		
		driver.findElementById("com.whatsapp:id/entry").sendKeys("Happy New Year");
		
		driver.findElementById("com.whatsapp:id/send").click();
		
		List<WebElement> ele_cont = driver.findElementsById("com.whatsapp:id/message_text");
		System.out.println(ele_cont.size());
		
		int flag=0;//mismatch
		for(WebElement ele:ele_cont)
		{
			System.out.println(ele.getText());
			
			if(ele.getText().equals(Exp_Result))
			{
				flag=1;//matching
				break;
			}
			else
			{
				flag=0;//mismatch
			}
			
		}
		
		
		if(flag==1)
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
	}

}
