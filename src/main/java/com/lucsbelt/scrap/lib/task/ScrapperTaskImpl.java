package com.lucsbelt.scrap.lib.task;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.data.util.AnnotatedTypeScanner;
import org.springframework.stereotype.Component;

import com.lucsbelt.scrap.lib.Scrapper;
import com.lucsbelt.scrap.lib.crawler.Crawler;
import com.lucsbelt.scrap.lib.crawler.GenericCrawler;


@Component
public class ScrapperTaskImpl {
	
	@PostConstruct
	public void scrapp() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException, InstantiationException, ClassNotFoundException {

		AnnotatedTypeScanner as = new AnnotatedTypeScanner(ScrapperTask.class);
		
		for(Class<?> sc : as.findTypes("")) {
			
			String url = sc.getAnnotation(ScrapperTask.class).value();
			long timeout = sc.getAnnotation(ScrapperTask.class).timeout();
			long interval = sc.getAnnotation(ScrapperTask.class).interval();
			String crawlerClassName = sc.getAnnotation(ScrapperTask.class).crawler();
			Scrapper<?> scrapper = (Scrapper<?>) sc.getDeclaredConstructors()[0].newInstance();
			Class<?> crawlerClass = Class.forName(crawlerClassName);
	
			Crawler crawler = (Crawler)crawlerClass.getDeclaredConstructor()
				.newInstance();
			
			GenericCrawler genericCrawler = new GenericCrawler(crawler, scrapper, url, timeout);
			
			ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
			ses.scheduleAtFixedRate(genericCrawler, 0l, interval,TimeUnit.SECONDS);

		}

	}
}
