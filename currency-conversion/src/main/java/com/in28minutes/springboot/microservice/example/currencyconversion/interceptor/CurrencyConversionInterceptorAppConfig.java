package com.in28minutes.springboot.microservice.example.currencyconversion.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class CurrencyConversionInterceptorAppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	CurrencyConversionInterceptor currencyConversionInterceptor;
	

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(currencyConversionInterceptor).addPathPatterns("/**/currency-converter-feign/**/");
   }

}
