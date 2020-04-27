package com.mygdx.clicker.Service;

public class FeatureFlagService {

    public static final String REGUEST_URL = "http://mateuszd2411.pythonanywhere.com/clickergame/api/v1.0/features";
    public static final String FEATURE_SHOP = "FEATURE_SHOP";

    private boolean shop = false;

    public void makeRequest(){
        //todo
    }

    public boolean hasShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }
}
