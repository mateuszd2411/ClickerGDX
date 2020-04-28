package com.mygdx.clicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.mygdx.clicker.IRequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BalanceService {
    public static final String REQUEST_URL = "http://mateuszd2411.pythonanywhere.com/clickergame/api/v1.0/balance";
    public static final String BALANCE_MONEY_CLIKC = "BALANCE_MONEY_CLIKC";

    private int moneyClickValue;

    public void makeBalanceServiceRequest(final IRequestCallback requestCallback){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(REQUEST_URL).build();

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
        try {
            JSONObject obj = new JSONObject(resultAsString);
            JSONArray jsonArray = obj.getJSONArray("balance");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject innerObj = jsonArray.getJSONObject(i);
                if (innerObj.get("name").equals(BALANCE_MONEY_CLIKC)){
                    moneyClickValue = (Integer)innerObj.get("value");
                    System.out.println("MoneyClickValue: " + moneyClickValue);
                }
            }
        }catch (JSONException e){
            e.getMessage();
        }


    }

    public int getMoneyClickValue() {
        return moneyClickValue;
    }
}
