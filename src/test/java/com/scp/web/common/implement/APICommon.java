package com.scp.web.common.implement;

import com.scp.web.common.IAPICommon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

public class APICommon implements IAPICommon {
    KeywordCommon keywordCommon = new KeywordCommon();
    public APICommon(){
    }
    @Override
    public void setBaseUrl(String url) {
        baseURI = url;
        requestSpecification = RestAssured.given();
    }

    @Override
    public void setLogLevel(String level) {
        if (level.equals("all")) {
            requestSpecification = RestAssured.given().log().all();
            return;
        }
        if (level.equals("body")) {
            requestSpecification = RestAssured.given().log().body();
            return;
        }
        if (level.equals("param")) {
            requestSpecification = RestAssured.given().log().params();
            return;
        }
        if (level.equals("headers")) {
            requestSpecification = RestAssured.given().log().headers();
            return;
        }
        if (level.equals("cookies")) {
            requestSpecification = RestAssured.given().log().cookies();
            return;
        }
        if (level.equals("method")) {
            requestSpecification = RestAssured.given().log().method();
        }
    }

    @Override
    public void setContentTypeIsApplicationJson() {
        setHeaderAttribute("Content-Type", "application/json");
    }

    @Override
    public void setHeaderAttribute(String name, String value) {
        requestSpecification = RestAssured.given().header(name, value);

    }

    @Override
    public void setAuthentication(String token) {
        /// TODO Check This
        authentication = oauth2(token);
    }

    @Override
    public Response sendGetRequest(String path) {
        Response response = given().get(path);
        System.out.println("Sending Get Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }

    @Override
    public Response sendGetRequestWithPathParameter(String path, List<Object> path_param) {
        RequestSpecification get = RestAssured.given();
        for (Object e : path_param){
            path = path.replaceFirst("\\{\\w+}", (String) e);
        }

        Response response = get.get(path);

        System.out.println("Sending Get Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }


    @Override
    public Response sendPostRequest(String path, JSONObject param) {

        RequestSpecification post = RestAssured.given();
        Response response = post.body(param.toMap()).post(path);

        System.out.println("Sending Post Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }

    @Override
    public Response sendPostRequest(String path, Map param) {
        RequestSpecification post = RestAssured.given();
        Response response = post.body(param).post(path);

        System.out.println("Sending Post Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }

    @Override
    public Response sendPostRequest(String path, HashMap param) {
        RequestSpecification post = RestAssured.given();
        Response response = post.body(param).post(path);

        System.out.println("Sending Post Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }

    @Override
    public Response sendPostRequest(String path, String jsonFileLocate) {
        Object json_param = keywordCommon.readJsonFileAndConvertToJsonObject(jsonFileLocate);
        RequestSpecification post = RestAssured.given();
        Response response = post.body(((JSONObject) json_param).toMap()).post(path);

        System.out.println("Sending Post Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }

    @Override
    public void verifyStatus(Response response, int status) {
        int actualStatus = response.getStatusCode();
        Assert.assertEquals(status, actualStatus, "Response Status is not match: " + "Expected: " + status + " | Actual : " + actualStatus);
    }

    @Override
    public void verifyStatusSuccess(Response response) {
        String status = String.valueOf(response.getStatusCode());
        Assert.assertTrue(status.matches("2.."), "Unsuccessful Response" + response.asPrettyString());
    }

    @Override
    public void verifyStatusFailure(Response response) {
        String status = String.valueOf(response.getStatusCode());
        Assert.assertTrue(status.matches("[456].."), "Successful Response" + response.asPrettyString());
    }
}
