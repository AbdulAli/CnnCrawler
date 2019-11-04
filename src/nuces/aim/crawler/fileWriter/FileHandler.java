package nuces.aim.crawler.fileWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nuces.aim.crawler.cnn.CnnData;
import nuces.aim.crawler.twitter.TwitterData;

public abstract class FileHandler {

	public static void generateCSV(List<CnnData> cnnDataList,
			List<TwitterData> twitterDataList) {
		try {
			// Write CNN .csv
			BufferedWriter outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter("cnn.csv"));
			for (CnnData cnn : cnnDataList) {
				outputWriter.write(cnn.getHeadline().replaceAll("\n", "").replaceAll(",", " ") + ","
						+ cnn.getDate().replaceAll("\n", "").replaceAll(",", " ") + ","
						+ cnn.getCompleteNews().replaceAll("\n", "").replaceAll(",", " "));
				outputWriter.newLine();
			}
			outputWriter.flush();
			outputWriter.close();

			// Write Twitter .csv
			outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter("twitter.csv"));
			for (TwitterData twitter : twitterDataList) {
				outputWriter.write(twitter.getNumberOfRetweets() + ","
						+ twitter.getBody().replaceAll("\n", ""));
				outputWriter.newLine();
			}
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<CnnData> readCnnCSV() {
		List<CnnData> cnnDataList = new ArrayList<CnnData>(0);
		List<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("cnn.csv"));
			String line;
			String[] tokens;
			while ((line = reader.readLine()) != null) {
				tokens = line.split(",");
				cnnDataList.add(new CnnData(tokens[0], tokens[1], tokens[2]));
			}
			reader.close();
			return cnnDataList;
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.",
					"cnn.csv");
			e.printStackTrace();
			return null;
		}
	}

	public static List<TwitterData> readTwitterCSV() {
		List<TwitterData> twitterDataList = new ArrayList<TwitterData>(0);
		List<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"twitter.csv"));
			String line;
			String[] tokens;
			while ((line = reader.readLine()) != null) {
				if (line.contains(",")) {
					tokens = line.split(",");
					twitterDataList.add(new TwitterData(0, tokens[1]));
				}
			}
			reader.close();
			return twitterDataList;
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.",
					"twitter.csv");
			e.printStackTrace();
			return null;
		}
	}

}
