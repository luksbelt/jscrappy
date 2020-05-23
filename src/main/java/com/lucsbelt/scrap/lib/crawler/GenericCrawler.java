package com.lucsbelt.scrap.lib.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lucsbelt.scrap.lib.Scrapper;

public class GenericCrawler implements Runnable{
	
    Logger logger = LoggerFactory.getLogger(GenericCrawler.class);

	private Crawler crawler;
    private Scrapper<?> scrapper;
	private String initUrl;
	private long timeout;//TODO
	
	public GenericCrawler(Crawler crawler, Scrapper<?> scrapper, String initUrl, long timeout) {
		super();
		this.crawler = crawler;
		this.scrapper = scrapper;
		this.initUrl = initUrl;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		try {
			crawler.run(scrapper, initUrl);
		}
		catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	

}
