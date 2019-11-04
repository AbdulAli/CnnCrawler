package nuces.aim.crawler.cnn;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CnnCrawler {
	
	public List<CnnData> crawlCNN() {
		List<CnnData> cnnDataList = new ArrayList<CnnData>(0);
		WebDriver driver;
		ChromeOptions chromeOptions = new ChromeOptions();
		String[] array = { "--start-maximized" };
		chromeOptions.addArguments(array);

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		driver = new ChromeDriver(chromeOptions);
		driver.get("http://edition.cnn.com/");
		driver.findElement(By.cssSelector(".buttonstyles__ButtonBase-augw5g-0 > .search-icon")).click();
		driver.findElement(By.id("header-search-bar")).sendKeys("Donald Trump");
		driver.findElement(By.cssSelector(".Box-sc-1fet97o-0 .Text-sc-1amvtpj-0")).click();

		List<WebElement> titles = driver.findElements(By
				.className("cnn-search__result-headline"));
		List<WebElement> dates = driver.findElements(By
				.className("cnn-search__result-publish-date"));
		List<WebElement> texts = driver.findElements(By
				.className("cnn-search__result-body"));
		
		List<String> titlesText = new ArrayList<String>(0);
		List<String> datesText = new ArrayList<String>(0);
		List<String> completeText = new ArrayList<String>(0);
		
		for(int i=0;i<titles.size();i++){
			titlesText.add(titles.get(i).getText());
			datesText.add(dates.get(i).getText());
			completeText.add(texts.get(i).getText());
		}

		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0,2000)");
			driver.findElement(By.cssSelector(".pagination-arrow-right")).click();
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			titles = driver.findElements(By
					.className("cnn-search__result-headline"));
			dates = driver.findElements(By
					.className("cnn-search__result-publish-date"));
			texts = driver
					.findElements(By.className("cnn-search__result-body"));

			for (int j = 0; j < titles.size(); j++) {
				titlesText.add(titles.get(j).getText());
				datesText.add(dates.get(j).getText());
				completeText.add(texts.get(j).getText());
			}
		}
		for(int i=0;i<25;i++){
			System.out.println(titlesText.get(i));
			System.out.println(datesText.get(i));
			System.out.println(completeText.get(i));
			cnnDataList.add(new CnnData(titlesText.get(i), datesText.get(i), completeText.get(i)));
		}
		driver.close();
		return cnnDataList;
	}
}