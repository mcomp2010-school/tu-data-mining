package org.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;


// TODO: Auto-generated Javadoc
/**
 * The Class Testa.
 */
public class SplitStringDemo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Test="";
		Test="\'Brain pacemaker\' world! Year's D.C. Hello Hi (manny rivera hello) \"indeed\'s man\" why";
		Test="Elway: Dumervil\'s salary is \"out of whack, hopefully he realizes that\"";
		Test="Pierre Vinken , 61 years old , will join the South America, spain,  board as a nonexecutive 1 million director Nov. 29. Manny Rivera";
		
		ArrayList<String> temp= Util.splitStringToWords(Test);
		

		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));
		}

		
	}

	


}
