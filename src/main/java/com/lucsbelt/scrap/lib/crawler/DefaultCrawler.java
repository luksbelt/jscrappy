package com.lucsbelt.scrap.lib.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lucsbelt.scrap.lib.Scrapper;
import com.lucsbelt.scrap.lib.Scrapper.Element;

public class DefaultCrawler implements Crawler{
	
	Logger logger = LoggerFactory.getLogger(DefaultCrawler.class);

	@Override
	public <T> void run(Scrapper<T> scrapper, String initUrl) throws Exception {
		List<String> urls = new ArrayList<String>(Arrays.asList(initUrl));
		for(int i =0; i < urls.size(); i++) {
			String nextUrl = urls.get(i);
			Document targetDocument = null;
			try {
				targetDocument = Jsoup.connect(nextUrl).ignoreContentType(true).parser(Parser.xmlParser()).get();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
				return;
			}
			
			Element ele = scrapper.parse(targetDocument);
			if(ele != null) {
				urls.addAll(ele.getUrls());
				if(ele.getElement() != null) {
					T element = scrapper.convert(ele.getElement());
					scrapper.validate(element);
					scrapper.save(element);
				}
				if(ele.getRet() != null) {
					while(ele != null && ele.getRet() != null && !ele.getUrls().isEmpty()) {
						Document nestedDocument = null;
						try {
							nestedDocument = Jsoup.connect(nextUrl).get();
						} catch (IOException e) {
							logger.error(e.getMessage(),e);
							return;
						}
						ele = ele.getRet().apply(nestedDocument);
					}
				}
			}
		}
	}

	
}
