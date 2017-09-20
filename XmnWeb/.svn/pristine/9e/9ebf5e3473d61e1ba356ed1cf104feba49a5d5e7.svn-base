package com.xmniao.xmn.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtil {
	
	@SuppressWarnings("unchecked")
	public static  <T> T getClassAnnotation(Object handler,Class<? extends Annotation> clazz){
		T annotation=null;
        boolean isPresent  = handler.getClass().isAnnotationPresent(clazz);
         if(isPresent){
        	 annotation =(T) handler.getClass().getAnnotation(clazz); 
         }
		return annotation;
	}
	
	@SuppressWarnings("unchecked")
	public static  <T> T getMethodAnnotation(Method handler,Class<? extends Annotation> clazz){
		T annotation=null;
        boolean isPresent  = handler.isAnnotationPresent(clazz);
         if(isPresent){
        	 annotation =(T) handler.getAnnotation(clazz); 
         }
		return annotation;
	}
	
	

}
