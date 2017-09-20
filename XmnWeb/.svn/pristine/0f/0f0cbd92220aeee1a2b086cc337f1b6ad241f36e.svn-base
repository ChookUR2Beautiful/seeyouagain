package com.xmniao.xmn.core.exception;

/**
 * 自定义应用运行时异常 继承{@link RuntimeException} 
 * @author ch
 *
 */
public class ApplicationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object[] args;//传入参数
	private Object returnValue;//返回参数
	private String[] logInfo;
	
	
	/**
	 * 含有消息的异常
	 * @param msg
	 */
	public ApplicationException(String msg) {
		super(msg);
	}
	
	
	/**
	 * 无传参 无返回值的方法异常
	 * @param msg 异常消息
	 * @param cause 异常
	 */
	public ApplicationException(String msg,Throwable cause) {
		super(msg, cause);
	}
	
	/**
	 * 有消息 有传参的异常
	 * @param msg
	 * @param args
	 */
	public ApplicationException(String msg,Object[] args) {
		super(msg);
		this.args = args;
	}
	
	/**
	 * 有传参 无返回值的方法异常
	 * @param msg 异常消息
	 * @param cause 异常
	 * @param args 传入方法的参数
	 */
	public ApplicationException(String msg,Throwable cause,Object[] args) {
		super(msg, cause);
		this.args =args;
		
	}
	
	/**
	 * 有传参 无返回值的方法异常
	 * @param msg 异常消息
	 * @param cause 异常
	 * @param args 传入方法的参数
	 * @param logInfo 日志记录信息
	 */
	public ApplicationException(String msg,Throwable cause,Object[] args,String[] logInfo) {
		super(msg, cause);
		this.args =args;
		this.logInfo = logInfo;
		
	}
	
	
	/**
	 * 无传参 有返回值的方法异常
	 * @param msg 异常消息
	 * @param cause 异常
	 * @param returnValue 返回值信息
	 */
	public ApplicationException(String msg,Throwable cause,Object returnValue) {
		super(msg, cause);
		this.returnValue = returnValue;
	}
	
	/**
	 * 有传参 有返回值的方法异常
	 * @param msg 异常消息
	 * @param cause 异常
	 * @param args 传入方法的参数
	 * @param returnValue 返回值信息
	 */
	public ApplicationException(String msg,Throwable cause,Object[] args,Object returnValue) {
		super(msg, cause);
		this.args = args;
		this.returnValue = returnValue;
	}

	public Object[] getArgs() {
		return args;
	}

	
	public Object getReturnValue() {
		return returnValue;
	}
	

	public String[] getLogInfo() {
		return logInfo;
	}



	@Override
	public String getMessage() {
		return ApplicationExceptionUtils.buildMessage(super.getMessage(), getCause(),getArgs(),getReturnValue());
	}


}
