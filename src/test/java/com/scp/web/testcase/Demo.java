package com.scp.web.testcase;

import com.scp.web.common.implement.APICommon;
import com.scp.web.common.implement.KeywordCommon;
import com.scp.web.common.implement.WebUICommon;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


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
        apiCommon.setLogLevel("body");
        apiCommon.setLogLevel("method");


        apiCommon.setContentTypeIsApplicationJson();

//        System.out.println("Test sendGetRequest");
//        Response response_health_check = apiCommon.sendGetRequest("/health-check");
//        apiCommon.verifyStatus(response_health_check, 200);
//
//        System.out.println("Test sendGetRequestWithPathParameter");
//        List<Object> lparam = new ArrayList<>();
//        lparam.add("health-check");
//        Response response_health_check2 = apiCommon.sendGetRequestWithPathParameter("/{test_param}", lparam);
//        apiCommon.verifyStatusSuccess(response_health_check2);
//
//        System.out.println("Test sendGetRequest");
//        Response response_failed = apiCommon.sendGetRequest("/health-check-failed");
//        apiCommon.verifyStatusFailure(response_failed);
//
//        System.out.println("Test sendPostRequest JsonObject");
//        JSONObject json_user = new JSONObject();
//        json_user.put("email","test_user_1@gmail.com");
//        json_user.put("password","123456789");
//
//        apiCommon.sendPostRequest("/users/login" ,json_user);
//
//        System.out.println("Test sendPostRequest Map");
//        Map<String, Object> map_user = new HashMap<>();
//        map_user.put("email","test_user_1@gmail.com");
//        map_user.put("password","123456789");
//        apiCommon.sendPostRequest("/users/login", map_user);
//
//        System.out.println("Test sendPostRequest Hashmap");
//        HashMap<String, Object> hashmap_user = new HashMap<>();
//        hashmap_user.put("email","test_user_1@gmail.com");
//        hashmap_user.put("password","123456789");
//        apiCommon.sendPostRequest("/users/login", hashmap_user);
//
        Response login_info = apiCommon.sendPostRequest("/users/login", "/com/scp/web/testdata/data_api_test_1.json");
        JSONObject json_login_info = (JSONObject) new JSONObject(login_info.asString()).get("data");

        apiCommon.setHeaderAttribute("x-auth-token", (String) json_login_info.toMap().get("token"));
        apiCommon.sendGetRequest("/users/profile");


    }

}


