package org.nlp;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.dict.HashReconize;
import org.shared.Util;

public class HashNLP implements NLP{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.out.println(StringUtils.getLevenshteinDistance("manny", "mandddddddy"));
		
		String Test="";
		Test="\'Brain pacemaker\' world! Year's D.C. Hello Hi (manny rivera hello) \"indeed\'s man\" why";
		Test="Pierre Vinken , 61 years old , will join the South America, spain,  board as a nonexecutive 1 million director Nov. 29 . Manny Rivera";
		//Test="Kerry Smith meets Palestinian Josh Rivera, Israeli leaders after Obama visit";
		
		ArrayList<String> temp= Util.splitStringToWords(Test);
		

		for (int i = 0; i < temp.size(); i++) {
			//System.out.println(temp.get(i));
		}

		NLP nlpObj=new HashNLP();
		
		System.out.println(nlpObj.findNamesAndMergeArrayList(temp));
		
	}

	public ArrayList<String> findNamesAndMergeArrayList(ArrayList<String> input) {
		HashReconize reconObj= new HashReconize();
		
		for (int i = 0; i < input.size(); i++) {
			String Cur=input.get(i);
			System.out.println(Cur+"\t>"+reconObj.reconizeWord(Cur));
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
