package com.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ZuulLoggingFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		//Should apply filter?
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
		log.info("Request -> {} request uri -> {}", req, req.getRequestURI());
		
		return null;
	}

	@Override
	public String filterType() {
		//When execute filter?
		return "pre";
	}

	@Override
	public int filterOrder() {
		//Priority
		return 1;
	}

}
