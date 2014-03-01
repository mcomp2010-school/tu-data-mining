package org.dict;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class HashLoader {

	private TreeSet<String> lines= new TreeSet<String>();
	
	public HashLoader(String strDirInput){
		List<File> files = (List<File>) FileUtils.listFilesAndDirs(new File(strDirInput), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			if(file.isFile()){
				//System.out.println("English_Dictionaries\\" + file.getName());	
				try {
					
					List<String> temp=FileUtils.readLines(new File(strDirInput+"\\" + file.getName()));
					
					for (int i = 0; i < temp.size(); i++) {
						lines.add(temp.get(i).toLowerCase().trim());
					}		
							
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		//Util.writeTreeToFile(lines,"Dictionaries_English\\all.txt");
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		HashLoader dictObj=new HashLoader("Dictionaries\\English");
		
		
	
		
		
		System.out.println(dictObj.contains("rivera"));
		
	}

	public boolean contains(Object o) {
		return lines.contains(o);
	}


}
