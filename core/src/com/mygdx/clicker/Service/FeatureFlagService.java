package com.mygdx.clicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.mygdx.clicker.IRequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FeatureFlagService {

    public static final String REGUEST_URL = "http://mateuszd2411.pythonanywhere.com/clickergame/api/v1.0/features";
    public static final String FEATURE_SHOP = "FEATURE_SHOP";

    private Map<String,Boolean> featuresMap;

    public FeatureFlagService() {
        featuresMap = new HashMap<String, Boolean>();
    }

    public void makeFlagsRequest(final IRequestCallback requestCallback){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(REGUEST_URL).build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                parseResponse(httpResponse.getResultAsString());

                requestCallback.onSucceed();
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t.getMessage());
                requestCallback.onError();
            }

            @Override
            public void cancelled() {
                requestCallback.onError();
            }
        });
    }

    private void parseResponse(String resultAsString) {
        System.out.println("Response: " + resultAsString);

        try {
            JSONObject obj = new JSONObject(resultAsString);
            JSONArray jsonArray = obj.getJSONArray("features");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject innerObj = jsonArray.getJSONObject(i);
                featuresMap.put((String)innerObj.get("name"),(Boolean)innerObj.get("active"));
            }
            System.out.println("Parse map: " + featuresMap);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public boolean hasFeature(String s){
        if (featuresMap.containsKey(s)){
            return false;
        }else {
            return featuresMap.get(s);
        }
    }
}
