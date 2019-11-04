package nuces.aim.crawler.twitter;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * @author AbdulAli
 *
 */
public class TwitterCrawler {
    /**
     * Usage: java twitter4j.examples.timeline.GetHomeTimeline
     *
     * 
     */
    public List<TwitterData> crawlTwitter() {
    	
    	List<TwitterData> twitterDataList = new ArrayList<TwitterData>(0);
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	.setOAuthConsumerKey(TwitterConfig.API_KEY) //API KEY
        	.setOAuthConsumerSecret(TwitterConfig.API_SECRET) //API SECRET
        	.setOAuthAccessToken(TwitterConfig.ACCESS_TOKEN)	//Access Token
        	.setOAuthAccessTokenSecret(TwitterConfig.TOKEN_SECRET);	//Access Token Secret
            // gets Twitter instance with default credentials
        	TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            User user = twitter.verifyCredentials();
            System.out.println("Showing @realDonaldTrump's twitter page.");
            Paging paging = new Paging(2,25);
            List<Status> statuses = twitter.getUserTimeline("realDonaldTrump",paging);
            for (Status status : statuses) {
            	twitterDataList.add(new TwitterData(status.getRetweetCount(), status.getText()));
                //System.out.println(status.getText()+"\n====\n");
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
        return twitterDataList;
    }
}