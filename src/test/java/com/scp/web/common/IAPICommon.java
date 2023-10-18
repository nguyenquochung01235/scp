package com.scp.web.common;

import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IAPICommon {

    public void setBaseUrl(String url);
    public void setLogLevel(String level);
    public void setContentTypeIsApplicationJson();
    public void setHeaderAttribute(String name, String value);

    public void setAuthentication(String token);
    public Response sendGetRequest(String path);
    public Response sendGetRequestWithPathParameter(String path, List<Object> path_param);
    public Response sendPostRequest(String path, JSONObject param);
    public Response sendPostRequest(String path, Map param);
    public Response sendPostRequest(String path, HashMap param);
    public Response sendPostRequest(String path, String jsonFileLocate);
    public void verifyStatus(Response response, int status);
    public void verifyStatusSuccess(Response response);
    public void verifyStatusFailure(Response response);
}
