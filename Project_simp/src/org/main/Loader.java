package org.main;

import org.database.DataView;
import org.database.DatabaseFactory;
import org.enums.DatabaseEngineEnum;
import org.shared.StringTable;

public class Loader {


	/** The Data conn. */
	static DatabaseFactory DataConn = new DatabaseFactory(
			DatabaseEngineEnum.MYSQLSINGLE);

	/** The Current conn. */
	static DataView CurrentConn = DataConn.data;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringTable RssFeeds = CurrentConn.getRSSFeedsCategory("World");
		RssFeeds.setStrDelimiter(",");
		System.out.println(RssFeeds);

	}

}
