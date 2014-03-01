package org.rss;

import java.util.ArrayList;

import org.database.DataView;
import org.database.DatabaseFactory;
import org.enums.DatabaseEngineEnum;
import org.shared.StringTable;

// TODO: Auto-generated Javadoc
/**
 * The Class RssOutputer.
 */
public class RssOutputer {

	/** The Data conn. */
	static DatabaseFactory DataConn = new DatabaseFactory(DatabaseEngineEnum.MYSQLSINGLE);
	
	/** The Current conn. */
	static DataView CurrentConn= DataConn.data;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Output.
	 *
	 * @param Input the input
	 */
	public static void output(StringTable Input){
		//System.out.println(Input.size());
		if(Input.getArrLRowsSize()>0){
			System.out.println(Input);
		}
		
		
		CurrentConn.addRss(Input);
	}

}
