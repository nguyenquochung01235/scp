package com.scp.web.driver;

import com.scp.web.constant.BrowserConstant;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverBase {
    final String ENVIRONMENT_APPLICATION_PROPERTIES_FILE = "./src/test/java/com/scp/web/configuration/environment.application.properties";
    public static WebDriver baseDriver;
    private static Properties properties = new Properties();
    public static WebDriver getBaseDriver() {
        return baseDriver;
    }
    public static void setBaseDriver(WebDriver webDriver) {
        baseDriver = webDriver;
    }
    public DriverBase(){
        try{
            properties.load(new FileInputStream(ENVIRONMENT_APPLICATION_PROPERTIES_FILE));
        }catch (IOException error){
            error.printStackTrace();
        }
    }
    public void initialDriver(){
        String browser = properties.getProperty("browser");
        if (browser == null) browser = BrowserConstant.CHROME_BROWSER;
        switch (browser){
            case BrowserConstant.CHROME_BROWSER:
                WebDriverManager.chromedriver().setup();
                baseDriver = new ChromeDriver();
                baseDriver.manage().window().maximize();
                break;
            case BrowserConstant.FIREFOX_BROWSER:
                WebDriverManager.firefoxdriver().setup();
                baseDriver = new FirefoxDriver();
                baseDriver.manage().window().maximize();
                break;
            case BrowserConstant.SAFARI_BROWSER:
                WebDriverManager.safaridriver().setup();
                baseDriver = new SafariDriver();
                baseDriver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }

    public void closeDriver(){
        try{
            baseDriver.close();
        }catch (Error error){
            System.err.println(error.getMessage());
        }
    }

    public void quitDriver(){
        try{
            baseDriver.quit();
        }catch (Error error){
            System.err.println(error.getMessage());
        }
    }
}
