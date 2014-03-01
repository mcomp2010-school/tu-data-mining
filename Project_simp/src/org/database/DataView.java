package org.database;

import java.util.logging.Level;
import org.shared.StringTable;
import org.shared.performance.Timing;

// TODO: Auto-generated Javadoc
/**
 * The Class DataView.
 */
public abstract class DataView {
	//Sqlite Database File
	/** The Database file path. */
	protected String DatabaseFilePath="data\\data.db";
	
	//MySql Database Settings
	/** The url. */
	protected String url = "jdbc:mysql://192.168.2.16:3307/rssFeeds";
	
	/** The user. */
	protected String user = "rss";
	
	/** The password. */
	protected String password = "pass";
	
	//For Performance and Logging
	/** The Stop watch performance. */
	protected boolean StopWatchPerformance=false;
	
	/** The Log level. */
	protected Level LogLevel=Level.WARNING;
	
	//SQL Statements used to create database tables
	/** The Sql_ history_ table. */
	protected String rss_entries=("CREATE  TABLE IF NOT EXISTS rss_entries (idrss_entries INT NOT NULL ,  title VARCHAR(300) NULL , link VARCHAR(900) NULL ,  author VARCHAR(100) NULL ,  public_date VARCHAR(45) NULL ,  description TEXT NULL , provider VARCHAR(45) NULL , number_of_articles INT NULL , category VARCHAR(45) NULL , PRIMARY KEY (idrss_entries) )");
	
	
	//Methods for subclasses
	/**
	 * Generic insert.
	 *
	 * @param TableName the table name
	 * @param inDetailedTable the in detailed table
	 */
	protected abstract void genericInsert(String TableName, StringTable inDetailedTable);
	
	/**
	 * Generic select.
	 *
	 * @param string the string
	 * @return the string table
	 */
	protected abstract StringTable genericSelect(String string);
	
	/**
	 * Generic execute.
	 *
	 * @param string the string
	 */
	protected abstract void genericExecute(String string);

	/**
	 * Adds the rss.
	 *
	 * @param inHistoryTable the in history table
	 */
	public void addRss(StringTable inHistoryTable) {
		Timing StopWatch1=new Timing();
		StopWatch1.start();
		// TODO Auto-generated method stub
		this.genericInsert("rss_entries", inHistoryTable);
		if(StopWatchPerformance)System.out.println(this.getClass().getName()+"\tsetHistoryTable()\t"+StopWatch1.sec_ms(StopWatch1.stop_SecDouble()));
	}
		
	/**
	 * Gets the rSS feeds.
	 *
	 * @return the rSS feeds
	 */
	public StringTable getRSSFeeds() {
		Timing StopWatch1=new Timing();
		StopWatch1.start();
		// TODO Auto-generated method stub
		StringTable Temp= genericSelect("SELECT idrss_entries,title,public_date,category,provider,number_of_articles FROM rss_entries ORDER BY idrss_entries");
		//if(StopWatchPerformance)System.out.println(this.getClass().getName()+"\tgetHistoryTableWhere()\t"+StopWatch1.Min_Sec_ms(StopWatch1.stop_Double()));
		return Temp;
	}
	
	/**
	 * Gets the rSS feeds category.
	 *
	 * @param input the input
	 * @return the rSS feeds category
	 */
	public StringTable getRSSFeedsCategory(String input) {
		Timing StopWatch1=new Timing();
		StopWatch1.start();
		// TODO Auto-generated method stub
		StringTable Temp= genericSelect("SELECT idrss_entries,title,public_date,category,provider,number_of_articles FROM rss_entries Where Category like \'"+input+"\' ORDER BY idrss_entries");
		//if(StopWatchPerformance)System.out.println(this.getClass().getName()+"\tgetHistoryTableWhere()\t"+StopWatch1.Min_Sec_ms(StopWatch1.stop_Double()));
		return Temp;
	}
	
	/**
	 * Gets the rSS feeds title.
	 *
	 * @return the rSS feeds title
	 */
	public StringTable getRSSFeedsTitle() {
		Timing StopWatch1=new Timing();
		StopWatch1.start();
		// TODO Auto-generated method stub
		StringTable Temp= genericSelect("SELECT title FROM rss_entries ORDER BY idrss_entries");
		//if(StopWatchPerformance)System.out.println(this.getClass().getName()+"\tgetHistoryTableWhere()\t"+StopWatch1.Min_Sec_ms(StopWatch1.stop_Double()));
		return Temp;
	}
		

	


}//end class
