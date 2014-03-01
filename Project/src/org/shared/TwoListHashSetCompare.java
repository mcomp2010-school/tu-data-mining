package org.shared;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

// TODO: Auto-generated Javadoc
/**
 * The Class TwoListHashSetCompare.
 *
 * @param <E> the element type
 */
public class TwoListHashSetCompare <E> {
	
	/** The Hash set left. */
	private HashSet<E> HashSetLeft= new HashSet<E>();
	
	/** The Hash set right. */
	private HashSet<E> HashSetRight= new HashSet<E>();
	
	
	
	/**
	 * Sets the hash set left.
	 *
	 * @param hashSetLeft the new hash set left
	 */
	public void setHashSetLeft(HashSet<E> hashSetLeft) {
		HashSetLeft = hashSetLeft;
	}

	/**
	 * Sets the hash set right.
	 *
	 * @param hashSetRight the new hash set right
	 */
	public void setHashSetRight(HashSet<E> hashSetRight) {
		HashSetRight = hashSetRight;
	}

	
	/**
	 * Replace Left HashSet with ArrayList.
	 *
	 * @param ArrayListInput the new left hash set with array list
	 */
	public void setLeftHashSetWithArrayList(ArrayList<E> ArrayListInput){
		HashSetLeft=new HashSet<E>(ArrayListInput);
	}
	

	/**
	 * Replace right HashSet with ArrayList.
	 *
	 * @param ArrayListInput the new right hash set with array list
	 */
	public void setRightHashSetWithArrayList(ArrayList<E> ArrayListInput){
		HashSetRight=new HashSet<E>(ArrayListInput);
	}
	
	/**
	 * Add Item to Left HashSet.
	 *
	 * @param Item the item
	 */
	public void addItemToLeft(E Item){
		HashSetLeft.add(Item);
	}
	
	/**
	 * Add Item to Right HashSet.
	 *
	 * @param Item the item
	 */
	public void addItemToRight(E Item){
		HashSetRight.add(Item);
	}
	
	/**
	 * Remove Item from Left.
	 *
	 * @param Item the item
	 */
	public void removeItemFromLeft(E Item){
		HashSetLeft.remove(Item);
	}
	
	/**
	 * Remove Item from Right.
	 *
	 * @param Item the item
	 */
	public void removeItemFromRight(E Item){
		HashSetRight.remove(Item);
	}
	
	/**
	 * Get what is common in both Sets.
	 *
	 * @return the intersection from both
	 */
	public ArrayList<E> getIntersectionFromBoth(){
		ArrayList<E> temp= new ArrayList<E>();
		
		Iterator<E> itr = HashSetLeft.iterator();
		
		while(itr.hasNext()){
			E current=itr.next();
			
			if(HashSetRight.contains(current)){
				temp.add(current);
			}
		}
		
		return temp;
	}
	
	/**
	 * Get what is unique in Left.
	 *
	 * @return the left relativecomplement
	 */
	public ArrayList<E> getLeftRelativecomplement(){
		ArrayList<E> temp= new ArrayList<E>();
		
		Iterator<E> itr = HashSetLeft.iterator();
		
		while(itr.hasNext()){
			E current=itr.next();
			
			if(!HashSetRight.contains(current)){
				temp.add(current);
			}
		}
		
		return temp;
	}
	
	/**
	 * Get what is unique in Right.
	 *
	 * @return the right relativecomplement
	 */
	public ArrayList<E> getRightRelativecomplement(){
		ArrayList<E> temp= new ArrayList<E>();
		
		Iterator<E> itr = HashSetRight.iterator();
		
		while(itr.hasNext()){
			E current=itr.next();
			
			if(!HashSetLeft.contains(current)){
				temp.add(current);
			}
		}
		
		return temp;
	}
	
	
}
