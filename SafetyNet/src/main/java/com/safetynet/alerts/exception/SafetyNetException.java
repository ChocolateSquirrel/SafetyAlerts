package com.safetynet.alerts.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SafetyNetException extends RuntimeException {

	public SafetyNetException(String message) {
		super();
		log.error(this.getClass().getSimpleName() + ":" + message);
	}

}
