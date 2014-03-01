package org.binary;

import java.util.ArrayList;

import org.shared.StringTable;

// TODO: Auto-generated Javadoc
/**
 * The Class Engine.
 */
public class Engine {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		StringTable stHouse= new StringTable(",");
		stHouse.insertfiletoFastTableCL("house-votes-84.csv");
		
		StringTable stHouseSummarized=stHouse.freqReportClassSummarized();
		System.out.println(stHouseSummarized);
		//System.out.println(stHouse.freqReportClass());
		
		System.out.println(jaccardDistant(stHouseSummarized,"Democrat","Republican"));
		
		System.out.println("-------------------");
		//////////////////////////////////////////////////////
		StringTable stfruit= new StringTable(",");
		stfruit.insertfiletoFastTableCL("fruit.txt");
		stfruit.setDelimiter("\t");
		System.out.println(stfruit);
		
		System.out.println(jaccardDistant(stfruit,"Apple","Banana"));
		System.out.println("-------------------");
		///////////////////////
		StringTable stbook= new StringTable(",");
		stbook.insertfiletoFastTableCL("bookpg71.txt");
		stbook.setDelimiter("\t");
		System.out.println(stbook);
		
		System.out.println(jaccardDistant(stbook,"jack","jim"));
		System.out.println(jaccardDistant(stbook,"jack","mary"));
		System.out.println(jaccardDistant(stbook,"jim","mary"));
		
	}
	
	/**
	 * Jaccard distant.
	 *
	 * @param stInput the st input
	 * @param strItem1 the str item1
	 * @param strItem2 the str item2
	 * @return the double
	 */
	public static String jaccardDistant(StringTable stInput,String strItem1,String strItem2){
		double[] Matrix=getMatrixArray(stInput,strItem1,strItem2);
		
		double p=Matrix[0]; //number of variables that positive for both objects
		double q=Matrix[1]; //number of variables that positive for the Item1 th objects and negative for the Item2 th object 
		double r=Matrix[2]; //number of variables that negative for the Item1 th objects and positive for the Item2 th object
		double s=Matrix[3]; //number of variables that negative for both objects
		
		//System.out.println(p+"-"+q+"-"+r+"-"+s);
		
		return "d("+strItem1+","+strItem2+")="+String.format("%1.3f", ((q+r)/(p+q+r)));
	}
	
	/**
	 * Dissimilarity.
	 *
	 * @param stInput the st input
	 * @param strItem1 the str item1
	 * @param strItem2 the str item2
	 * @return the string
	 */
	public static String dissimilarity(StringTable stInput,String strItem1,String strItem2){
		double[] Matrix=getMatrixArray(stInput,strItem1,strItem2);
		
		double p=Matrix[0]; //number of variables that positive for both objects > Book Q
		double q=Matrix[1]; //number of variables that positive for the i Item1 th objects and negative for the j Item2 th object >Book r 
		double r=Matrix[2]; //number of variables that negative for the i Item1 th objects and positive for the j Item2 th object >Book s
		double s=Matrix[3]; //number of variables that negative for both objects > Book T
		
		//System.out.println(p+"-"+q+"-"+r+"-"+s);
		
		return "d("+strItem1+","+strItem2+")="+String.format("%1.3f", ((q+r)/(s+p+q+r)));
	}
	
	/**
	 * Gets the matrix array.
	 *
	 * @param stInput the st input
	 * @param strItem1 the str item1
	 * @param strItem2 the str item2
	 * @return the matrix array
	 */
	public static double[] getMatrixArray(StringTable stInput,String strItem1,String strItem2){
		int intItem1Index=-1;
		int intItem2Index=-1;
		
		ArrayList<String[]> ArrLRows= stInput.getArrLRows();
		
		for(int i = 0;i<ArrLRows.size();i++){
			String strClass=ArrLRows.get(i)[0];
			
			if(strClass.equalsIgnoreCase(strItem1))intItem1Index=i;
			if(strClass.equalsIgnoreCase(strItem2))intItem2Index=i;
		}
		
		double p=0; //number of variables that positive for both objects
		double q=0; //number of variables that positive for the Item1 th objects and negative for the Item2 th object 
		double r=0; //number of variables that negative for the Item1 th objects and positive for the Item2 th object
		double s=0; //number of variables that negative for both objects
		
		
		for(int i=1;i<ArrLRows.get(0).length;i++){
			String curItem1=ArrLRows.get(intItem1Index)[i];
			String curItem2=ArrLRows.get(intItem2Index)[i];
			
			if(curItem1.equalsIgnoreCase("y")&&curItem2.equalsIgnoreCase("y")){
				p++;
			}else if(curItem1.equalsIgnoreCase("y")&&curItem2.equalsIgnoreCase("n")){
				q++;
			}else if(curItem1.equalsIgnoreCase("n")&&curItem2.equalsIgnoreCase("y")){
				r++;
			}else if(curItem1.equalsIgnoreCase("n")&&curItem2.equalsIgnoreCase("n")){
				s++;
			}
		}
		
		//System.out.println(p+"-"+q+"-"+r+"-"+s);
		//System.out.println(intItem1Index+"\t"+intItem2Index);
		double[] temp=new double[]{p,q,r,s};
		return temp;
	}
	
}
