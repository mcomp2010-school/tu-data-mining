package org.main;

import java.util.Scanner;

import org.database.DataView;
import org.database.DatabaseFactory;
import org.enums.DatabaseEngineEnum;
import org.rss.GoogleRssCollection;
import org.rss.RssOutputer;

// TODO: Auto-generated Javadoc
/**
 * The Class Watcher.
 */
public class Watcher {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		Scanner newa= new Scanner(System.in);
		
		System.out.println("type in Y to start process");
		String cur = newa.nextLine();
		
		if(cur.trim().equalsIgnoreCase("y")){
			Watcher WatcherObj=new Watcher();
			WatcherObj.loop();
		}
		
	}
	
	
	/**
	 * Loop.
	 *
	 * @throws Exception the exception
	 */
	public void loop() throws Exception{
		System.out.println("Lookup");
		
		GoogleRssCollection GoogleWorld= new GoogleRssCollection("https://news.google.com/news/feeds?pz=1&cf=all&ned=us&hl=en&topic=w&output=rss");
		RssOutputer.output(GoogleWorld.getNewFeedsStringTable());
		
		GoogleRssCollection GoogleUS= new GoogleRssCollection("https://news.google.com/news/feeds?pz=1&cf=all&ned=us&hl=en&topic=n&output=rss");
		RssOutputer.output(GoogleUS.getNewFeedsStringTable());
		
		GoogleRssCollection GoogleBusiness= new GoogleRssCollection("https://news.google.com/news/feeds?pz=1&cf=all&ned=us&hl=en&topic=b&output=rss");
		RssOutputer.output(GoogleBusiness.getNewFeedsStringTable());
		
		GoogleRssCollection GoogleTech= new GoogleRssCollection("http://news.google.com/news?pz=1&cf=all&ned=us&hl=en&topic=tc&output=rss");
		RssOutputer.output(GoogleTech.getNewFeedsStringTable());
		
		GoogleRssCollection GoogleEntertainment= new GoogleRssCollection("https://news.google.com/news/feeds?pz=1&cf=all&ned=us&hl=en&topic=e&output=rss");
		RssOutputer.output(GoogleEntertainment.getNewFeedsStringTable());
		
		GoogleRssCollection GoogleSport= new GoogleRssCollection("https://news.google.com/news/feeds?pz=1&cf=all&ned=us&hl=en&topic=s&output=rss");
		RssOutputer.output(GoogleSport.getNewFeedsStringTable());
		
		GoogleRssCollection GoogleScience= new GoogleRssCollection("https://news.google.com/news/feeds?pz=1&cf=all&ned=us&hl=en&topic=snc&output=rss");
		RssOutputer.output(GoogleScience.getNewFeedsStringTable());
		
		GoogleRssCollection GoogleHealth= new GoogleRssCollection("https://news.google.com/news/feeds?pz=1&cf=all&ned=us&hl=en&topic=m&output=rss");
		RssOutputer.output(GoogleHealth.getNewFeedsStringTable());
		
		
		delay(60000);
		loop();
	}
	
	
	/**
	 * Delay.
	 *
	 * @param Time the time
	 */
	public void delay(int Time){
		 try
		  {
		  Thread.sleep(Time);  
		 
		  }catch (InterruptedException ie)
		  {
		  System.out.println(ie.getMessage());
		  }
	}
	

}
