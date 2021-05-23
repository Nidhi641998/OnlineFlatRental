package com.cg.onlineflatrental.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends Exception {
 //	Logger logger=LoggerFactory.getLogger(UserNotFoundException.class);

	public UserNotFoundException(String message) {
		super(message);
//		logger.info("In UserNotFoundException.....UserNotFoundException() started");
	}
}
