package com.xmniao.xmn.core.exception;



import java.util.Arrays;
/**
 * 异常信息格式化工具类
 * @author ch
 *
 */
public final class ApplicationExceptionUtils {
	
	public static String buildMessage(String message, Throwable cause,Object[] args,Object returnValue) {
		if (cause != null) {
			StringBuilder sb = new StringBuilder();
			if (message != null) {
				sb.append("\n异常描述 :\t[").append(message).append("]\n");
			}
			StackTraceElement stack[] = cause.getStackTrace();
			if(stack!=null && stack.length>0){
				StackTraceElement ste = stack[0];
				sb.append(String.format("异常发生在 :\t[%s]类的[%s]方法中,可能导致发生异常的代码行号为 : [%s]\n", ste.getClassName(),ste.getMethodName(),ste.getLineNumber()));
			}
			if(args!=null && args.length>0){
				sb.append("请求方法参数 :\t[").append(Arrays.toString(args)).append("]\n");
			}
			if(returnValue!=null){
				sb.append("返回参数 :\t[").append(returnValue.toString()).append("]\n");
			}
			
			/*StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw);
		    try {
		    	cause.printStackTrace(pw);
		    } catch (RuntimeException ex) {
		    }
		    pw.flush();*/
			sb.append("异常堆栈跟踪信息:\n").append(cause);
			return sb.toString();
		}
		else {
			return message;
		}
	}

}
