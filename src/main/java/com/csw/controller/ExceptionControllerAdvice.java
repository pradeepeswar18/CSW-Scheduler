package com.csw.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.csw.exception.CswSchedulerException;
import com.csw.model.ErrorInfo;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@Autowired
	private Environment environment;
	
	@ExceptionHandler( {Exception.class, CswSchedulerException.class} )
	public ResponseEntity<ErrorInfo> cswExceptionHandler(CswSchedulerException exception) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorcode(404);
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}
}