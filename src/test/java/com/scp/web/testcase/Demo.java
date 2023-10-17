package com.scp.web.testcase;

import com.scp.web.common.implement.APICommon;
import com.scp.web.common.implement.KeywordCommon;
import com.scp.web.common.implement.WebUICommon;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        webUICommon.capturePageScreenshot("before_click_login_button");
        webUICommon.getElement(By.xpath("//*[@id=\"login-button\"]")).click();
        webUICommon.capturePageScreenshot("after_click_login_button");
        webUICommon.clickText("abc");
        webUICommon.quitDriver();
    }

    @Test
    public void test_api(){
        apiCommon.setBaseUrl("https://practice.expandtesting.com/notes/api");

        System.out.println("Test sendGetRequest");
        apiCommon.sendGetRequest("/health-check");

        System.out.println("Test sendGetRequestWithPathParameter");
        List<Object> lparam = new ArrayList<>();
        lparam.add("pet");
        lparam.add("1");
        apiCommon.sendGetRequestWithPathParameter("/{category}/{petId}", lparam);

        System.out.println("Test sendPostRequest JsonObject");
        JSONObject json_create_user = new JSONObject();
        json_create_user.put("email","test_user_1@gmail.com");
        json_create_user.put("password","123456789");

        apiCommon.sendPostRequest("/users/login" ,json_create_user);

        System.out.println("Test sendPostRequest Map");
        Map<String, Object> map_create_user = new HashMap<>();
        map_create_user.put("email","test_user_1@gmail.com");
        map_create_user.put("password","123456789");
        apiCommon.sendPostRequest("/users/login", map_create_user);

        System.out.println("Test sendPostRequest Hashmap");
        HashMap<String, Object> hashmap_user = new HashMap<>();
        hashmap_user.put("email","test_user_1@gmail.com");
        hashmap_user.put("password","123456789");
        apiCommon.sendPostRequest("/users/login", hashmap_user);

        apiCommon.sendPostRequest("/users/login","/com/scp/web/testdata/data_api_test_1.json");

        apiCommon.sendBasicAuthenticationRequest("/users/profile", "test_user_1@gmail.com", "123456789");

    }

}


