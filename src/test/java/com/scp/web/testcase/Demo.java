package com.scp.web.testcase;

import com.scp.web.common.implement.APICommon;
import com.scp.web.common.implement.KeywordCommon;
import com.scp.web.common.implement.WebUICommon;
<<<<<<< HEAD
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import org.openqa.selenium.By;
=======
import org.openqa.selenium.WebElement;
>>>>>>> backup
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Demo {

    WebUICommon webUICommon = new WebUICommon();
    APICommon apiCommon = new APICommon();

    KeywordCommon keywordCommon = new KeywordCommon();

    @Test
    public void e2e(){
        webUICommon.initialDriver();
        webUICommon.navigateToURL("https://www.saucedemo.com/v1/");
<<<<<<< HEAD
        webUICommon.capturePageScreenshot("before_click_login_button");
        webUICommon.getElement(By.xpath("//*[@id=\"login-button\"]")).click();
        webUICommon.capturePageScreenshot("after_click_login_button");
=======
        webUICommon.clickText("abc");
>>>>>>> backup
        webUICommon.quitDriver();
    }

    @Test
    public void test_api(){
        apiCommon.setBaseUrl("https://petstore.swagger.io/v2");

        System.out.println("Test sendGetRequest");
        apiCommon.sendGetRequest("/pet/1");

        System.out.println("Test sendGetRequestWithPathParameter");
        List<Object> lparam = new ArrayList<>();
        lparam.add("pet");
        lparam.add("1");
        apiCommon.sendGetRequestWithPathParameter("/{category}/{petId}", lparam);

        System.out.println("Test sendPostRequest JsonObject");
        JsonObject json_oder = new JsonObject();
        json_oder.add("id", 0);
        json_oder.add("petId", 0);
        json_oder.add("quantity", 0);
        json_oder.add("shipDate", "2023-10-16T09:57:51.630Z");
        json_oder.add("status", "placed");
        json_oder.add("complete", true);
        apiCommon.sendPostRequest("/store/order" ,json_oder);

        System.out.println("Test sendPostRequest Map");
        Map<String, Object> map_oder = new HashMap<>();
        map_oder.put("id",0);
        map_oder.put("petId",0);
        map_oder.put("quantity",0);
        map_oder.put("shipDate","2023-10-16T09:57:51.630Z");
        map_oder.put("status","placed");
        map_oder.put("complete",true);
        apiCommon.sendPostRequest("/store/order", map_oder);

        apiCommon.sendPostRequest("/pet","/com/scp/web/testdata/data_api_test_1.json");

    }

}


