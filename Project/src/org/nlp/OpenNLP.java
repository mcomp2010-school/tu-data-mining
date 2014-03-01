package org.nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import org.apache.commons.lang3.StringUtils;
import org.dict.HashEnum;
import org.dict.HashReconize;
import org.shared.Util;

public class OpenNLP implements NLP{

	private InputStream modelIn = null;
	TokenNameFinderModel model=null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// TODO Auto-generated method stub
		String Test="";
		Test="\'Brain pacemaker\' world! Year's D.C. Hello Hi (manny rivera hello) \"indeed\'s man\" why";
		Test="Pierre Vinken , 61 years old , will join the South America, spain,  board as a nonexecutive 1 million director Nov. 29 . Manny Rivera";
		//Test="Kerry Smith meets Palestinian Josh Rivera, Israeli leaders after Obama visit";
		
		ArrayList<String> temp= Util.splitStringToWords(Test);
		

		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));
		}

		OpenNLP nlpObj=new OpenNLP();
		
		System.out.println(nlpObj.findNamesAndMergeArrayList(temp));
	}
	
	public OpenNLP(){
		
		try {
			modelIn = new FileInputStream("openNLP-models\\en-ner-person.bin");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			model = new TokenNameFinderModel(modelIn);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
		}	
	}
	
	public ArrayList<String> findNamesAndMergeArrayList(ArrayList<String> input){
		String [] n = input.toArray(new String[input.size()]);
		
		/*
		for (int i = 0; i < n.length; i++) {
			String string = n[i];
			System.out.println(">>>>>"+string);
		}
		*/
				


		NameFinderME nameFinder = new NameFinderME(model);


		Span nameSpans[] = nameFinder.find(n);

		for (int i = 0; i < nameSpans.length; i++) {
			int indexStart=nameSpans[i].getStart();
			int indexEnd=nameSpans[i].getEnd()-1;

			//System.out.println(indexStart+"-"+indexEnd);

			n[indexStart]="<START>"+n[indexStart];
			n[indexEnd]=n[indexEnd]+"<END>";

		}

		//System.out.println(Arrays.toString(n));
		
		ArrayList<String> MergeNames= new ArrayList<String>();
		
		HashReconize reconObj=new HashReconize();
		
		boolean turnOn=false;
		StringBuilder StringAppend=new StringBuilder();
		
		for (int i = 0; i < n.length; i++) {
			String CurrentWord=n[i];
			if(StringUtils.contains(CurrentWord, "<START>"))turnOn=true;
			
			//if(turnOn==true&&reconObj.reconizeWord(CurrentWord)==HashEnum.WORD)turnOn=false;
			
			
			if(turnOn==true){
				CurrentWord=CurrentWord.replace("<START>", "");
				CurrentWord=CurrentWord.replace("<END>", "");	
				StringAppend.append(CurrentWord+" ");
			}
			
			String Out1=CurrentWord.replace("<END>","").trim();
			
			if(!StringUtils.isNumeric(Out1)&&turnOn==false&&Out1.length()>=2){
				System.out.println(Out1+"\t>"+turnOn);	
				MergeNames.add(Out1);
			}
			
			
			if(StringUtils.contains(CurrentWord, "<END>")){
				String Appended=StringAppend.toString();
				System.out.println(Appended.replace("<END>","").trim());
				
				MergeNames.add(Appended.replace("<END>","").trim());
				StringAppend.setLength(0);
				turnOn=false;
			}
			
		}

		return MergeNames;
	}//end sub
	

}
