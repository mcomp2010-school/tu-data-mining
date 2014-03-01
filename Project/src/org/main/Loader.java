package org.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.database.DataView;
import org.database.DatabaseFactory;
import org.enums.DatabaseEngineEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.nlp.OpenNLP;
import org.records.Item;
import org.records.RecordMgr;
import org.shared.StringTable;
import org.shared.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class Loader.
 */
public class Loader {

	/** The Data conn. */
	static DatabaseFactory DataConn = new DatabaseFactory(
			DatabaseEngineEnum.MYSQLSINGLE);

	/** The Current conn. */
	static DataView CurrentConn = DataConn.data;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		//Start
		System.out.println("Start....");
		analyze("World");
		analyze("Business");
		analyze("Health");
		analyze("Nation");
		analyze("Science");
		analyze("Sports");
		analyze("Technology");
		analyze("Entertainment");
		System.out.println("Done");
		
		
	}

	private static void analyze(String Category) throws IOException {
		StringBuilder outStringResults=new StringBuilder();
		
		StringTable RssFeeds = CurrentConn.getRSSFeedsCategory(Category);
		RssFeeds.setStrDelimiter(",");
		// System.out.println(RssFeeds);

		outStringResults.append("Category: "+Category+"\n");
		outStringResults.append("Top Words"+"\n");
		outStringResults.append("-----------------"+"\n");
		TreeMap<String, Integer> tmCommonTitle = mapTitle(RssFeeds);
		RecordMgr rmCommonTitle=printMapMutli(tmCommonTitle, 100);
		outStringResults.append(printRecordMgr(rmCommonTitle)+"\n");
		//outStringBuilder.append(rmCommonTitle.printFreqWords());
		
		outStringResults.append("==============================="+"\n");
		outStringResults.append("Top Words based on Week Day"+"\n");
		outStringResults.append("-----------------"+"\n");
		TreeMap<String, Integer> tmCommonTitleDay = mapTitleWithDayWeekDay(RssFeeds);
		outStringResults.append(printRecordMgr(printMapMutli(tmCommonTitleDay, 10))+"\n");

		outStringResults.append("==============================="+"\n");
		outStringResults.append("Top Words based on Day of Month"+"\n");
		outStringResults.append("-----------------"+"\n");
		TreeMap<String, Integer> tmCommonTitleWithDayMonth = mapTitleWithDayMonth(RssFeeds);
		outStringResults.append(printRecordMgr(printMapMutli(tmCommonTitleWithDayMonth, 5))+"\n");

		outStringResults.append("==============================="+"\n");
		outStringResults.append("Top Words based on the Hour"+"\n");
		outStringResults.append("-----------------"+"\n");
		TreeMap<String, Integer> tmCommonTitleWithDayHour = mapTitleWithDayHour(RssFeeds);
		RecordMgr rmCommonTitleWithDayHour=printMapMutli(tmCommonTitleWithDayHour, 5);
		outStringResults.append(printRecordMgr(rmCommonTitleWithDayHour)+"\n");
		
		
		//System.out.println(outStringResults.toString());
		System.out.println("Done:"+Category);
		
	    Util.folderExistAndMake("results");
		// Writing table to file
		File Out = new File("results\\Results-"+Category+".txt");
		FileUtils.writeStringToFile(Out, outStringResults.toString());
		
		// Writing table to file
		Util.folderExistAndMake("data");
		Out = new File("data\\Data-"+Category+".csv");
		FileUtils.writeStringToFile(Out, RssFeeds.toString());
	}

	private static String printRecordMgr(RecordMgr rmCommonTitleWithDayHour) {
		StringBuilder outStringBuilder=new StringBuilder();
		
		for (Iterator iterator = rmCommonTitleWithDayHour.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			//System.out.println(item);
			outStringBuilder.append(item+"\n");
		}
		
		return outStringBuilder.toString();
	}

	/**
	 * Map title.
	 * 
	 * @param RssFeeds
	 *            the rss feeds
	 * @return the tree map
	 */
	private static TreeMap<String, Integer> mapTitle(StringTable RssFeeds) {
		TreeMap<String, Integer> tmCommon = new TreeMap<String, Integer>();

		
		for (int i = 0; i < RssFeeds.getArrLRowsSize(); i++) {
			String title = RssFeeds.getColumnValue("title", i);

			mapperHelper(tmCommon, title, "null");
		}
		return tmCommon;
	}

	/**
	 * Map title with day month.
	 * 
	 * @param RssFeeds
	 *            the rss feeds
	 * @return the tree map
	 */
	private static TreeMap<String, Integer> mapTitleWithDayMonth(
			StringTable RssFeeds) {
		TreeMap<String, Integer> tmCommon = new TreeMap<String, Integer>();
		
		for (int i = 0; i < RssFeeds.getArrLRowsSize(); i++) {
			String title = RssFeeds.getColumnValue("title", i);
			String date = RssFeeds.getColumnValue("public_date", i)
					.substring(4, 10).trim();

			mapperHelper(tmCommon, title, date);
		}
		return tmCommon;
	}

	/**
	 * Map title with day hour.
	 * 
	 * @param RssFeeds
	 *            the rss feeds
	 * @return the tree map
	 */
	private static TreeMap<String, Integer> mapTitleWithDayHour(
			StringTable RssFeeds) {
		TreeMap<String, Integer> tmCommon = new TreeMap<String, Integer>();

		for (int i = 0; i < RssFeeds.getArrLRowsSize(); i++) {
			String title = RssFeeds.getColumnValue("title", i);
			String date = RssFeeds.getColumnValue("public_date", i)
					.substring(11, 13).trim();

			Integer Hour = Integer.parseInt(date);

			if (Hour == 0) {
				date += "-12am";
			} else if (Hour <= 11) {
				date += "-" + StringUtils.leftPad(Hour + "", 2, "0") + "am";
			} else if (Hour == 12) {
				date += "-" + StringUtils.leftPad((Hour) + "", 2, "0") + "pm";
			} else {
				date += "-" + StringUtils.leftPad((Hour % 12) + "", 2, "0")
						+ "pm";
			}

			mapperHelper(tmCommon, title, date);
		}
		return tmCommon;
	}

	private static void mapperHelper(TreeMap<String, Integer> tmCommon,
			String title, String date) {
		
		//OpenNLP nlpObj=new OpenNLP();
		ArrayList<String> titleSplitStrings = Util
				.splitStringToWords(title);
		//titleSplitStrings=nlpObj.findNamesAndMergeArrayList(titleSplitStrings);
		

		for (int j = 0; j < titleSplitStrings.size(); j++) {
			String CurrentTitle = date + ">>>" + titleSplitStrings.get(j);
			// System.out.println(CurrentTitle);
			// CurrentTitle= CurrentTitle.replace(".", "");
			CurrentTitle = CurrentTitle.replace("...", "");
			CurrentTitle = CurrentTitle.replace(";", "");

			addTmCommon(tmCommon, CurrentTitle);
		}
	}

	/**
	 * Map title with day week day.
	 * 
	 * @param RssFeeds
	 *            the rss feeds
	 * @return the tree map
	 */
	private static TreeMap<String, Integer> mapTitleWithDayWeekDay(
			StringTable RssFeeds) {
		TreeMap<String, Integer> tmCommon = new TreeMap<String, Integer>();

		for (int i = 0; i < RssFeeds.getArrLRowsSize(); i++) {
			String title = RssFeeds.getColumnValue("title", i);
			String date = RssFeeds.getColumnValue("public_date", i)
					.substring(0, 3).trim();
			date = date.replace("Mon", "1-Mon");
			date = date.replace("Tue", "2-Tue");
			date = date.replace("Wed", "3-Wed");
			date = date.replace("Thu", "4-Thu");
			date = date.replace("Fri", "5-Fri");
			date = date.replace("Sat", "6-Sat");
			date = date.replace("Sun", "7-Sun");

			mapperHelper(tmCommon, title, date);
		}
		return tmCommon;
	}

	/**
	 * Prints the map mutli.
	 * 
	 * @param map
	 *            the map
	 */
	public static RecordMgr printMapMutli(Map<String, Integer> map, int topWords) {
		Map<String, Integer> orderedMap = orderMap(map);
		RecordMgr RecordsItems = new RecordMgr();

		HashSet<String> IgnoreWords = IgnoreWordsHashSet();

		HashMap<String, Integer> currword = new HashMap<String, Integer>();

		for (Entry<String, Integer> entry : orderedMap.entrySet()) {
			// ystem.out.println(entry.getKey());

			String col = "";
			String Wordentry = "";

			if (StringUtils.contains(entry.getKey(), ">>>")) {
				col = StringUtils.substringBefore(entry.getKey(), ">>>");
				Wordentry = StringUtils.substringAfter(entry.getKey(), ">>>");

			} else {
				col = "null";
				Wordentry = entry.getKey();
			}

			// System.out.println(Wordentry);

			if (IgnoreWords.contains(Wordentry.toLowerCase())
					|| Wordentry.trim().length() == 0) {
				// Ignore
			} else {

				if (!currword.containsKey(col)) {
					currword.put(col, 1);
				} else {
					currword.put(col, currword.get(col) + 1);
				}

				if (currword.get(col) <= topWords) {
					RecordsItems.add(new Item(col, Wordentry, entry.getValue()));
					// System.out.println("" + entry.getKey().replace(">>>",
					// "\t") + "\t"+ entry.getValue());
				}

			}
		}
	
		RecordsItems.sort();
	
		return RecordsItems;
	}

	/**
	 * Order map.
	 * 
	 * @param map
	 *            the map
	 * @return the map
	 */
	private static Map<String, Integer> orderMap(Map<String, Integer> map) {
		List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(
				map.entrySet());
		Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> e1,
					Entry<String, Integer> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		// Put entries back in an ordered map.
		Map<String, Integer> orderedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : entries) {
			orderedMap.put(entry.getKey(), entry.getValue());
		}
		return orderedMap;
	}


	/**
	 * Ignore words hash set.
	 * 
	 * @return the hash set
	 */
	private static HashSet<String> IgnoreWordsHashSet() {
		HashSet<String> IgnoreWords = new HashSet<String>();
		IgnoreWords.add("to");
		IgnoreWords.add("in");
		IgnoreWords.add("of");
		IgnoreWords.add("for");
		IgnoreWords.add("on");
		IgnoreWords.add("at");
		IgnoreWords.add("and");
		IgnoreWords.add("with");
		IgnoreWords.add("after");
		IgnoreWords.add("the");
		IgnoreWords.add("is");
		IgnoreWords.add("to");
		IgnoreWords.add("in");
		IgnoreWords.add("with");
		IgnoreWords.add("not");
		IgnoreWords.add("for");
		IgnoreWords.add("says");
		IgnoreWords.add("no");
		IgnoreWords.add("no.");
		IgnoreWords.add("say");
		IgnoreWords.add("who");
		IgnoreWords.add("its");
		IgnoreWords.add("his");
		IgnoreWords.add("her");
		IgnoreWords.add("up");
		IgnoreWords.add("he");
		IgnoreWords.add("she");
		IgnoreWords.add("an");
		IgnoreWords.add("be");
		IgnoreWords.add("over");
		IgnoreWords.add("what");
		IgnoreWords.add("are");
		IgnoreWords.add("it");
		IgnoreWords.add("vs.");
		IgnoreWords.add("may");
		IgnoreWords.add("who");
		IgnoreWords.add("more");
		IgnoreWords.add("as");
		IgnoreWords.add("by");
		IgnoreWords.add("from");
		IgnoreWords.add("how");
		IgnoreWords.add("was");
		IgnoreWords.add("why");
		IgnoreWords.add("--");
		IgnoreWords.add("or");
		IgnoreWords.add("you");
		IgnoreWords.add("he's");
		IgnoreWords.add("my");
		IgnoreWords.add("but");
		IgnoreWords.add("has");
		IgnoreWords.add("a");
		IgnoreWords.add(".");
		IgnoreWords.add("-");
		return IgnoreWords;
	}

	/**
	 * Adds the tm common.
	 * 
	 * @param tmCommon
	 *            the tm common
	 * @param CurrentTitle
	 *            the current title
	 */
	private static void addTmCommon(TreeMap<String, Integer> tmCommon,
			String CurrentTitle) {
		if (tmCommon.get(CurrentTitle) == null) {
			tmCommon.put(CurrentTitle, 1);
		} else {
			tmCommon.put(CurrentTitle, tmCommon.get(CurrentTitle) + 1);
		}
	}

}
