package Scenarios2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hybrid_app_using_maps {

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage", "com.emn8.mobilem8.nativeapp.bk");
		capabilities.setCapability("appActivity", "com.emn8.mobilem8.nativeapp.bk.BKNativeMobileApp");
		
		//Appium Server details
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
		
		HashMap<String,String> hashMap=new HashMap<String,String>();
		
		Set<String> contextHandles = driver.getContextHandles();
		
		for(String contextname:contextHandles)
		{
						
			if(contextname.contains("NATIVE"))
			{
				hashMap.put("native", contextname);
			}
			else
			{
				hashMap.put("webview", contextname);
			}
			
		}
		
		//****************************************************************************************
		Thread.sleep(4000);
		
		driver.context(hashMap.get("native"));
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement ele_Voucher = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@index='9'][@content-desc='VOUCHERS'][@class='android.view.View']")));
		
		System.out.println(ele_Voucher.isDisplayed());
		
		Thread.sleep(4000);
		ele_Voucher.click();
		
		//***************************************************************************************************
		Thread.sleep(4000);
		
		driver.context(hashMap.get("native"));
		Thread.sleep(4000);
		
		WebElement ele_Redeem = driver.findElementByXPath("//*[@index='17'][@content-desc='REDEEM'][@class='android.view.View']");
		ele_Redeem.click();
		
		//*********************************************************************************************
		driver.context(hashMap.get("webview"));
		
		Thread.sleep(4000);
		
		WebDriverWait wait3 = new WebDriverWait(driver, 50);
		WebElement ele_uname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email'][@name='email'][@class='x-input-email']")));
		
		ele_uname.sendKeys("test@gmail.com");
		
		WebElement ele_pwd = driver.findElementByXPath("//input[@type='password'][@name='password'][@class='x-input-password']");
		ele_pwd.click();
		
		ele_pwd.sendKeys("12345");
		
		
		driver.context(hashMap.get("native"));
		
		Thread.sleep(4000);
		
		WebElement ele_signin = driver.findElementByXPath("//*[@index='69'][@content-desc='SIGN IN'][@class='android.view.View']");
		ele_signin.click();
		
		Thread.sleep(4000);
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		

	}

}
