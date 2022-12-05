package Scenarios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Drag_drop {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		//Launch app
				DesiredCapabilities capabilities= new DesiredCapabilities();
				
				//device details
				capabilities.setCapability("deviceName", "GT-I9300I");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("platformVersion", "4.4.4");
				
				//app details
				capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
				capabilities.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");
					
				//Appium server details
				AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				//******************************************************
				
				Thread.sleep(4000);
				String Exp_Result="Michael Brecker";
				
				driver.findElementById("com.mobeta.android.demodslv:id/activity_title").click();
				
				List<WebElement> ele_cont = driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle");
				System.out.println(ele_cont.size());
				
				//Touch Action
				TouchAction action = new TouchAction(driver);
				action.longPress(ele_cont.get(6)).moveTo(ele_cont.get(1)).release().perform();
				
				
				List<WebElement> ele_item = driver.findElementsById("com.mobeta.android.demodslv:id/text");
				System.out.println(ele_item.size());
				
				int flag=0;
				for(WebElement ele:ele_item)
				{
					System.out.println(ele.getText());
					
					if(ele.getText().equals(Exp_Result))
					{
						flag=1;
						break;
					}
					else
					{
						flag=0;
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
