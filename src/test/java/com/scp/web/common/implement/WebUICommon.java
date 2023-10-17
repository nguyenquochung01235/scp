package com.scp.web.common.implement;

import com.scp.web.common.IWebUICommon;
import com.scp.web.constant.ApplicationConstant;
import com.scp.web.driver.DriverBase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
        if(fileName.isBlank()){
            throw new IllegalArgumentException("File name can be null or blank");
        }
        fileName = fileName.trim().replace(" ","_");
        File screenshotFile = ((TakesScreenshot)baseDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile,new File("./src/results/screenshot/"+fileName+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearText(String xpath) {
        WebElement element = getElement(xpath);
        try {
            element.clear();
        }catch (RuntimeException exception){
            throw new InvalidElementStateException("Error: Can not clear text of element have path '"+xpath+"' cause this element not input type");
        }
    }

    @Override
    public void clearText(By location) throws NoSuchElementException{
        WebElement element = getElement(location);
        try {
            element.clear();
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not clear text of element have path '"+location.toString()+"' cause this element not input type");
        }
    }

    @Override
    public void clearText(WebElement element) throws NoSuchElementException {
        try {
            element.clear();
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not clear text of element have path '"+element.toString()+"' cause this element not input type");
        }
    }

    @Override
    public void clickElement(String xpath) {
        WebElement element = getElement(xpath);
        try {
            element.click();
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not click element have path '"+xpath+"', cause invalid state");
        }
    }

    @Override
    public void clickElement(By location) {
        WebElement element = getElement(location);
        try {
            element.click();
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not click element have path '"+location.toString()+"', cause invalid state");
        }
    }

    @Override
    public void clickElement(WebElement element) {
        try {
            element.click();
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not click element have path '"+element.toString()+"', cause invalid state");
        }
    }

    @Override
    public void clickAndHoldElement(String xpath) {
        WebElement element = getElement(xpath);
        try {
            Actions actions = new Actions(baseDriver);
            actions.clickAndHold(element).perform();
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not perform action click and hold element have path '"+xpath+"', cause invalid state");
        }
    }

    @Override
    public void clickAndHoldElement(By location) {
        WebElement element = getElement(location);
        try {
            Actions actions = new Actions(baseDriver);
            actions.clickAndHold(element).perform();
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not perform action click and hold element have path '"+location.toString()+"', cause invalid state");
        }
    }

    @Override
    public void clickAndHoldElement(WebElement element) {
        try {
            Actions actions = new Actions(baseDriver);
            actions.clickAndHold(element).perform();
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not perform action click and hold element have path '"+element.toString()+"', cause invalid state");
        }
    }

    @Override
    public void clickText(String text) {
        if(text.isEmpty() || text.isBlank()){
            throw new IllegalArgumentException("Text expect can be null or blank");
        }
        try{
            WebElement element = getElement(By.xpath("//body/*[text()[contains(.,'"+text+"')]][not(script)]"));
            element.click();
        }catch (Exception error){
            throw new java.util.NoSuchElementException("Can not found text '"+text+"' in current page");
        }

    }

    @Override
    public void dragAndDrop(String sourceXpath, String destinationXpath) {
        WebElement elementSource = getElement(sourceXpath);
        WebElement elementDestination = getElement(destinationXpath);
        try{
            Actions actions = new Actions(baseDriver);
            actions.dragAndDrop(elementSource, elementDestination).perform();
        }catch (Exception error){
            throw new InvalidElementStateException("Can not perform action drag and drop with elements have path '"+sourceXpath+"' and '"+destinationXpath+"'");
        }
    }

    @Override
    public void dragAndDrop(By sourceLocation, By destinationLocation) {
        WebElement elementSource = getElement(sourceLocation);
        WebElement elementDestination = getElement(destinationLocation);
        try{
            Actions actions = new Actions(baseDriver);
            actions.dragAndDrop(elementSource, elementDestination).perform();
        }catch (Exception error){
            throw new InvalidElementStateException("Can not perform action drag and drop with elements have path '"+sourceLocation.toString()+"' and '"+destinationLocation.toString()+"'");
        }
    }

    @Override
    public void dragAndDrop(WebElement sourceElement, WebElement destinationElement) {
        try{
            Actions actions = new Actions(baseDriver);
            actions.dragAndDrop(sourceElement, destinationElement).perform();
        }catch (Exception error){
            throw new InvalidElementStateException("Can not perform action drag and drop with elements have path '"+sourceElement.toString()+"' and '"+destinationElement.toString()+"'");
        }
    }

    @Override
    public void doubleClickElement(String xpath) {
        WebElement element = getElement(xpath);
        try{
            Actions actions = new Actions(baseDriver);
            actions.doubleClick(element);
        }catch (Exception error){
            throw new InvalidElementStateException("Can not perform action double click with elements have path '"+xpath+"'");
        }
    }

    @Override
    public void doubleClickElement(By location) {
        WebElement element = getElement(location);
        try{
            Actions actions = new Actions(baseDriver);
            actions.doubleClick(getElement(location));
        }catch (Exception error){
            throw new InvalidElementStateException("Can not perform action double click with elements have path '"+location.toString()+"'");
        }
    }

    @Override
    public void doubleClickElement(WebElement element) {
        try{
            Actions actions = new Actions(baseDriver);
            actions.doubleClick(element);
        }catch (Exception error){
            throw new InvalidElementStateException("Can not perform action double click with elements have path '"+element.toString()+"'");
        }
    }

    @Override
    public void elementAttributeShouldMatch(String xpath, String attribute, String expect) {
        if(attribute.isBlank() || attribute.isEmpty()){
            throw new IllegalArgumentException("The attribute properties can not blank or empty");
        }
        if(expect.isBlank() || expect.isEmpty()){
            throw new IllegalArgumentException("The expect properties can not blank or empty");
        }
        WebElement element = getElement(xpath);
        try {
            String actualAttribute = element.getAttribute(attribute);
            Assert.assertEquals(actualAttribute, expect, "Attribute of element does not match");
        }catch (Exception error){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+xpath+"'");
        }
    }

    @Override
    public void elementAttributeShouldMatch(By location, String attribute, String expect) {
        if(attribute.isBlank() || attribute.isEmpty()){
            throw new IllegalArgumentException("The attribute properties can not blank or empty");
        }
        if(expect.isBlank() || expect.isEmpty()){
            throw new IllegalArgumentException("The expect properties can not blank or empty");
        }
        WebElement element = getElement(location);
        try {
            String actualAttribute = element.getAttribute(attribute);
            Assert.assertEquals(actualAttribute, expect, "Attribute of element does not match");
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+location.toString()+"'");
        }
    }

    @Override
    public void elementAttributeShouldMatch(WebElement element, String attribute, String expect) {
        if(attribute.isBlank() || attribute.isEmpty()){
            throw new IllegalArgumentException("The attribute properties can not blank or empty");
        }
        if(expect.isBlank() || expect.isEmpty()){
            throw new IllegalArgumentException("The expect properties can not blank or empty");
        }
        try {
            String actualAttribute = element.getAttribute(attribute);
            Assert.assertEquals(actualAttribute, expect, "Attribute of element does not match");
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+element.toString()+"'");
        }
    }

    @Override
    public void elementAttributeShouldNotMatch(String xpath, String attribute, String expect) {
        if(attribute.isBlank() || attribute.isEmpty()){
            throw new IllegalArgumentException("The attribute properties can not blank or empty");
        }
        if(expect.isBlank() || expect.isEmpty()){
            throw new IllegalArgumentException("The expect properties can not blank or empty");
        }
        WebElement element = getElement(xpath);
        try {
            String actualAttribute = element.getAttribute(attribute);
            Assert.assertNotEquals(actualAttribute, expect, "Attribute of element is matching");
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+xpath+"'");
        }
    }

    @Override
    public void elementAttributeShouldNotMatch(By location, String attribute, String expect) {
        if(attribute.isBlank() || attribute.isEmpty()){
            throw new IllegalArgumentException("The attribute properties can not blank or empty");
        }
        if(expect.isBlank() || expect.isEmpty()){
            throw new IllegalArgumentException("The expect properties can not blank or empty");
        }
        WebElement element = getElement(location);
        try {
            String actualAttribute = element.getAttribute(attribute);
            Assert.assertNotEquals(actualAttribute, expect, "Attribute of element is matching");
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+location.toString()+"'");
        }
    }

    @Override
    public void elementAttributeShouldNotMatch(WebElement element, String attribute, String expect) {
        if(attribute.isBlank() || attribute.isEmpty()){
            throw new IllegalArgumentException("The attribute properties can not blank or empty");
        }
        if(expect.isBlank() || expect.isEmpty()){
            throw new IllegalArgumentException("The expect properties can not blank or empty");
        }
        try {
            String actualAttribute = element.getAttribute(attribute);
            Assert.assertNotEquals(actualAttribute, expect, "Attribute of element is matching");
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+element.toString()+"'");
        }
    }

    @Override
    public void elementShouldBeDisabled(String xpath) {
        WebElement element = getElement(xpath);
        try {
            Assert.assertFalse(element.isEnabled(), "Element have path '"+xpath+"' is not disabled");
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+xpath+"' or this element is not display anymore");
        }
    }

    @Override
    public void elementShouldBeDisabled(By location) {
        WebElement element = getElement(location);
        try {
            Assert.assertFalse(element.isEnabled(), "Element have path '"+location.toString()+"' is not disabled");
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+location.toString()+"' or this element is not display anymore");
        }
    }

    @Override
    public void elementShouldBeDisabled(WebElement element) {
        try {
            Assert.assertFalse(element.isEnabled(), "Element have path '"+element.toString()+"' is not disabled");
        }catch (Exception exception){
            throw new InvalidElementStateException("Can not get attribute of elements have path '"+element.toString()+"' or this element is not display anymore");
        }
    }

    @Override
    public void elementShouldBeEnabled(String xpath) {
        try {
            WebElement element = getElement(xpath);
            Assert.assertTrue(element.isEnabled(), "Element is not enabled");
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldBeEnabled(By location) {
        try {
            WebElement element = getElement(location);
            Assert.assertTrue(element.isEnabled(), "Element is not enabled");
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldBeEnabled(WebElement element) {
        try {
            Assert.assertTrue(element.isEnabled(), "Element is not enabled");
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldBeVisible(String xpath) {
        try {
            WebElement element = getElement(xpath);
            Assert.assertTrue(element.isEnabled(), "Element is not enabled");
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldBeVisible(By location) {
        try {
            WebElement element = getElement(location);
            Assert.assertTrue(element.isEnabled(), "Element is not enabled");
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldBeVisible(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed(), "Element is not enabled");
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldContainText(String xpath, String text) {
        try {
            String actualText = getElement(xpath).getText();
            Assert.assertTrue(actualText.contains(text), "Element text not contain '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldContainText(By location, String text) {
        try {
            String actualText = getElement(location).getText();
            Assert.assertTrue(actualText.contains(text), "Element text not contain '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldContainText(WebElement element, String text) {
        try {
            String actualText = element.getText();
            Assert.assertTrue(actualText.contains(text), "Element text not contain '"+text+"'");
        }catch (Exception exception){
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldNotContainText(String xpath, String text) {
        try {
            String actualText = getElement(xpath).getText();
            Assert.assertFalse(actualText.contains(text), "Element text contain '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldNotContainText(By location, String text) {
        try {
            String actualText = getElement(location).getText();
            Assert.assertFalse(actualText.contains(text), "Element text contain '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementShouldNotContainText(WebElement element, String text) {
        try {
            String actualText = element.getText();
            Assert.assertFalse(actualText.contains(text), "Element text contain '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementTextShouldBe(String xpath, String text) {
        try {
            String actualText = getElement(xpath).getText();
            Assert.assertEquals(actualText, text,"Element text not equal with '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementTextShouldBe(By location, String text) {
        try {
            String actualText = getElement(location).getText();
            Assert.assertEquals(actualText, text,"Element text not equal with '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementTextShouldBe(WebElement element, String text) {
        try {
            String actualText = element.getText();
            Assert.assertEquals(actualText, text,"Element text not equal with '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementValueShouldBe(String xpath, String value) {
        try {
            String actualValue = getElement(xpath).getAttribute("value");
            Assert.assertEquals(actualValue, value,"Element value not equal with '"+value+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementValueShouldBe(By location, String value) {
        try {
            String actualValue = getElement(location).getAttribute("value");
            Assert.assertEquals(actualValue, value,"Element value not equal with '"+value+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementValueShouldBe(WebElement element, String value) {
        try {
            String actualValue = element.getAttribute("value");
            Assert.assertEquals(actualValue, value,"Element value not equal with '"+value+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementTextShouldNotBe(String xpath, String text) {
        try {
            String actualText = getElement(xpath).getText();
            Assert.assertNotEquals(actualText, text,"Element text equal with '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementTextShouldNotBe(By location, String text) {
        try {
            String actualText = getElement(location).getText();
            Assert.assertNotEquals(actualText, text,"Element text equal with '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementTextShouldNotBe(WebElement element, String text) {
        try {
            String actualText = element.getText();
            Assert.assertNotEquals(actualText, text,"Element text equal with '"+text+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementValueShouldNotBe(String xpath, String value) {
        try {
            String actualValue = getElement(xpath).getAttribute("value");
            Assert.assertNotEquals(actualValue, value,"Element value equal with '"+value+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementValueShouldNotBe(By location, String value) {
        try {
            String actualValue = getElement(location).getAttribute("value");
            Assert.assertNotEquals(actualValue, value,"Element value equal with '"+value+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public void elementValueShouldNotBe(WebElement element, String value) {
        try {
            String actualValue = element.getAttribute("value");
            Assert.assertNotEquals(actualValue, value,"Element value equal with '"+value+"'");
        }catch (Exception exception) {
            System.err.println("No such found element");
        }
    }

    @Override
    public String getCurrentUrl() {
        try{
            return baseDriver.getCurrentUrl();
        }catch (Exception exception){
            throw new RuntimeException("Can not get current url");
        }
    }

    @Override
    public WebElement getElement(String xpath) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                    .withTimeout(Duration.ofMillis(ApplicationConstant.TIME_OUT))
                    .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                    .ignoring(NoSuchElementException.class);

            return  wait.until(driver -> driver.findElement(By.xpath(xpath)));
        }catch (Exception error){
            throw new RuntimeException("No such found element have path: '"+xpath+"'");
        }
    }

    @Override
    public WebElement getElement(By location) {
           try{
               Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                       .withTimeout(Duration.ofMillis(ApplicationConstant.TIME_OUT))
                       .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                       .ignoring(NoSuchElementException.class);

               return  wait.until(driver -> driver.findElement(location));
           }catch (Exception error){
               throw new RuntimeException("No such found element");
           }
    }

    @Override
    public List<WebElement> getElements(String xpath) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                    .withTimeout(Duration.ofMillis(ApplicationConstant.TIME_OUT))
                    .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                    .ignoring(NoSuchElementException.class);

            return  wait.until(driver -> driver.findElements(By.xpath(xpath)));
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public List<WebElement> getElements(By location) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                    .withTimeout(Duration.ofMillis(ApplicationConstant.TIME_OUT))
                    .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                    .ignoring(NoSuchElementException.class);

            return  wait.until(driver -> driver.findElements(location));
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public WebElement getElement(String xpath, long timeout) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                    .withTimeout(Duration.ofMillis(timeout))
                    .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                    .ignoring(NoSuchElementException.class);

            return  wait.until(driver -> driver.findElement(By.xpath(xpath)));
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public WebElement getElement(By location, long timeout) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                    .withTimeout(Duration.ofMillis(timeout))
                    .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                    .ignoring(NoSuchElementException.class);

            return  wait.until(driver -> driver.findElement(location));
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public List<WebElement> getElements(String xpath, long timeout) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                    .withTimeout(Duration.ofMillis(timeout))
                    .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                    .ignoring(NoSuchElementException.class);

            return  wait.until(driver -> driver.findElements(By.xpath(xpath)));
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public List<WebElement> getElements(By location, long timeout) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(baseDriver)
                    .withTimeout(Duration.ofMillis(timeout))
                    .pollingEvery(Duration.ofMillis(ApplicationConstant.POLLING))
                    .ignoring(NoSuchElementException.class);

            return  wait.until(driver -> driver.findElements(location));
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public String getElementAttribute(String xpath, String attribute) {
        try{
            if(attribute.isEmpty() || attribute.isBlank()) throw new IllegalArgumentException("Attribute can be null or empty");
            String attributeValue = getElement(xpath).getAttribute(attribute);
            Assert.assertNotNull(attributeValue, "Not found '"+attribute+"' attribute in this element");
            return attributeValue;
        }catch (Exception exception){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public String getElementAttribute(By location, String attribute) {
        try{
            if(attribute.isEmpty() || attribute.isBlank()) throw new IllegalArgumentException("Attribute can be null or empty");
            String attributeValue = getElement(location).getAttribute(attribute);
            Assert.assertNotNull(attributeValue, "Not found '"+attribute+"' attribute in this element");
            return attributeValue;
        }catch (Exception exception){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public String getElementAttribute(WebElement element, String attribute) {
        try{
            if(attribute.isEmpty() || attribute.isBlank()) throw new IllegalArgumentException("Attribute can be null or empty");
            String attributeValue = element.getAttribute(attribute);
            Assert.assertNotNull(attributeValue, "Not found '"+attribute+"' attribute in this element");
            return attributeValue;
        }catch (Exception exception){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public int getNumberOfListElement(String xpath) {
        try{
            List<WebElement> elementList = getElements(xpath);
            return elementList.size();
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public int getNumberOfListElement(By location) {
        try{
            List<WebElement> elementList = getElements(location);
            return elementList.size();
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public int getNumberOfListElement(List<WebElement> elementList) {
        try{
            return elementList.size();
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public String getSourcePage() {
        try{
            return baseDriver.getPageSource();
        }catch (Exception error){
            throw new RuntimeException("Can not get source base");
        }
    }

    @Override
    public String getText(String xpath) {
        try{
            return getElement(xpath).getText();
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public String getText(By location) {
        try{
            return getElement(location).getText();
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public String getText(WebElement element) {
        try{
            return element.getText();
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public void hoverElement(String xpath) {
        try{
            Actions actions = new Actions(baseDriver);
            actions.moveToElement(getElement(xpath));
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public void hoverElement(By location) {
        try{
            Actions actions = new Actions(baseDriver);
            actions.moveToElement(getElement(location));
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public void hoverElement(WebElement element) {
        try{
            Actions actions = new Actions(baseDriver);
            actions.moveToElement(element);
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public void inputText(String xpath, String text) {
        try{
            getElement(xpath).sendKeys(text);
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public void inputText(By location, String text) {
        try{
            getElement(location).sendKeys(text);
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
    }

    @Override
    public void inputText(WebElement element, String text) {
        try{
            element.sendKeys(text);
        }catch (Exception error){
            throw new RuntimeException("No such found element");
        }
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
        try{
            if(text.isBlank() || text.isEmpty()) throw new IllegalArgumentException("Text can be blank or empty");
            Assert.assertTrue(baseDriver.getPageSource().contains(text), "Page not contain text '"+text+"'");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void pageShouldNotContainText(String text) {
        try{
            if(text.isBlank() || text.isEmpty()) throw new IllegalArgumentException("Text can be blank or empty");
            Assert.assertFalse(baseDriver.getPageSource().contains(text), "Page contain text '"+text+"'");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void pageShouldContainElement(String xpath) {
        try{
            Assert.assertFalse(getElements(xpath).isEmpty(), "Page not contain this element");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void pageShouldContainElement(By location) {
        try{
            Assert.assertFalse(getElements(location).isEmpty(), "Page not contain this element");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void pageShouldNotContainElement(String xpath) {
        try{
            Assert.assertTrue(getElements(xpath).isEmpty(), "Page contain this element");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void pageShouldNotContainElement(By location) {
        try{
            Assert.assertTrue(getElements(location).isEmpty(), "Page contain this element");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }
    @Override
    public void reloadCurrentPage() {
        try{
            baseDriver.navigate().refresh();
        }catch (Exception error){
            System.err.println("Can not reload current page");
        }
    }

    @Override
    public void waitUntilPageContainText(String text) {
        try{
            WebElement element = getElement(By.xpath("//*[text()[contains(.,'"+text+"')]][not(script)]"));
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageContainText(String text, long millis) {
        try{
            WebElement element = getElement(By.xpath("//*[text()[contains(.,'"+text+"')]][not(script)]"), millis);
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageContainElement(String xpath) {
        try{
            Assert.assertFalse(getElements(xpath).isEmpty(), "Page not contain this element after 10 seconds");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageContainElement(By location) {
        try{
            Assert.assertFalse(getElements(location).isEmpty(), "Page not contain this element after 10 seconds");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageNotContainElement(String xpath) {
        try{
            long timeout = ApplicationConstant.TIME_OUT;
            while (timeout > 0){
                if(getElements(xpath, ApplicationConstant.POLLING).isEmpty()) {
                    timeout = 0;
                }else{
                    Thread.sleep(ApplicationConstant.POLLING);
                    timeout -=ApplicationConstant.POLLING;
                }
            }
            Assert.assertTrue(getElements(xpath).isEmpty(), "Page still contain this element after 10 seconds");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageNotContainElement(By location) {
        try{
            long timeout = ApplicationConstant.TIME_OUT;
            while (timeout > 0){
                if(getElements(location, ApplicationConstant.POLLING).isEmpty()) {
                    timeout = 0;
                }else{
                    Thread.sleep(ApplicationConstant.POLLING);
                    timeout -=ApplicationConstant.POLLING;
                }
            }
            Assert.assertTrue(getElements(location).isEmpty(), "Page still contain this element after 10 seconds");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageContainElement(String xpath, long millis) {
        try{
            Assert.assertFalse(getElements(xpath, millis).isEmpty(), "Page not contain this element after "+millis/1000+" seconds");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageContainElement(By location, long millis) {
        try{
            Assert.assertFalse(getElements(location, millis).isEmpty(), "Page not contain this element after "+millis/1000+" seconds");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageNotContainElement(String xpath, long millis) {
        try{
            long timeout = millis;
            while (timeout > 0){
                if(getElements(xpath, ApplicationConstant.POLLING).isEmpty()) {
                    timeout = 0;
                }else{
                    Thread.sleep(ApplicationConstant.POLLING);
                    timeout -=ApplicationConstant.POLLING;
                }
            }
            Assert.assertTrue(getElements(xpath).isEmpty(), "Page still contain this element after "+millis/1000+" seconds");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

    @Override
    public void waitUntilPageNotContainElement(By location, long millis) {
        try{
            long timeout = millis;
            while (timeout > 0){
                if(getElements(location, ApplicationConstant.POLLING).isEmpty()) {
                    timeout = 0;
                }else{
                    Thread.sleep(ApplicationConstant.POLLING);
                    timeout -=ApplicationConstant.POLLING;
                }
            }
            Assert.assertTrue(getElements(location).isEmpty(), "Page still contain this element after "+millis/1000+" seconds");
        }catch (Exception error){
            System.err.println("Can not get source page");
        }
    }

}
