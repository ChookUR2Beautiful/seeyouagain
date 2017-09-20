package com.xmniao.exception;

public class RepetitionLedgerException extends RuntimeException {
	public RepetitionLedgerException(String msg){
		super(msg);
	}

	public RepetitionLedgerException() {
		super();
	}
}
