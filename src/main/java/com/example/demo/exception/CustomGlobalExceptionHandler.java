package com.example.demo.exception;

import java.util.Date;
//import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders header, HttpStatus status, WebRequest request) {

		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(),
				"From methodArgumentNotValid Exception in GEH", ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.BAD_REQUEST);
	}

	// Http request method not supported
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(),
				"From methodArgumentNotValid Exception in GEH - methos not allowed", ex.getMessage());

		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.METHOD_NOT_ALLOWED);

	}

	// UsernameNotfoundexception
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex,
			WebRequest request) {

		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.NOT_FOUND);

	}

	// ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.BAD_REQUEST);

	}

}
