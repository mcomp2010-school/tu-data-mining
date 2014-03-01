package org.dist.pos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;


public class PartOfSpeech {
	private TreeMap<String,String> worldList=new TreeMap<String,String>();
	private HashMap<String,String> POSTag=new HashMap<String,String>();
	
	public PartOfSpeech(){
		POSTag.put("N","Noun");
		POSTag.put("P","Plural");
		POSTag.put("h","Noun Phrase");
		POSTag.put("V","Verb (usu participle)");
		POSTag.put("t","Verb (transitive)");
		POSTag.put("i","Verb (intransitive)");
		POSTag.put("A","Adjective");
		POSTag.put("v","Adverb");
		POSTag.put("C","Conjunction");
		POSTag.put("P","Preposition");
		POSTag.put("!","Interjection");
		POSTag.put("r","Pronoun");
		POSTag.put("D","Definite Article");
		POSTag.put("I","Indefinite Article");
		POSTag.put("o","Nominative");
		POSTag.put("|","");
		
		try {
			  BufferedReader cfgFile = new BufferedReader(new FileReader(new File("Dictionaries\\part-of-speech.txt")));
			  String line = null;

			  // Read the file line by line
			  while ((line = cfgFile.readLine()) != null) {
			    line.trim();
			    // Ignore empty lines
			    if (!line.equals("")) {
			       String [] fields = line.split("\t"); 
			       String key = fields[0].toLowerCase().trim();
			       String value = fields[1].replace("|", "").trim();
			       
			       String valueTemp="";
			       
			       if(value.length()>=1){
			    	   for(int i=0;i<value.length();i++){
			    		   String CurrentLetter=value.substring(i,i+1);
			    		   valueTemp+=POSTag.get(CurrentLetter)+" ";
			    	   }
			    	   value=valueTemp.trim();
			       }
			       //System.out.println(key+">"+value);
			       worldList.put(key, value);
			    } // if
			  } // while

			  cfgFile.close();
			} catch (IOException e) {
			  System.out.println("Unexpected File IO Error");
			}
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		PartOfSpeech posObj=new PartOfSpeech();
		System.out.println(posObj.get("old"));
		
	}

	public boolean containsKey(Object key) {
		return this.worldList.containsKey(key.toString().toLowerCase());
	}

	public boolean containsValue(Object value) {
		return this.worldList.containsValue(value.toString().toLowerCase());
	}

	public String get(Object key) {
		return this.worldList.get(key);
	}

	public int size() {
		return this.worldList.size();
	}

}
