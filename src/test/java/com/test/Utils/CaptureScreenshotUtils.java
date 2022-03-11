package com.test.Utils;

import Utilities.WebKit.helper.BasePage;
import com.google.inject.Inject;
//import com.test.helper.BasePage;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

;


public class CaptureScreenshotUtils extends BasePage {

   /* @Inject
    CahootSteps cards_corporate_steps;*/


    public String folderName = null;
    public String DATESTRING = null;

    public String testScenarioName = null;

    final String USER_DIR = System.getProperty("user.dir");
    String Path = USER_DIR + "\\src\\test\\resources\\testScreenShots\\";

    String screenShotDir= USER_DIR+"\\src\\test\\resources\\TestData\\Screenshots\\";
    String screenShotDir_CucumberReport=USER_DIR+"/target/Screenshots/";

    public String getDateTimestamp() {
        Date date = new Date();
//        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss");
        String formatedDate = sdf.format(date);
        String[] tokens = formatedDate.split(Pattern.quote(" "));
        String dateString = dateSplitter(tokens[0].substring(0, tokens[0].length()), "/") + dateSplitter(tokens[1], ":");
        return dateString;
    }

    public String dateSplitter(String str, String formatter) {
        String[] strings = str.split(formatter);
        String string = strings[0] + strings[1] + strings[2];
        return string;
    }




    public void stepScreens() {
        folderName = testScenarioName;
        DATESTRING = getDateTimestamp();
        File dir = new File(Path + folderName);
        dir.mkdir();
        try {
            File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrnsht, new File(Path + folderName + "\\\\" + DATESTRING + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getScenarioName(Scenario scenario) {
        testScenarioName = scenario.getName();
        System.out.println(testScenarioName);
    }

   /* public void stepScreensWebElement(WebElement element) {
        folderName = testScenarioName;
        DATESTRING = getDateTimestamp();
        File dir = new File(Path + folderName);
        dir.mkdir();
        try {
            nonUIUtils.highlighElement(driver, element);
            File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            int ImageWidth = element.getSize().getWidth();
            int ImageHeight = element.getSize().getHeight();
            Point point = element.getLocation();
            int xcord = point.getX();
            int ycord = point.getY();
            BufferedImage img = ImageIO.read(scrnsht);
            BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
            ImageIO.write(dest, "png", scrnsht);
            FileUtils.copyFile(scrnsht, new File(Path + folderName + "\\\\" + DATESTRING + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void takeScreenShot(String screenshotName) {

//        System.out.println("-----------" + folderName + "------------------"+DATESTRING);
        File dir = new File(screenShotDir);
        dir.mkdir();
        System.out.println(dir.toString());

        try {
            File scrnsht =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrnsht, new
                    File(screenShotDir_CucumberReport+"/"+screenshotName+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void takeScreenShot() {

        String stringScenarioName = "Jenkinsscreenshot";
        //String stringScenarioName = cards_corporate_steps.testScenarioName;
        folderName=stringScenarioName;
        DATESTRING = getDateTimestamp();
        File dir = new File(screenShotDir + folderName);
        dir.mkdir();

        try {
            File scrnsht =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrnsht, new
                    File(screenShotDir+folderName+"\\"+DATESTRING+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
