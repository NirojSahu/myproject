package Scenarios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Send_sms {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		//Launch app
		
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage", "com.android.mms");
		capabilities.setCapability("appActivity", "com.android.mms.ui.ConversationComposer");
		
		//Appium Server details
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
		
		String Exp_Result="7406145556";
		System.out.println("Expected Result is "+Exp_Result);
		
//		driver.findElementByXPath("//*[@index='0'][@class='android.widget.TextView'][@content-desc='Compose Button']").click();
//		
//		driver.findElementById("com.android.mms:id/recipients_editor_to").sendKeys("7406145556");
//		
//		driver.findElementById("com.android.mms:id/edit_text_bottom").sendKeys("Hi Students" +"\n" +"How are you ?");
//		Thread.sleep(3000);
//		
//		driver.findElementById("com.android.mms:id/send_button").click();
//		Thread.sleep(3000);
//		
//		//Back
//		driver.sendKeyEvent(AndroidKeyCode.BACK);
//		
		//element having same attributes
		
		List<WebElement> ele_msgcont = driver.findElementsById("com.android.mms:id/from");
		System.out.println(ele_msgcont.size());
		
		//flag
		int flag=0;//it is not matching
		
		//for each loop
		//for(datatype ref variable:array)
		//int i;
		
		for(WebElement ele:ele_msgcont)
		{
			System.out.println(ele.getText());
			
			if(ele.getText().equals(Exp_Result))
			{
				flag=1;//matching
				break;
			}
			else
			{
				flag=0;//Not matching
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
		
		
		//close app
		driver.quit();
		
		
		

	}

}
