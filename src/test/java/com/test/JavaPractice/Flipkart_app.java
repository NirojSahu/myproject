package Scenarios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Flipkart_app {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		//Launch app
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage", "com.flipkart.android");
		capabilities.setCapability("appActivity", "com.flipkart.android.activity.HomeFragmentHolderActivity");
		
		//appium server details
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
		
		WebDriverWait wait = new WebDriverWait(driver, 35);
		WebElement until = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.flipkart.android:id/search_widget_textbox")));
		System.out.println(until.isDisplayed());
		
		driver.findElementById("com.flipkart.android:id/search_widget_textbox").click();
		Thread.sleep(4000);
		
		driver.findElementById("com.flipkart.android:id/search_autoCompleteTextView").sendKeys("Reebok shoes");
		Thread.sleep(4000);
		
		driver.sendKeyEvent(AndroidKeyCode.ENTER);
		Thread.sleep(12000);
		
		Dimension size = driver.manage().window().getSize();
		System.out.println(size);
		
		int Starty=(int)(size.height*0.90);
		int Endy=(int)(size.height*0.10);
		
		int Startx=(int)(size.width*0.50);
		
		WebElement ele_item1 = driver.findElementByXPath("//*[@class='android.widget.TextView']");
		Thread.sleep(4000);
		
		do
		{
			
			List<WebElement> ele_item2 = driver.findElementsByXPath("//*[@class='android.widget.TextView'][@index='2'][@text='Reebok SPEED XT Running Shoes']");
			int size2 = ele_item2.size();
			System.out.println(size2);
			Thread.sleep(4000);
		
		if(size2>0)
		{
			WebElement ele_item3 = driver.findElementByXPath("//*[@class='android.widget.TextView'][@index='2'][@text='Reebok SPEED XT Running Shoes']");
			ele_item3.click();
			break;
		}
			
		driver.swipe(Startx, Starty, Startx, Endy, 1000);
		Thread.sleep(4000);
		
		} while(ele_item1.isDisplayed()==true);
		
		
		WebDriverWait wait2 = new WebDriverWait(driver, 35);
		WebElement until2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.flipkart.android:id/product_buynow_1")));
		System.out.println(until2.isDisplayed());
		
		driver.findElementById("com.flipkart.android:id/product_buynow_1").click();
		Thread.sleep(4000);
		
		driver.findElementByXPath("//*[@class='android.widget.TextView'][@text='6'][@index='0']").click();
		Thread.sleep(3000);
		
		driver.findElementByXPath("//*[@class='android.widget.TextView'][@text='CONTINUE'][@index='0']").click();
		Thread.sleep(12000);
		
		String text = driver.findElementById("com.flipkart.android:id/title_action_bar").getText();
		System.out.println(text);
		
		if(text.contains("Select Address"))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		
		
	}

}
