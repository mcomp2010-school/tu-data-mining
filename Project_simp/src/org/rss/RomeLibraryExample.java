package org.rss;

import java.net.HttpURLConnection;  
import java.net.URL;  
import java.util.Iterator;  
import java.util.List;  

import org.apache.commons.lang3.StringUtils;
  
import com.sun.syndication.feed.synd.SyndEntry;  
import com.sun.syndication.feed.synd.SyndFeed;  
import com.sun.syndication.io.SyndFeedInput;  
import com.sun.syndication.io.XmlReader;  
  
// TODO: Auto-generated Javadoc
/**
 * The Class RomeLibraryExample.
 */
public class RomeLibraryExample {  
    
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {  
        URL url = new URL("http://news.google.com/news?pz=1&cf=all&ned=us&hl=en&topic=e&output=rss");  
        
        HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();  
        // Reading the feed
        SyndFeedInput input = new SyndFeedInput();  
        SyndFeed feed = input.build(new XmlReader(httpcon));  
        List entries = feed.getEntries();  
        Iterator itEntries = entries.iterator();  
  
        while (itEntries.hasNext()) {  
            SyndEntry entry = (SyndEntry) itEntries.next();  
            
            GoogleRssEntry Entry = new GoogleRssEntry(entry.getTitle(),entry.getLink(),entry.getAuthor(),entry.getPublishedDate().toString(), entry.getDescription().getValue(),entry.getCategories().get(0).toString().trim());
           
            System.out.println("================"); 
            System.out.println(Entry);            
            System.out.println("================");
            System.out.println();  
        }  
    }  
}  