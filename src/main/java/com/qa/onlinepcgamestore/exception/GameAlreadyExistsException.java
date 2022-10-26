package com.qa.onlinepcgamestore.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Game Already Exists with these details")
public class GameAlreadyExistsException extends Exception{

}
