package com.spring.boot.msk.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LogProcessingTimeFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		long startTime = System.currentTimeMillis();
		
		chain.doFilter(request, response);// DispatherServlet
		
		HttpServletRequest hReq = (HttpServletRequest)request;
		
		log.error(hReq.getMethod() +" : The Total Time taken to process "+hReq.getRequestURL()+" is " +(System.currentTimeMillis() - startTime)+" Milli Seconds ");
	}

}
