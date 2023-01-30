package com.musala.alvaro.testdrones.exceptions;

import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class DronesExceptionHandler {

	//value = { IllegalArgumentException.class, IllegalStateException.class })
	
	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> dataNotFoundExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>(
        		new ExceptionDetail(new Date(), 
        		HttpStatus.BAD_REQUEST.toString(),
        		"Data not found.",
        		"code#1",
        		exception.getMessage() ), 
        HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(NotAvailableDroneException.class)
    public ResponseEntity<?> NotAvailableDroneExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>(
        		new ExceptionDetail(new Date(), 
        		HttpStatus.BAD_REQUEST.toString(),
        		"The drone it's not available.",
        		"code#2",
        		exception.getMessage() ), 
        HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(LowBatteryException.class)
    public ResponseEntity<?> LowBatteryExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>(
        		new ExceptionDetail(new Date(), 
        		HttpStatus.BAD_REQUEST.toString(),
        		"The drone has a low battery.",
        		"code#3",
        		exception.getMessage() ), 
        HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(NoMedicineToLoadException.class)
    public ResponseEntity<?> NoMedicineToLoadExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>(
        		new ExceptionDetail(new Date(), 
        		HttpStatus.BAD_REQUEST.toString(),
        		"There is no medicine to be loaded.",
        		"code#4",
        		exception.getMessage() ), 
        HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(WeightLimitException.class)
    public ResponseEntity<?> WeightLimitExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>(
        		new ExceptionDetail(new Date(), 
        		HttpStatus.BAD_REQUEST.toString(),
        		"The medicines weight it's over the limit.",
        		"code#5",
        		exception.getMessage() ), 
        HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> EmptyResultExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>(
        		new ExceptionDetail(new Date(), 
        		HttpStatus.BAD_REQUEST.toString(),
        		"Empty data result.",
        		"code#6",
        		exception.getMessage() ), 
        HttpStatus.BAD_REQUEST);
    }
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> UnknowExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
        		new ExceptionDetail(new Date(), 
        		HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        		"Unknow error sorry :(.",
        		"code#7",
        		exception.getMessage() ), 
        HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
