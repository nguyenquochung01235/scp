package com.scp.web.testcase;

import com.scp.web.common.implement.WebUICommon;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Demo {

    WebUICommon webUICommon = new WebUICommon();

    @Test
    public void test(){
        webUICommon.initialDriver();
        webUICommon.navigateToURL("https://www.saucedemo.com/v1/");
        webUICommon.capturePageScreenshot("before_click_login_button");
        webUICommon.getElement(By.xpath("//*[@id=\"login-buttn\"]")).click();
        webUICommon.capturePageScreenshot("after_click_login_button");
        webUICommon.quitDriver();
    }

}
