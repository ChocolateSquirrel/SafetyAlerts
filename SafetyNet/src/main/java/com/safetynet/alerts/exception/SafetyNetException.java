package com.safetynet.alerts.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SafetyNetException extends RuntimeException {
	
	public SafetyNetException() {
		super();
		log.error(this.getClass().getSimpleName() + ":" + this.getMessage());
	}
	
	public SafetyNetException(String message, Throwable cause) {
		super();
		log.error(this.getClass().getSimpleName() + ":" + this.getMessage());
	}
	
	public SafetyNetException(String message) {
		super();
		log.error(this.getClass().getSimpleName() + ":" + this.getMessage());
	}
	
	public SafetyNetException(Throwable cause) {
		super();
		log.error(this.getClass().getSimpleName() + ":" + this.getMessage());
	}

}
