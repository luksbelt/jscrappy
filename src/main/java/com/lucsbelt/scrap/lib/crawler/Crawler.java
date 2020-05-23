package com.lucsbelt.scrap.lib.crawler;

import com.lucsbelt.scrap.lib.Scrapper;

public interface Crawler {

	public <T> void run(Scrapper<T> scrapper, String initUrl) throws Exception;
}
