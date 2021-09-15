//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.helper;

import com.test.configuration.Configuration;
import com.test.configuration.PageFactory;
import com.test.exceptions.StopTestException;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;

public class ReportingHelper {
    static List<Result> results = new ArrayList();
    private static File path = null;
    private static String runnerName;

    public ReportingHelper() {
    }

    public static String takeScreenShot() throws StopTestException {
        File screenshotFile = new File("");
        WebDriver driver = PageFactory.getCurrentDriver();
        if (driver instanceof InternetExplorerDriver) {
            try {
                Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
                Rectangle screenBounds = new Rectangle(0, 0, screenDim.width, screenDim.height);
                Robot robot = new Robot();
                BufferedImage image = robot.createScreenCapture(screenBounds);
                String location = ExecControlHelper.getScreenshotLocation();
                String screenshotPath = null;
                if (System.getProperty("concordion.output.dir") != null) {
                    screenshotPath = System.getProperty("concordion.output.dir") + File.separator + location;
                } else {
                    screenshotPath = System.getProperty("java.io.tmpdir") + File.separator + "concordion" + File.separator + location;
                    screenshotPath = screenshotPath.replace("\\\\", File.separator);
                }

                screenshotFile = new File(screenshotPath + File.separator + "screenshot" + System.currentTimeMillis() + ".png");
                screenshotFile.mkdirs();
                ImageIO.write(image, "png", screenshotFile);
            } catch (AWTException var8) {
                System.err.println("Can not take a screenshot: " + var8.getMessage());
            } catch (IOException var9) {
                System.err.println("Can not take a screenshot: " + var9.getMessage());
            }
        }

        return screenshotFile.getName().length() == 0 ? "no screenshot" : screenshotFile.getAbsolutePath();
    }

    public static String getRemoteWebDriverEnv() {
        String env = "";

        try {
            WebDriver driver = PageFactory.getRemoteWebDriver("firefox");
            env = driver.getCurrentUrl();
        } catch (Exception var2) {
        }

        return env;
    }

    public static List<Result> getResultsForTestCase() throws StopTestException {
        Configuration config = Configuration.getConfiguration();
        String resultsLocation = config.getResultsLocation();
        File folder = new File(resultsLocation);
        File[] var11 = folder.listFiles();
        int var12 = var11.length;

        for(int var13 = 0; var13 < var12; ++var13) {
            File file = var11[var13];
            String fileName = file.getName();
            String environment = "";
            String sessionID = "";
            Result testResult = new Result();
            int endIndex = false;
            int lastIndexFullStop = false;
            int endIndex = fileName.lastIndexOf(".xml");
            int lastIndexFullStop = fileName.lastIndexOf(".", endIndex - 1);
            String testName = fileName.substring(lastIndexFullStop + 1, endIndex);
            if (!testName.toUpperCase().equals("REPORTINGHELPERTEST")) {
                testResult.setTestName(testName);
                String line = null;

                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while((line = bufferedReader.readLine()) != null) {
                        Pattern pattern = Pattern.compile("^HttpPost\\s+url\\s+=\\s+\\[http://(\\w+).*");
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find() && environment.length() == 0) {
                            testResult.setEnvironemnt(matcher.group(1));
                        }

                        pattern = Pattern.compile("^HttpPost\\s+sessionId=\\[(\\d+)]");
                        matcher = pattern.matcher(line);
                        if (matcher.find() && sessionID.length() == 0) {
                            testResult.setSessionID(matcher.group(1));
                        }

                        pattern = Pattern.compile("^\\s+<failure message=\"(.*)\\.\"");
                        matcher = pattern.matcher(line);
                        if (matcher.find() || environment.length() > 0) {
                            testResult.setErrorMessage(matcher.group(1));
                        }

                        matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            testResult.setErrorMessage(matcher.group(1));
                        } else {
                            pattern = Pattern.compile("^\\s+<error message=\"(.*)");
                            matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                String errorMsg = matcher.group(1);
                                if (errorMsg.length() > 100) {
                                    testResult.setErrorMessage(matcher.group(1).substring(0, 100));
                                } else {
                                    testResult.setErrorMessage(matcher.group(1));
                                }
                                break;
                            }
                        }

                        pattern = Pattern.compile("Successes:\\s(\\d+),\\sFailures:\\s(\\d+)$");
                        matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            testResult.setSuccesses(matcher.group(1));
                            testResult.setFailures(matcher.group(2));
                            testResult.setExceptions("0");
                            break;
                        }

                        pattern = Pattern.compile("Successes:\\s(\\d+),\\sFailures:\\s(\\d+),\\sExceptions:\\s(\\d+)$");
                        matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            testResult.setSuccesses(matcher.group(1));
                            testResult.setFailures(matcher.group(2));
                            testResult.setExceptions(matcher.group(3));
                            break;
                        }
                    }

                    fileReader.close();
                    bufferedReader.close();
                } catch (FileNotFoundException var20) {
                    throw new StopTestException("Unable to open file: " + file.getName() + " in ReportingHelper.java");
                } catch (IOException var21) {
                    throw new StopTestException("Error reading file : " + file.getName() + " in ReportingHelper.java");
                }
            }

            results.add(testResult);
        }

        return results;
    }

    public static void takeGridScreenShot() throws StopTestException, IOException {
        Configuration config = Configuration.getConfiguration();
        WebDriver webDriver = PageFactory.getCurrentDriver();
        WebDriver augmentedDriver = (new Augmenter()).augment(webDriver);
        File screenshot = (File)((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        String screenshotLocation = System.getProperty("user.home") + "/";
        FileUtils.copyFile(screenshot, new File(screenshotLocation + config.getTestCaseName() + "_" + System.currentTimeMillis() + ".png"));
    }

    public static void getPageSourceCode() throws StopTestException {
        Writer writer = null;
        String fileLocation = System.getProperty("user.home") + "/";

        try {
            WebDriver webDriver = PageFactory.getCurrentDriver();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation + Configuration.getConfiguration().getTestCaseName() + "_" + System.currentTimeMillis() + ".txt"), "utf-8"));
            writer.write(webDriver.getPageSource());
        } catch (IOException var11) {
        } finally {
            try {
                writer.close();
            } catch (Exception var10) {
            }

        }

    }
}
