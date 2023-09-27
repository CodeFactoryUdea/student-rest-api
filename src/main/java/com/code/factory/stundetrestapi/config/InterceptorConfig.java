package com.code.factory.stundetrestapi.config;

import com.code.factory.stundetrestapi.interceptors.RequestIncerceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestIncerceptor requestIncerceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIncerceptor);
        super.addInterceptors(registry);
    }
}