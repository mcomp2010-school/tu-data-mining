package org.rss;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RssEntry.
 */
public class GoogleRssEntry {
	
	/** The Title. */
	private String Title;
	
	/** The Link. */
	private String Link;
	
	/** The Author. */
	private String Author;
	
	/** The Public_ date. */
	private String Public_Date;
	
	/** The Description. */
	private String Description;
	
	/** The Provider. */
	private String Provider;
	
	/** The Number_of_ article. */
	private String Number_of_Article;
	
	/** The Category. */
	private String Category;
	
	/**
	 * Gets the number_of_ article.
	 *
	 * @return the number_of_ article
	 */
	public String getNumber_of_Article() {
		return Number_of_Article;
	}

	/**
	 * Sets the number_of_ article.
	 *
	 * @param number_of_Article the new number_of_ article
	 */
	public void setNumber_of_Article(String number_of_Article) {
		Number_of_Article = number_of_Article;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return Category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		Category = category;
	}

	/**
	 * Instantiates a new rss entry.
	 *
	 * @param title the title
	 * @param link the link
	 * @param author the author
	 * @param public_Date the public_ date
	 * @param description the description
	 * @param category the category
	 */
	public GoogleRssEntry(String title, String link, String author,
			String public_Date, String description, String category) {
		super();
		Title = title.substring(0,title.lastIndexOf("-")).trim();
		Provider=title.substring(title.lastIndexOf("-")+1).trim();
		Link = link;
		Author = author;
		Public_Date = public_Date;
		Description = description;
		
	
		
		try{
			this.Number_of_Article=StringUtils.substringBetween(Description, "<b>all "," news articles&nbsp;&raquo;</b>").toString().trim();
		}catch (Exception e) {
			// TODO: handle exception
			this.Number_of_Article="-1";
			//System.out.println(this.Description);
		}
		this.Category=StringUtils.substringAfter(category, "SyndCategoryImpl.name=");
	
	}
	
	/**
	 * Gets the provider.
	 *
	 * @return the provider
	 */
	public String getProvider() {
		return Provider;
	}

	/**
	 * Sets the provider.
	 *
	 * @param provider the new provider
	 */
	public void setProvider(String provider) {
		Provider = provider;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Title\t" + Title + 
				"\nProvider\t" + Provider +
				"\nNumber of Articles\t" + this.Number_of_Article +
				"\nLink\t" +  Link +
				"\nAuthor\t" + Author + 
				"\nPublic_Date\t" + Public_Date + 
				"\nCategory\t" + this.Category + 
				"\nDescription\t"+ Description + "";
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		Title = title;
	}
	
	/**
	 * Gets the link.
	 *
	 * @return the link
	 */
	public String getLink() {
		return Link;
	}
	
	/**
	 * Sets the link.
	 *
	 * @param link the new link
	 */
	public void setLink(String link) {
		Link = link;
	}
	
	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return Author;
	}
	
	/**
	 * Sets the author.
	 *
	 * @param author the new author
	 */
	public void setAuthor(String author) {
		Author = author;
	}
	
	/**
	 * Gets the public_ date.
	 *
	 * @return the public_ date
	 */
	public String getPublic_Date() {
		return Public_Date;
	}
	
	/**
	 * Sets the public_ date.
	 *
	 * @param public_Date the new public_ date
	 */
	public void setPublic_Date(String public_Date) {
		Public_Date = public_Date;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		Description = description;
	}
	
	
	/**
	 * Hash.
	 *
	 * @return the string
	 */
	public String getMD5Titlehash(){
		String plaintext = this.Title;
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		
		return hashtext.toUpperCase();
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Author == null) ? 0 : Author.hashCode());
		result = prime * result
				+ ((Category == null) ? 0 : Category.hashCode());
		result = prime * result
				+ ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + ((Link == null) ? 0 : Link.hashCode());
		result = prime
				* result
				+ ((Number_of_Article == null) ? 0 : Number_of_Article
						.hashCode());
		result = prime * result
				+ ((Provider == null) ? 0 : Provider.hashCode());
		result = prime * result
				+ ((Public_Date == null) ? 0 : Public_Date.hashCode());
		result = prime * result + ((Title == null) ? 0 : Title.hashCode());
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
		GoogleRssEntry other = (GoogleRssEntry) obj;
		if (Author == null) {
			if (other.Author != null)
				return false;
		} else if (!Author.equals(other.Author))
			return false;
		if (Category == null) {
			if (other.Category != null)
				return false;
		} else if (!Category.equals(other.Category))
			return false;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (Link == null) {
			if (other.Link != null)
				return false;
		} else if (!Link.equals(other.Link))
			return false;
		if (Number_of_Article == null) {
			if (other.Number_of_Article != null)
				return false;
		} else if (!Number_of_Article.equals(other.Number_of_Article))
			return false;
		if (Provider == null) {
			if (other.Provider != null)
				return false;
		} else if (!Provider.equals(other.Provider))
			return false;
		if (Public_Date == null) {
			if (other.Public_Date != null)
				return false;
		} else if (!Public_Date.equals(other.Public_Date))
			return false;
		if (Title == null) {
			if (other.Title != null)
				return false;
		} else if (!Title.equals(other.Title))
			return false;
		return true;
	}
	
	

	
	
}
