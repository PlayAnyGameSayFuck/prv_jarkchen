package prv.jarkchen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnnotation {

	String opretion() default "";		// 描述方法作用
	String type() default "";
	String level() default "";
	
	/*
	 * 是否忽略返回值
	 */
	boolean ignoreReturn() default false;	
}
