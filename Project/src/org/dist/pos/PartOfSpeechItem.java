package org.dist.pos;

import java.util.HashMap;

public class PartOfSpeechItem {

	
	HashMap<String,String> POSTag=new HashMap<String,String>();
	
	public PartOfSpeechItem(){
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
	}

	

}
