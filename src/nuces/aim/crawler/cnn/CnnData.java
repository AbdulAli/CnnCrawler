package nuces.aim.crawler.cnn;

public class CnnData {
	
	String headline;
	String date;
	String completeNews;
	
	public CnnData(String headline, String date, String completeNews) {
		super();
		this.headline = headline;
		this.date = date;
		this.completeNews = completeNews;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCompleteNews() {
		return completeNews;
	}
	public void setCompleteNews(String completeNews) {
		this.completeNews = completeNews;
	}
}
