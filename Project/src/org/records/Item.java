package org.records;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public class Item {
	
	/** The first field. */
	private String firstField;
	
	/** The word. */
	private String word;
	
	/** The count. */
	private Integer count;
	
	
	
	/**
	 * Instantiates a new item.
	 *
	 * @param firstField the first field
	 * @param word the word
	 * @param count the count
	 */
	public Item(String firstField, String word, Integer count) {
		super();
		this.firstField = firstField;
		this.word = word;
		this.count = count;
	}
	
	/**
	 * Gets the first field.
	 *
	 * @return the first field
	 */
	public String getFirstField() {
		return firstField;
	}
	
	/**
	 * Sets the first field.
	 *
	 * @param firstField the new first field
	 */
	public void setFirstField(String firstField) {
		this.firstField = firstField;
	}
	
	/**
	 * Gets the word.
	 *
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Sets the word.
	 *
	 * @param word the new word
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	
	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + firstField + "\t" + word + "\t"
				+ count + "";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result
				+ ((firstField == null) ? 0 : firstField.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		Item other = (Item) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (firstField == null) {
			if (other.firstField != null)
				return false;
		} else if (!firstField.equals(other.firstField))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	

}
