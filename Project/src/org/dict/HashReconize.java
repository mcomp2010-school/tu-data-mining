package org.dict;

import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.dist.pos.PartOfSpeech;

public class HashReconize {

	HashLoader dictObj=new HashLoader("Dictionaries\\English");
	HashLoader namesObj=new HashLoader("Dictionaries\\Names");
	PartOfSpeech posObj=new PartOfSpeech();
	
	HashSet<String> Punctuation=new HashSet<String>();
	
	
	public HashReconize(){
		Punctuation.add(".");
		Punctuation.add(",");
		Punctuation.add(":");
		Punctuation.add(";");
		Punctuation.add("-");
		Punctuation.add("_");
		Punctuation.add("^");
		Punctuation.add("#");
		Punctuation.add("!");
		Punctuation.add("@");
		Punctuation.add("\"");
		Punctuation.add("(");
		Punctuation.add(")");
		Punctuation.add("*");
		Punctuation.add("?");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashReconize reconObj=new HashReconize();
		
		System.out.println(reconObj.reconizeWord("ken"));
		System.out.println(reconObj.reconizeWord("Pierre"));
		System.out.println(reconObj.reconizeWord("154"));
		System.out.println(reconObj.reconizeWord("South"));
		System.out.println(reconObj.reconizeWord("the"));
		System.out.println(reconObj.reconizeWord("1st"));
	
	}
	
	
	public HashEnum reconizeWord(String input){
		input=input.toLowerCase().trim();
		
		boolean foundPos=posObj.containsKey(input);
		boolean foundDict=dictObj.contains(input);
		boolean foundName=namesObj.contains(input);
		
		System.out.println("\n"+input+"\tPOS:"+foundPos+">DICT:"+foundDict+">NAME:"+foundName);
		if((foundPos||foundDict)&&!foundName){
			return HashEnum.WORD;
		}else if(foundName){
			return HashEnum.NAME;
		}else if(foundDict){
			return HashEnum.WORD;
		}else if(StringUtils.isNumeric(input)){
			return HashEnum.NUMBER;
		}else if(this.Punctuation.contains(input)){
			return HashEnum.PUNCTUATION;
		}else{
			return HashEnum.UNKNOWN;
		}
		
	}

}
