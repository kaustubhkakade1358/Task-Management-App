package net.javaguides.springboot.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.javaguides.springboot.dto.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<net.javaguides.springboot.dto.ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		
		ApiResponse apiResponse=new ApiResponse(message,false,new Date());
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
		
		String message=ex.getMessage();
		
		ApiResponse apiResponse=new ApiResponse(message,false,new Date());
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>  handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		
		Map<String,String> resp=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((e)->{
			
			String fieldName = ((FieldError)e).getField();
			
			String message = e.getDefaultMessage();
			
			
			resp.put(fieldName, message);
		});
		
		
		
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
		
	}
	
	

}
