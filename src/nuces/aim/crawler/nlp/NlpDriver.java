package nuces.aim.crawler.nlp;

import nuces.aim.crawler.cnn.CnnData;
import nuces.aim.crawler.fileWriter.FileHandler;
import nuces.aim.crawler.twitter.TwitterData;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 
 * @author AbdulAli
 * Find name of the people in a certain CNN NEWS
 */
public class NlpDriver {

	public NlpDriver() throws IOException {
		// Reading CNN Data
		List<CnnData> cnnDataList = FileHandler.readCnnCSV();
		// Reading Twitter Data
		List<TwitterData> twitterDataList = FileHandler.readTwitterCSV();

		// Loading the tokenizer model
		InputStream inputStreamTokenizer = new FileInputStream(
				"nlp-model/en-token.bin");
		TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);

		// Instantiating the TokenizerME class
		TokenizerME tokenizer = new TokenizerME(tokenModel);

		// Tokenizing the sentence in to a string array

		for (CnnData data : cnnDataList) {
			String sentence = data.getCompleteNews();
			String tokens[] = tokenizer.tokenize(sentence);

			// Loading the NER-person model
			InputStream inputStreamNameFinder = new FileInputStream(
					"nlp-model/en-ner-person.bin");
			TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

			// Instantiating the NameFinderME class
			NameFinderME nameFinder = new NameFinderME(model);

			// Finding the names in the sentence
			Span nameSpans[] = nameFinder.find(tokens);
			if(nameSpans.length>0){
				System.out.println("The following NEWS: "+data.getHeadline());
				System.out.println("Contains following Names:-");
				// Printing the names and their spans in a sentence
				for (Span s : nameSpans)
					System.out.println(s.toString() + "  " + tokens[s.getStart()]);
			}
		}

	}
}
