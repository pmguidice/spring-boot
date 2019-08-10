package com.pg.config;

import com.pg.filters.RequestFilter;
import com.pg.filters.RequestResponseLoggingFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    public FilterConfig() {
    }

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(){
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.addUrlPatterns("/hello/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RequestFilter> requestFilter(){
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestFilter());
        registrationBean.addUrlPatterns("/hello/*");

        return registrationBean;
    }
}
