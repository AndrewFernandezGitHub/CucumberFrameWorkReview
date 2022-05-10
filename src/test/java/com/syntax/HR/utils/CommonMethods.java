package com.syntax.HR.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializers{

    public static WebDriver driver;

    /**
     * This method is opening and navigating to the specified url
     */

    public void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");
        }
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        // will add this once we extend page initializers class initializePageObjects();
    }

    /**
     * This method sends text to any text box
     *
     * @param element
     * @param textToSend
     */
    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    /**
     * This Method switches to frame
     * If frame not found exception is handled
     * @param element
     */
    public static void switchToFrame(WebElement element) {
        try {
            driver.switchTo().frame(element);
        }catch (NoSuchFrameException e){
            e.printStackTrace();
        }
    }

    public static void switchToFrame(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }
    public static void switchToFrame(String nameOrID){
            try {
                driver.switchTo().frame(nameOrID);
            } catch (NoSuchFrameException e) {
                e.printStackTrace();
            }
        }
    public static void selectDdValue(WebElement element, String textToSelect) {
        Select select=new Select(element);
        List<WebElement>options=select.getOptions();
        for(WebElement option:options){
            if (option.getText().equals(textToSelect)){
                select.selectByVisibleText(textToSelect);
                break;
            }
        }
    }

    public static void selectDdValue(WebElement element, int index) {
        Select select=new Select(element);
        int size=select.getOptions().size();
        if(size>index){
            select.selectByIndex(index);
        }
    }

    /**
     * This method creates an object of WebDriver
     *
     * @return WebDriverWait
     */
    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }
    public static byte[] takeScreenShot(String fileName){
        TakesScreenshot ts= (TakesScreenshot) driver;
        byte[] picBytes= ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile=ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile,new File(Constants.SCREENSHOT_FILEPATH+fileName+" "+getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }
    public static String getTimeStamp(String pattern){
        Date date=new Date();
        //to format the date according to our choice we want to implement in this function
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    public static void tearDown() {
        driver.quit();
    }
}
