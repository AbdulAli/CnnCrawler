package nuces.aim.web;

import java.util.ArrayList;
import java.util.List;

import nuces.aim.crawler.cnn.CnnData;
import nuces.aim.crawler.twitter.TwitterData;

public class Test {

	public static void main(String[] args) throws Exception {
		
		List<CnnData> cnnDataList = new ArrayList<CnnData>(0);
		
		cnnDataList.add(new CnnData("Rock", "21oct", "trump died of gunchsot"));
		cnnDataList.add(new CnnData("Bottom", "4oct", "trump died of cocain"));
		cnnDataList.add(new CnnData("Pop", "12oct", "trump died of posion"));
		cnnDataList.add(new CnnData("Jazz", "3oct", "trump lives forever"));
		
		List<TwitterData> twitterDataList = new ArrayList<TwitterData>(0);
		
		twitterDataList.add(new TwitterData(23, "Lets Party"));
		twitterDataList.add(new TwitterData(12, "Lets rock"));
		twitterDataList.add(new TwitterData(10, "Lets live"));
		twitterDataList.add(new TwitterData(5, "Lets Dream"));
		
		GenerateWebPage gen = new GenerateWebPage(cnnDataList,twitterDataList);
		gen.generateHTML();
	}

}
