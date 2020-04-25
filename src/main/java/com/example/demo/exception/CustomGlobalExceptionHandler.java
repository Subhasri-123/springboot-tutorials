package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders header,HttpStatus status, WebRequest request
			) {
		
		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), "From methodArgumentNotValid Exception in GEH", ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.BAD_REQUEST);
	}
}
