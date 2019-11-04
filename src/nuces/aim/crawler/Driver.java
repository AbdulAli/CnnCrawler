package nuces.aim.crawler;

import java.util.ArrayList;
import java.util.List;

import nuces.aim.crawler.cnn.CnnCrawler;
import nuces.aim.crawler.cnn.CnnData;
import nuces.aim.crawler.fileWriter.FileHandler;
import nuces.aim.crawler.twitter.TwitterCrawler;
import nuces.aim.crawler.twitter.TwitterData;
import nuces.aim.web.GenerateWebPage;

/**
 * 
 * @author AbdulAli Main Driver of The Program
 * 
 */
public class Driver {

	public static void main(String[] args) {
		
		
		//Crawling CNN
		CnnCrawler cnn = new CnnCrawler();
		List<CnnData> cnnDataList = new ArrayList<CnnData>(0);
		cnnDataList = cnn.crawlCNN();
		
		
		//Crawling Twitter 
		TwitterCrawler twitter = new TwitterCrawler();
		List<TwitterData> twitterDataList = twitter.crawlTwitter();

		FileHandler.generateCSV(cnnDataList,twitterDataList);
		
		//Generating Output as html
		GenerateWebPage gen = new GenerateWebPage(cnnDataList, twitterDataList);
		try {
			gen.generateHTML();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
