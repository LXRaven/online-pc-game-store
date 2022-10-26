package com.qa.onlinepcgamestore.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Game found with these details")
@SuppressWarnings("serial")
public class GameNotFoundException extends Exception{

}
