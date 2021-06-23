package com.csw.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@Autowired
	private Environment environment;
	
	@AfterThrowing(pointcut = "execution(* com.csw.controller.*.*(..))", throwing = "exception")
	public void logControllerException(Exception exception) {
		LOGGER.error(environment.getProperty(exception.getMessage(),
				"Something went wrong in controller. Please check log file for more details."), exception);
	}
	
	
	@AfterThrowing(pointcut = "execution(* com.csw.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) {
		LOGGER.error(environment.getProperty(exception.getMessage(),
				"Something went wrong in service. Please check log file for more details."), exception);
	}
	
	@AfterThrowing(pointcut = "execution( * com.csw.repository.*Impl.*(..))", throwing = "exception")
	public void logRepositoryException(Exception exception) {
		LOGGER.error(environment.getProperty(exception.getMessage(),
				"Something went wrong in repository. Please check log file for more details."), exception);
	}
	

}
