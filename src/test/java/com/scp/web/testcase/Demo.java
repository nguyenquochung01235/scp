package com.scp.web.testcase;

import com.scp.web.common.implement.WebUICommon;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Demo {

    WebUICommon webUICommon = new WebUICommon();

    @Test
    public void e2e(){
        webUICommon.initialDriver();
        webUICommon.navigateToURL("https://www.saucedemo.com/v1/");
        webUICommon.clickText("abc");
        webUICommon.quitDriver();
    }

}
