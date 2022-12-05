package Scenarios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Drag_drop_Remove_item {

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
				
				driver.findElementByXPath("//*[@class='android.widget.TextView'][@index='0'][@text='CursorAdapter']").click();
		
				List<WebElement> ele_txtcont = driver.findElementsById("com.mobeta.android.demodslv:id/text");
				System.out.println(ele_txtcont.size());
				
				List<WebElement> ele_remcont = driver.findElementsById("com.mobeta.android.demodslv:id/click_remove");
				System.out.println(ele_remcont.size());
				
				//find Michael brecker and click on remove button
				for(int i=0;i<ele_txtcont.size();i++)
				{
					if((ele_txtcont.get(i).getText()).equals("Michael Brecker"))
					{
						ele_remcont.get(i).click();
						break;
						
						
					}
					
					
				}
				
				//We need to validate
				int flag=0;
				for(int i=0;i<ele_txtcont.size();i++)
				{
					
					if(!(ele_txtcont.get(i).getText()).equals("Michael Breaker"))
						{
						
							flag=1;
						
						}
					else
					{
						flag=0;
						break;
					}
					
				}
				
				//Validate
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
