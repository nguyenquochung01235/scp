package com.scp.web.common.implement;

import com.scp.web.common.IWebUICommon;
import com.scp.web.constant.ApplicationConstant;
import com.scp.web.driver.DriverBase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class WebUICommon extends DriverBase implements IWebUICommon {

    @Override
    public void capturePageScreenshot() {
        File screenshotFile = ((TakesScreenshot)baseDriver).getScreenshotAs(OutputType.FILE);
        Long currentTime = new Date().getTime();
        String fileName = currentTime.toString();
        try {
            FileUtils.copyFile(screenshotFile,new File("./src/results/screenshot/"+fileName+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void capturePageScreenshot(String fileName) {
        File screenshotFile = ((TakesScreenshot)baseDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile,new File("./src/results/screenshot/"+fileName+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearText(By location) throws NoSuchElementException{
        try {
            WebElement element = getElement(location);
            element.clear();
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void clearText(WebElement element) throws NoSuchElementException {
        try {
            element.clear();
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void clickElement(By location) {
        try {
            WebElement element = getElement(location);
            element.click();
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void clickElement(WebElement element) {
        try {
            element.click();
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void clickAndHoldElement(By location) {

    }

    @Override
    public void clickAndHoldElement(WebElement element) {

    }

    @Override
    public void clickText(String text) {

    }

    @Override
    public void dragAndDrop(By sourceLocation, By destinationLocation) {

    }

    @Override
    public void dragAndDrop(WebElement sourceElement, WebElement destinationElement) {

    }

    @Override
    public void doubleClickElement(By location) {

    }

    @Override
    public void doubleClickElement(WebElement element) {

    }

    @Override
    public void elementAttributeShouldMatch(By location, String attribute, String expect) {

    }

    @Override
    public void elementAttributeShouldMatch(WebElement element, String attribute, String expect) {

    }

    @Override
    public void elementAttributeShouldNotMatch(By location, String attribute, String expect) {

    }

    @Override
    public void elementAttributeShouldNotMatch(WebElement element, String attribute, String expect) {

    }

    @Override
    public void elementShouldBeDisabled(By location) {

    }

    @Override
    public void elementShouldBeDisabled(WebElement element) {

    }

    @Override
    public void elementShouldBeEnabled(By location) {

    }

    @Override
    public void elementShouldBeEnabled(WebElement element) {

    }

    @Override
    public void elementShouldBeVisible(By location) {

    }

    @Override
    public void elementShouldBeVisible(WebElement element) {

    }

    @Override
    public void elementShouldContainText(By location, String text) {

    }

    @Override
    public void elementShouldContainText(WebElement element, String text) {

    }

    @Override
    public void elementShouldNotContainText(By location, String text) {

    }

    @Override
    public void elementShouldNotContainText(WebElement element, String text) {

    }

    @Override
    public void elementTextShouldBe(By location, String text) {

    }

    @Override
    public void elementTextShouldBe(WebElement element, String text) {

    }

    @Override
    public void elementValueShouldBe(By location, String value) {

    }

    @Override
    public void elementValueShouldBe(WebElement element, String value) {

    }

    @Override
    public void elementTextShouldNotBe(By location, String text) {

    }

    @Override
    public void elementTextShouldNotBe(WebElement element, String text) {

    }

    @Override
    public void elementValueShouldNotBe(By location, String value) {

    }

    @Override
    public void elementValueShouldNotBe(WebElement element, String value) {

    }

    @Override
    public void getCurrentUrl() {

    }

    @Override
    public WebElement getElement(By location) {
           try{
               Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                       .withTimeout(Duration.ofMillis(ApplicationConstant.TIME_OUT))
                       .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                       .ignoring(NoSuchElementException.class);

               return  wait.until(driver -> {
                   if(driver.findElement(location).isDisplayed()){
                       return driver.findElement(location);
                   }
                   return null;
               });
           }catch (Exception error){
               System.err.println("No such found element");
               return null;
           }
    }

    @Override
    public List<WebElement> getElements(By location) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                    .withTimeout(Duration.ofMillis(ApplicationConstant.TIME_OUT))
                    .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                    .ignoring(NoSuchElementException.class);

            return  wait.until(driver -> {
                if(driver.findElement(location).isDisplayed()){
                    return driver.findElements(location);
                }
                return null;
            });
        }catch (NoSuchElementException error){
            System.err.println("No such found element");
            return null;
        }
    }

    @Override
    public WebElement getElement(By location, long timeout) {
        Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                .withTimeout(Duration.ofMillis(timeout))
                .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                .ignoring(NoSuchElementException.class);

        return  wait.until(driver -> {
            if(driver.findElement(location).isDisplayed()){
                return driver.findElement(location);
            }
            return null;
        });
    }

    @Override
    public List<WebElement> getElements(By location, long timeout) {
        Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                .withTimeout(Duration.ofMillis(timeout))
                .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                .ignoring(NoSuchElementException.class);

        return  wait.until(driver -> {
            if(driver.findElement(location).isDisplayed()){
                return driver.findElements(location);
            }
            return null;
        });
    }

    @Override
    public String getElementAttribute(By location) {
        return null;
    }

    @Override
    public String getElementAttribute(WebElement element) {
        return null;
    }

    @Override
    public int getNumberOfListElement(By location) {
        return 0;
    }

    @Override
    public int getNumberOfListElement(List<WebElement> elementList) {
        return 0;
    }

    @Override
    public String getSourcePage() {
        return null;
    }

    @Override
    public String getText(By location) {
        return null;
    }

    @Override
    public String getText(WebElement element) {
        return null;
    }

    @Override
    public void hoverElement(By location) {

    }

    @Override
    public void hoverElement(WebElement element) {

    }

    @Override
    public void inputText(By location) {

    }

    @Override
    public void inputText(WebElement element) {

    }

    @Override
    public void navigateToURL(String url){
        try{
            UrlValidator validator = new UrlValidator();
            Assert.assertTrue(validator.isValid(url),"Url is not valid");
            baseDriver.navigate().to(url);
        }catch (Exception error){
            System.err.println("Can not process with this url");
        }
    }

    @Override
    public void pageShouldContainText(String text) {

    }

    @Override
    public void pageShouldNotContainText(String text) {

    }

    @Override
    public void pageShouldContainElement(By location) {

    }

    @Override
    public void pageShouldNotContainElement(By location) {

    }

    @Override
    public void pageShouldContainElement(WebElement element) {

    }

    @Override
    public void pageShouldNotContainElement(WebElement element) {

    }

    @Override
    public void reloadCurrentPage() {

    }

    @Override
    public void waitUntilPageContainText(String text) {

    }

    @Override
    public void waitUntilPageContainText(String text, long millis) {

    }

    @Override
    public void waitUntilPageContainElement(By location) {

    }

    @Override
    public void waitUntilPageNotContainElement(By location) {

    }

    @Override
    public void waitUntilPageContainElement(WebElement element) {

    }

    @Override
    public void waitUntilPageNotContainElement(WebElement element) {

    }
}
