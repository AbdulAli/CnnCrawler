package nuces.aim.crawler.twitter;

public class TwitterData {
	
	int numberOfRetweets; // getRetweetCount()
	
	String body; // getText()

	public TwitterData(int numberOfRetweets, String body) {
		super();
		this.numberOfRetweets = numberOfRetweets;
		this.body = body;
	}

	public int getNumberOfRetweets() {
		return numberOfRetweets;
	}

	public void setNumberOfRetweets(int numberOfRetweets) {
		this.numberOfRetweets = numberOfRetweets;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
