package com.aryanto.currencyconverter.errorhandling;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aryanto.currencyconverter.utils.FailureMessage;

@RestController
public class BasicErrorController implements ErrorController{

	  @RequestMapping("/error")
	  public ErrorMessage handleError(HttpServletRequest request) {
	      String message;
		  Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	      Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
	     
	      message = generateMessage(e,statusCode);
	      
	      return new ErrorMessage(statusCode,message);
	  }
	  
	  private String generateMessage(Exception e, Integer statusCode){
		  	if(statusCode == 400){
		    	 return FailureMessage.BAD_REQUEST;
		     }else if(statusCode == 500){
		    	 return FailureMessage.INTERNAL_ERROR;
		     }else if(statusCode == 404){
		    	 return FailureMessage.NOT_FOUND;
		     }else if(e==null){
		    	 return FailureMessage.NOT_AVAILABLE;
		     }
			return e.getMessage();
		}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

	
}
