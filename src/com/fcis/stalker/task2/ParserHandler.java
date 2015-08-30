package com.fcis.stalker.task2;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserHandler extends DefaultHandler
{
	private List<FeedItem> itemsList;
	private FeedItem currentItem;
	private boolean parsingLink = false;
	private boolean parsingTitle = false;
	private boolean parsingDescription = false;
	private boolean parsingItem = false;
	private boolean parsingDate = false;
	
	public ParserHandler ()
	{
		itemsList = new ArrayList<FeedItem>();
	}
	
	public List<FeedItem> getItems()
	{
		return itemsList;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		// TODO Auto-generated method stub
		if (localName.equals("enclosure"))
		{
			parsingLink = true; 
		}
		else 
		{
		parsingLink = false;
		}
		
		if (localName.startsWith("item"))
		{
			currentItem = new FeedItem ();
			parsingItem = true;
		}
		
		else if (parsingItem)
		{
			if (localName.equals("link"))
			{
				parsingLink = true;
			}
			
			else
			{
				parsingLink = false;
			}
			
			if (localName.equals("title"))
			{
				parsingTitle = true;
			}
			
			else
			{
				parsingTitle = false;
			}
			
			if (localName.equals("description"))
			{
				parsingDescription = true;
			}
			
			else
			{
				parsingDescription = false;
			}
			
			if (localName.equals("pubDate"))
			{
				parsingDate = true;
			}
			
			else
			{
				parsingDate = false;
			}
			if (qName.equals("media:content"))
			{
				currentItem.setImageLink(attributes.getValue("url"));
			}
		}
	}


	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException 
	{
		// TODO Auto-generated method stub
		if (localName.equals("enclosure"))
		{
			parsingLink = true;
		}
		
		if (localName.startsWith("item"))
		{
			itemsList.add(currentItem);
			currentItem = null;
			parsingItem = true;
		}
		else if (parsingItem)
		{
			if (localName.equals("title"))
			{
			parsingTitle = false;
			}
			
			if (localName.equals("description"))
			{
				parsingDescription = false;
			}
			
			if (localName.equals("link"))
			{
			parsingLink = false;
			}
			if (localName.equals("pubDate"))
			{
			parsingDate = false;
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException 
	{
		// TODO Auto-generated method stub
		if (parsingLink)
		{
			if (currentItem != null)
			{
				currentItem.setLink(new String (ch, start, length));
				parsingTitle = false;
			}
			
		}

		else if (parsingTitle)
		{
			if (currentItem != null)
			{
				currentItem.setTitle(new String (ch, start, length));
				parsingLink = false;
			}
			
		}
		
		else if (parsingDate)
		{
			if (currentItem != null)
			{
				currentItem.setDate(new String (ch, start, length));
				parsingDate = false;
			}
		}
		
		else if (parsingDescription)
		{
			if (currentItem != null)
			{
				currentItem.setDescription(new String (ch, start, length));
				parsingDescription = false;
			}
		}
		
		
	}
}