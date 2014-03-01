package org.rss;

import org.database.DataView;
import org.database.DatabaseFactory;
import org.enums.DatabaseEngineEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Demo.
 */
public class Demo {

	/** The Data conn. */
	static DatabaseFactory DataConn = new DatabaseFactory(DatabaseEngineEnum.MYSQLSINGLE);
	
	/** The Current conn. */
	static DataView CurrentConn= DataConn.data;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		GoogleRssCollection GoogleWorld= new GoogleRssCollection("https://news.google.com/news/feeds?pz=1&cf=all&ned=us&hl=en&topic=w&output=rss");
		//GoogleWorld.getFeeds();
		//System.out.println(GoogleWorld.getHashes());
		System.out.println(GoogleWorld.getNewFeedsStringTable());
		
		System.out.println(System.getProperty("file.separator"));
		
		//CurrentConn.addRss(GoogleWorld.getNewFeedsStringTable());
		
		//RssOutputer.output(GoogleWorld.getNewFeeds());
		
		System.out.println("-----------------------------");
		
		GoogleRssCollection GoogleTech= new GoogleRssCollection("http://news.google.com/news?pz=1&cf=all&ned=us&hl=en&topic=tc&output=rss");
		//GoogleTech.getFeeds();
		//System.out.println(GoogleTech);
		
		//RssOutputer.output(GoogleTech.getNewFeeds());
	}

}
