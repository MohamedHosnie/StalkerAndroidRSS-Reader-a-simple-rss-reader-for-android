package com.fcis.stalker.task2;

public class FeedItem 
{
	private String title;
	private String link;
	private String date;
	private String description;
	private String ImageLink;
	
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getDate() 
	{
		return date;
	}
	public void setDate(String date) 
	{
		this.date = date;
	}
	public String getLink() 
	{
		return link;
	}
	public void setLink(String link) 
	{
		this.link = link;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getImageLink() {
		return ImageLink;
	}
	public void setImageLink(String imageLink) {
		ImageLink = imageLink;
	}
}
