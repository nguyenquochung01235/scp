package com.scp.web.common;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IAPICommon {

    public void setBaseUrl(String url);
    public Response sendGetRequest(String path);
    public Object sendGetRequestWithPathParameter(String path, List<Object> path_param);
    public Object sendPostRequest(String path, JsonObject param);
    public Object sendPostRequest(String path, Map param);
    public Object sendPostRequest(String path, HashMap param);
    public Object sendPostRequest(String path, String jsonFileLocate);
    public void verifyStatus(Response response, int status);
    public void verifyStatusSuccess(Response response);
}
