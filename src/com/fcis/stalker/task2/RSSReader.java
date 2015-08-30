package com.fcis.stalker.task2;

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class RSSReader 
{
	private String rssLink;
	
	public RSSReader (String _rssLink)
	{
		this.rssLink = _rssLink;
	}
	
	public List<FeedItem> getItems () throws Exception 
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		ParserHandler handler = new ParserHandler();
		saxParser.parse(rssLink, handler);
		return handler.getItems();
	}
}