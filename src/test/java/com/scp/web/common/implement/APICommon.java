package com.scp.web.common.implement;

import com.scp.web.common.IAPICommon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
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
    }

    @Override
    public Response sendGetRequest(String path) {
        Response response = given().get(path);
        System.out.println("Sending Get Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }

    @Override
    public Object sendGetRequestWithPathParameter(String path, List<Object> path_param) {
        RequestSpecification get = RestAssured.given();
        for (Object e : path_param){
            path = path.replaceFirst("\\{\\w+}", (String) e);
        }
        get.header("Content-Type", "application/json");

        Response response = get.get(path);

        System.out.println("Sending Get Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }


    @Override
    public Object sendPostRequest(String path, JSONObject param) {

        RequestSpecification post = RestAssured.given();
        post.header("Content-Type", "application/json");
        Response response = post.body(param.toMap()).post(path);

        System.out.println("Sending Post Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());

        return response;
    }

    @Override
    public Object sendPostRequest(String path, Map param) {
        RequestSpecification post = RestAssured.given();
        post.header("Content-Type", "application/json");
        Response response = post.body(param).post(path);

        System.out.println("Sending Post Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());

        return response;
    }

    @Override
    public Object sendPostRequest(String path, HashMap param) {
        RequestSpecification post = RestAssured.given();
        post.header("Content-Type", "application/json");
        Response response = post.body(param).post(path);

        System.out.println("Sending Post Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());

        return response;

    }

    @Override
    public Object sendPostRequest(String path, String jsonFileLocate) {
        Object json_param = keywordCommon.readFileAndConvertToJsonObject(jsonFileLocate);
        RequestSpecification post = RestAssured.given();
        post.header("Content-Type", "application/json");
        Response response = post.body(((JSONObject) json_param).toMap()).post(path);

        System.out.println("Sending Post Request: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());
        return response;
    }

    @Override
    public Object sendBasicAuthenticationRequest(String path, String username, String password) {
        authentication = basic(username, password);

        RequestSpecification authen = RestAssured.given();
        Response response = authen.get(path);

//        Response response = authen.auth().basic(username, password).get(path);

        System.out.println("Sending Get Request With Basic Authentication: " + baseURI + path);
        System.out.println("Response: "+ response.asPrettyString());

        return response;
    }


    @Override
    public void verifyStatus(Response response, int status) {
        int actualStatus = response.getStatusCode();
        Assert.assertTrue(actualStatus == status, "Response Status is not match: "+ "Expected: "+ status + " | Actual : "+ actualStatus);

    }

    @Override
    public void verifyStatusSuccess(Response response) {
        String status = String.valueOf(response.getStatusCode());
        Assert.assertTrue(status.matches("2.."));
    }
}
