package org.shared;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 *
 * @author Emanuel Rivera
 */

public class Util {

	public static void writeTreeToFile(TreeSet<String> emailTree,
			String destFile) {
		try {
			FileWriter fw = new FileWriter(new File(destFile));
			BufferedWriter bw = new BufferedWriter(fw);
			for (String email : emailTree) {
				bw.write(email + "\n");
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints the sep line.
	 */
	public void PrintSepLine() {
		System.out.println("===========================");
	}// end PrintSepLine()
	

	
	/**
	 * Split string to words.
	 *
	 * @param strInput the str input
	 * @return the array list
	 */
	public static ArrayList<String> splitStringToWords(String strInput) {
        ArrayList<String> ArrCurrent = new ArrayList<String>();
        
        StringBuilder TempAppend= new StringBuilder();
       
        HashSet<String> Open=new HashSet<String>();
        Open.add("(");
        Open.add("\"");
        Open.add("\'");
        
        HashSet<String> Close=new HashSet<String>();
        Close.add(")");
        Close.add("\"");
        Close.add("\'");
        
        boolean Inside=false;
        int count=0;
        String opening="";
        
        
        for(int i=0;i<strInput.length();i++){
        	
        	String previousLetter="";
        	if(i>=1)previousLetter=strInput.substring(i-1, i);
        	
        	String currentLetter=strInput.substring(i, i+1);
        	
        	if(Open.contains(currentLetter)&&Inside==false&&(previousLetter.equals(" ")||i==0)){
        		
        		
        		Inside=true;
        		count=i;
        		if(Open.contains(currentLetter)&&Close.contains(currentLetter)){
        			opening=currentLetter;	
        		}
        	}
        	
        	if(currentLetter.equals(" ")&&Inside==false){
        		TempAppend.append("*");
        		//System.out.println("*"+"\t>"+Inside+"\t>*"+opening+"*");
        	}else{
        		TempAppend.append(currentLetter);
        		
        		
        		//System.out.println(currentLetter+"\t>"+Inside+"\t>*"+opening+"*");
        	}
        
        	
        	if(!currentLetter.equals(opening)&&opening.length()>=1&&Close.contains(currentLetter)){
    			Inside=true;
    			//System.out.println(">>>>>>>>>>>");
    		}else if(Close.contains(currentLetter)){
        		if(count==i){
        			//this prevents " 
        		}else{	
            			Inside=false;
            			opening="";
        		}	
        	}
        }
        
        strInput=TempAppend.toString();
        
        
        StringTokenizer stringtokenizer = new StringTokenizer(strInput, "*");
        while (stringtokenizer.hasMoreElements()) {
                String strCurrentToken=stringtokenizer.nextToken().trim();
               
                if(strCurrentToken.trim().length()<=0){
                        //Ignore Spaces and 1 char worlds
                }else{
                	if(StringUtils.isBlank(strCurrentToken)){
                		//Ignore numeric and non-ascii
                	}else if(!StringUtils.isAsciiPrintable(strCurrentToken)){
                		//ignore non-ascii
                	}else if(StringUtils.isAllUpperCase(strCurrentToken)){
                    		ArrCurrent.add(strCurrentToken.trim());
                	}else{
                		
                		if(strCurrentToken.trim().length()>=2){
                			String Lastchr=StringUtils.substring(strCurrentToken, strCurrentToken.length()-1, strCurrentToken.length());
                    		if(StringUtils.isAlphanumeric(Lastchr)==false){
                    			strCurrentToken=StringUtils.substring(strCurrentToken, 0, strCurrentToken.length()-1);
                    		}
                    			
                       		String FirstChar=StringUtils.substring(strCurrentToken, 0, 1);
                    		if(StringUtils.isAlphanumeric(FirstChar)==false){
                    			strCurrentToken=StringUtils.substring(strCurrentToken, 1, strCurrentToken.length());
                    		}
                		}
                			
                		
                		
                		
                		//System.out.println(">"+Lastchr+"<"+StringUtils.isAlphanumeric(Lastchr));
                		
                		
                		ArrCurrent.add(StringUtils.capitalize(strCurrentToken).trim());
                	}
                        
                }                      
        }
        return ArrCurrent;
    }
    
	


	
	/**
	 * Used to get the current system time and convert it to formatted date.
	 *
	 * @return the current formatted date
	 */
	public String getCurrentFormattedDate(){
		java.util.Date Curdate= new java.util.Date();
		
		String strDate = new Timestamp(Curdate.getTime())+"";
		
		try {
		    SimpleDateFormat DATESource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date date = DATESource.parse(strDate);
		    SimpleDateFormat DATEFinish = new SimpleDateFormat("MMM d,yyyy hh:mm:ss aa");
		    strDate = DATEFinish.format(date);
		    // System.out.println("Converted date is : " + strDate);

		} catch (Exception e) {
		    // System.out.println("Parse Exception : " + pe);
		    return "*ERROR*";
		}
		
		return strDate;
	}
	
	/**
	 * Used to format Date from 24-hour to 12-hour format.
	 *
	 * @param input the input
	 * @return the string
	 */
    public String formatDate(String input) {
	String strDate = input;
	try {
	    SimpleDateFormat DATESource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = DATESource.parse(strDate);
	    SimpleDateFormat DATEFinish = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
	    strDate = DATEFinish.format(date);
	    // System.out.println("Converted date is : " + strDate);

	} catch (Exception e) {
	    // System.out.println("Parse Exception : " + pe);
	    return "*ERROR*";
	}

	return strDate;
    }
	
    /**
     * Sorting of ArrayList with 1 column.
     *
     * @param ArrInput the arr input
     */
	public void qsortArrayList(ArrayList<String> ArrInput) {
		quicksortArrayList(ArrInput, 0, ArrInput.size() - 1);
	}//end qsort

	/**
	 * Supporting method for Sorting.
	 *
	 * @param list the list
	 * @param p the p
	 * @param r the r
	 */
	private void quicksortArrayList(ArrayList<String> list, int p, int r) {
		if (p < r) {
			int q = partitionArrayList(list, p, r);
			if (q == r) {
				q--;
			}
			quicksortArrayList(list, p, q);
			quicksortArrayList(list, q + 1, r);
		}
	}//end quicksort

	/**
	 * Supporting method for Sorting.
	 *
	 * @param list the list
	 * @param p the p
	 * @param r the r
	 * @return the int
	 */
	private int partitionArrayList(ArrayList<String> list, int p, int r) {
		String pivot = list.get(p);
		int lo = p;
		int hi = r;

		while (true) {
			while (list.get(hi).compareTo(pivot) >= 0 && lo < hi) {
				hi--;
			}
			while (list.get(lo).compareTo(pivot) < 0 && lo < hi) {
				lo++;
			}
			if (lo < hi) {
				String T = list.get(lo);
				list.set(lo, list.get(hi));
				list.set(hi, T);
				
			} else
				return hi;
		}
	}//end partition
	
	/**
	 * Apply unique key.
	 *
	 * @param a1 the a1
	 * @return the fast table
	 */
	public ArrayList<String> applyUniqueKey(ArrayList<String> a1) {
		ArrayList<String> a2 = new ArrayList<String>();
		for (int i = 0; i < a1.size(); i++) {
			if (!a2.contains(a1.get(i))) {
				a2.add(a1.get(i));
			}
		}
		return a2;
	}
	
	/**
	 * Used to Split string with a Delimiter.
	 *
	 * @param strInput the str input
	 * @param strDelimeter the str delimeter
	 * @return the fast table
	 */
	public ArrayList<String> OccurrenceSplit(String strInput,String strDelimeter){
		ArrayList<String> ArrCurrent= new ArrayList<String>();
		if(strInput.trim().length()==0){//if nothing 
			return ArrCurrent;
		}
						
		if(strDelimeter.trim().length()==0||strInput.indexOf(strDelimeter)<=0){
			ArrCurrent.add(strInput);
		}
		
		return ArrCurrent;
	}
	
	
	/**
	 * Insert txt template.
	 *
	 * @param strTemplateFile the str template file
	 * @param strFind the str find
	 * @param strReplaceBy the str replace by
	 * @return the string
	 */
	public String insertTxtTemplate(String strTemplateFile ,String strFind, String strReplaceBy){
		String TemplateString=this.filetoString(strTemplateFile);		
		TemplateString=TemplateString.replace(strFind, strReplaceBy);
		return TemplateString;
	}
	
	/**
	 * Used to replace text.
	 *
	 * @param strSource the str source
	 * @param strFind the str find
	 * @param strReplaceBy the str replace by
	 * @return the string
	 */
	public String insertStringTemplate(String strSource ,String strFind, String strReplaceBy){
		String TemplateString=strSource.replace(strFind, strReplaceBy);
		return TemplateString;
	}
	
	/**
	 * Used to see if string is a integer.
	 *
	 * @param Input the input
	 * @return true, if successful
	 */
	public boolean IsNum(String Input) {
		boolean isnum = true;

		try {
			Integer.parseInt(Input);
		} catch (Exception e) {
			isnum = false;
		}

		return isnum;
	}
	
	/**
	 * used to Convert data in file to 1 columned ArrayList.
	 *
	 * @param StrFile the str file
	 * @return the fast table
	 */
	public ArrayList<String> fileto1dArrayList(String StrFile){
		return fileto1dArrayList(StrFile,true);
	}
	
	/**
	 * used to Convert data in file to 1 columned ArrayList.
	 *
	 * @param StrFile the str file
	 * @param blnIncludeBlankLines the bln include blank lines
	 * @return the fast table
	 */
	
	public ArrayList<String> fileto1dArrayList(String StrFile,boolean blnIncludeBlankLines){
		ArrayList<String> ArrLLog = new ArrayList<String>();		
		
		try {// Main
			ArrLLog.clear();// Clear ArrayList
			String StrFILENAME = StrFile;
			FileInputStream fstream = new FileInputStream(StrFILENAME);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
		
				if(strLine.trim().length()>=0&&blnIncludeBlankLines==true){
					ArrLLog.add(strLine.trim());
				}else if (strLine.trim().length()>=1&&blnIncludeBlankLines==false){
					ArrLLog.add(strLine.trim());
				}

			}
			in.close(); // Close the input stream
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}// End Main
		return ArrLLog;
	}//end fileto1dArrayList()
	
	/**
	 * used to Convert data in file to string.
	 *
	 * @param StrFile the str file
	 * @return the string
	 */
	
	public String filetoString(String StrFile){
		String Output="";
		try {// Main
			String StrFILENAME = StrFile;
			FileInputStream fstream = new FileInputStream(StrFILENAME);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				Output+=(strLine.trim())+"\n";
			}
			in.close(); // Close the input stream
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}// End Main
		return Output;
	}//end fileto1dArrayList()
	
	
	/**
	 * Checks if folder exist and not exist make new folder.
	 *
	 * @param StringInFolder the string in folder
	 * @return true, if successful
	 */
	public static boolean folderExistAndMake(String StringInFolder){
		File CurrentFolder=new File(StringInFolder);
		
		boolean exists = CurrentFolder.exists();
		if(exists==false){
			CurrentFolder.mkdirs();
		}
		return exists;
	}
	
	/**
	 * Checks if folder exists.
	 *
	 * @param StringInFolder the string in folder
	 * @return true, if successful
	 */
	public boolean folderExist(String StringInFolder){
		File CurrentFolder=new File(StringInFolder);
		boolean exists = CurrentFolder.exists();
		return exists;
	}
	
	/**
	 * Checks if file exist.
	 *
	 * @param StringInFile the string in file
	 * @return true, if successful
	 */
	public boolean fileExist(String StringInFile){
		File CurrentFolder=new File(StringInFile);
		boolean exists = CurrentFolder.isFile();
		return exists;
	}
	
	/**
	 * Used to delete a folder.
	 *
	 * @param StringInFolder the string in folder
	 */
	public void folderDelete(String StringInFolder){
		try {
			FileUtils.deleteDirectory(new File(StringInFolder));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used to copy a folder.
	 *
	 * @param source the source
	 * @param destination the destination
	 */
	public void folderCopy(String source, String destination){
        File srcDir = new File(source);
        File destDir = new File(destination);
        
        try {
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * Write string to file.
	 *
	 * @param Results the results
	 * @param Path the path
	 */
	public void writeStringToFile(String Results,String Path){
		writeStringToFile(Results,Path,false);
	}//end void

	
	/**
	 * Write string to file.
	 *
	 * @param Results the results
	 * @param Path the path
	 * @param append the append
	 */
	public void writeStringToFile(String Results,String Path,boolean append){
	
		try {
			FileWriter fstream = new FileWriter(Path, append);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(Results);
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}//end void
	

	/**
	 * Fast table to string.
	 *
	 * @param ArrInput the arr input
	 * @return the string
	 */
	public String ArrayListToString(ArrayList<String> ArrInput){
		return ArrayListToString(ArrInput,true);
	}
	
	/**
	 * Fast table to string.
	 *
	 * @param ArrInput the arr input
	 * @param blnIncludeBlankLines the bln include blank lines
	 * @return the string
	 */
	public String ArrayListToString(ArrayList<String> ArrInput,boolean blnIncludeBlankLines){
		String Output="";
		for(int i=0;i<ArrInput.size();i++){
			
			if(ArrInput.get(i).trim().length()>=0&&blnIncludeBlankLines==true){
				Output+=(ArrInput.get(i))+"\n";
			}else if (ArrInput.get(i).trim().length()>=1&&blnIncludeBlankLines==false){
				Output+=(ArrInput.get(i))+"\n";
			}
		}
		return Output;	
	}//end String

}//End Class
