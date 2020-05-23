package com.lucsbelt.scrap.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.jsoup.nodes.Document;

public interface Scrapper<T> {

	public Element parse(Document document);
	public T convert(String object);
	public void validate(T object) throws Exception;
	public void save(T object);
	
	public class Element{
		private Function<Document, Element> ret;
		private String element;
		private List<String> urls;
		
		public Element(Function<Document, Element> ret, String element, List<String> urls) {
			super();
			this.ret = ret;
			this.element = element;
			this.urls = urls;
		}
		
		public Element(String element, List<String> urls) {
			super();
			this.ret = null;
			this.element = element;
			this.urls = urls;
		}
		
		public String getElement() {
			return element;
		}
		public void setElement(String element) {
			this.element = element;
		}
		public Function<Document, Element> getRet() {
			return ret;
		}
		public void setRet(Function<Document, Element> ret) {
			this.ret = ret;
		}

		public List<String> getUrls() {
			if(urls == null)
				return new ArrayList<String>();
			return urls;
		}

		public void setUrls(List<String> urls) {
			this.urls = urls;
		}
	}
}
