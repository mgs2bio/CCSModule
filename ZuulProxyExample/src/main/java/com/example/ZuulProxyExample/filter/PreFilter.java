package com.example.ZuulProxyExample.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	  public String filterType() {
	    return "pre";
	  }
	 
	  @Override
	  public int filterOrder() {
	    return 1;
	  }
	 
	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }

	/*
	 * if shouldFilter() is true, this method will be invoked. this method is the
	 * core method of a ZuulFilter
	 * Returns:
     * Some arbitrary artifact may be returned. Current implementation ignores it.
	 */
	  @Override
	  public Object run() {
	    RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	 
	    logger.info("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
	    return null;
	  }

}
