package org.records;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang3.StringUtils;

public class RecordMgr {

	private ArrayList<Item> Items=new ArrayList<Item>();

	public boolean add(Item arg0) {
		return Items.add(arg0);
	}

	public Item get(int arg0) {
		return Items.get(arg0);
	}

	public int size() {
		return Items.size();
	}
	
	public boolean isEmpty() {
		return Items.isEmpty();
	}

	public Iterator<Item> iterator() {
		return Items.iterator();
	}

	public void sort(){
		ComparatorChain chain = new ComparatorChain();
		
		chain.addComparator(new Comparator<Item>() {
			public int compare(Item e1, Item e2) {
				int results = e1.getFirstField().compareTo(e2.getFirstField());
				return results;
			}
		});

		chain.addComparator(new Comparator<Item>() {
			public int compare(Item e1, Item e2) {
				int results = e2.getCount().compareTo(e1.getCount());
				return results;
			}
		});

		chain.addComparator(new Comparator<Item>() {
			public int compare(Item e1, Item e2) {
				int results = e1.getWord().compareTo(e1.getWord());
				return results;
			}
		});

		Collections.sort(this.Items, chain);
	}
	
	public String printFreqWords(){
		StringBuilder output=new StringBuilder();
		
		for (int i = 0; i < this.Items.size(); i++) {
			Item currentItem=Items.get(i);	
			output.append(StringUtils.repeat(currentItem.getWord(), " ", currentItem.getCount())+"\n");
		}
		
		return output.toString();
	}
	
	
}
