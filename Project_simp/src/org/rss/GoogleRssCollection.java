package org.rss;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.shared.StringTable;
import org.shared.TwoListHashSetCompare;
import org.shared.Util;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

// TODO: Auto-generated Javadoc
/**
 * The Class GoogleRssCollection.
 */
public class GoogleRssCollection {
	
	/** The Al entry. */
	private ArrayList<GoogleRssEntry> newFeeds = new ArrayList<GoogleRssEntry>();
	
	/** The url. */
	private URL url;  
	 
	/** The Site. */
	private String Site;
	
	/** The Utils. */
	private Util Utils=new Util();
	
	
	/**
	 * Instantiates a new google rss collection.
	 *
	 * @param url the url
	 */
	public GoogleRssCollection(String url) {
		super();
		try {
			this.url = new URL(url);
			getFeeds();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	/**
	 * Gets the new feeds string table.
	 *
	 * @return the new feeds string table
	 * @throws Exception the exception
	 */
	public StringTable getNewFeedsStringTable() throws Exception{
		
		ArrayList<GoogleRssEntry> newFeeds=this.getNewFeeds();
		
		StringTable Cur=new StringTable("title;;;link;;;author;;;public_date;;;description;;;provider;;;number_of_articles;;;category",";;;");
		
		for (int j = 0; j < newFeeds.size(); j++) {
			
			String[] temp= new String[]{newFeeds.get(j).getTitle(),
					newFeeds.get(j).getLink(),
					newFeeds.get(j).getAuthor(),
					newFeeds.get(j).getPublic_Date(),
					newFeeds.get(j).getDescription(),
					newFeeds.get(j).getProvider(),
					newFeeds.get(j).getNumber_of_Article(),
					newFeeds.get(j).getCategory()};

			Cur.insertStringArray(temp);
		}
		
		
		return Cur;
	}
	
	/**
	 * Gets the new feeds.
	 *
	 * @return the new feeds
	 * @throws Exception the exception
	 */
	public ArrayList<GoogleRssEntry> getNewFeeds() throws Exception{
		
		this.newFeeds.clear();
		Utils.folderExistAndMake("history");
		
		ArrayList<GoogleRssEntry> AlCur = new ArrayList<GoogleRssEntry>();
		
		TwoListHashSetCompare<String> HsCur= new TwoListHashSetCompare<String>();
		
		String Sep=System.getProperty("file.separator");
		HsCur.setLeftHashSetWithArrayList(Utils.fileto1dArrayList("."+Sep+"history"+Sep+"history.txt"));
		this.getFeeds();
		HsCur.setHashSetRight(this.getHashes());
		
		Utils.writeStringToFile(Utils.ArrayListToString(HsCur.getRightRelativecomplement()), "."+Sep+"history"+Sep+"history.txt",true);
		
		ArrayList<String> NewAr=HsCur.getRightRelativecomplement();
		
		//System.out.println();
		
		for (int i = 0; i < NewAr.size(); i++) {
			for (int j = 0; j < newFeeds.size(); j++) {
				if(NewAr.get(i).equals(this.newFeeds.get(j).getMD5Titlehash()))
				//System.out.println(">>"+AlEntry.get(j).getTitle());
				AlCur.add(newFeeds.get(j));
			}
		}
		return AlCur;
	}
	
	/**
	 * Gets the feeds.
	 *
	 * @return the feeds
	 * @throws Exception the exception
	 */
	public ArrayList<GoogleRssEntry> getFeeds() throws Exception{
		this.newFeeds.clear();
		
        HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();  
        // Reading the feed
        SyndFeedInput input = new SyndFeedInput();  
        SyndFeed feed = input.build(new XmlReader(httpcon));  
        List entries = feed.getEntries();  
        Iterator itEntries = entries.iterator();  
  
        while (itEntries.hasNext()) {  
            SyndEntry entry = (SyndEntry) itEntries.next();  
            
            GoogleRssEntry Entry = new GoogleRssEntry(entry.getTitle(),entry.getLink(),entry.getAuthor(),entry.getPublishedDate().toString(), entry.getDescription().getValue(),entry.getCategories().get(0).toString().trim());
           
            newFeeds.add(Entry);
            //System.out.println("================"); 
            //System.out.println(Entry);            
            //System.out.println("================");
            //System.out.println();  
        } 
        
        return this.newFeeds;
	}
	

	/**
	 * Gets the site.
	 *
	 * @return the site
	 */
	public String getSite() {
		return Site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site the new site
	 */
	public void setSite(String site) {
		Site = site;
	}

	/**
	 * Gets the hashes.
	 *
	 * @return the hashes
	 */
	public HashSet<String> getHashes() {
		HashSet<String> outputBuilder= new HashSet<String>();
		
		for (int i = 0; i < this.newFeeds.size(); i++) {
			outputBuilder.add(this.newFeeds.get(i).getMD5Titlehash());
		}
		
		return outputBuilder;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder outputBuilder= new StringBuilder();
		
		for (int i = 0; i < this.newFeeds.size(); i++) {
			outputBuilder.append("-"+this.newFeeds.get(i).getTitle()+"\n");
		}
		
		return outputBuilder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((newFeeds == null) ? 0 : newFeeds.hashCode());
		result = prime * result + ((Site == null) ? 0 : Site.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoogleRssCollection other = (GoogleRssCollection) obj;
		if (newFeeds == null) {
			if (other.newFeeds != null)
				return false;
		} else if (!newFeeds.equals(other.newFeeds))
			return false;
		if (Site == null) {
			if (other.Site != null)
				return false;
		} else if (!Site.equals(other.Site))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	

}
