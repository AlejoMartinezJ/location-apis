package com.tesis.apis.locationmaps.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class appProp {
    private final String appkey;

    @Autowired
    public appProp(@Value("${location.api.key}") String appkey) {
        this.appkey = appkey;
    }

    public String getAppkey() {
        return appkey;
    }
}
