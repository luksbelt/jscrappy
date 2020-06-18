package com.lucsbelt.scrap.lib.task;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ScrapperTask {
	
	@AliasFor("url")
	public String value() default "";
	@AliasFor("value")
	public String url() default "";
	public String query() default "";
	public long timeout() default 0;
	public long interval() default 1;
	public int rpm() default 0;
	public String crawler() default "com.lucsbelt.scrap.lib.crawler.DefaultCrawler";

}
