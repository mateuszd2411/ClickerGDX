package com.mygdx.clicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.mygdx.clicker.IRequestCallback;

public class FeatureFlagService {

    public static final String REGUEST_URL = "http://mateuszd2411.pythonanywhere.com/clickergame/api/v1.0/features";
    public static final String FEATURE_SHOP = "FEATURE_SHOP";

    private boolean shop = false;

    public void makeFlagsRequest(final IRequestCallback requestCallback){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(REGUEST_URL).build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println("Result: ");
                System.out.println(httpResponse.getResultAsString());
                System.out.println("-------------------");

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
        //todo
    }

    public boolean hasShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }
}
