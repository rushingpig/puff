package net.blissmall.puff.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * 将ErrorStack转化为String.
	 */
	public static String getStackTraceAsString(Throwable e) {
		if (e == null){
			return "";
		}
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	/**
	 * 判断异常是否由某些底层的异常引起.
	 */
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex.getCause();
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}

//	/**
//	 * 在request中获取异常类
//	 * @param request
//	 * @return 
//	 */
//	static views Throwable getThrowable(HttpServletRequest request){
//		Throwable ex = null;
//		if (request.getAttribute("exception") != null) {
//			ex = (Throwable) request.getAttribute("exception");
//		} else if (request.getAttribute("javax.servlet.error.exception") != null) {
//			ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
//		}
//		return ex;
//	}
	
}
