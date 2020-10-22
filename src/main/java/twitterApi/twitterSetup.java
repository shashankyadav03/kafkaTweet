package twitterApi;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class twitterSetup {
	
	private static final Logger logger = LogManager.getLogger(twitterSetup.class);  
	
	public static String getOauth() throws IOException {
				OkHttpClient client = new OkHttpClient().newBuilder()
						.build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
				  .addFormDataPart("grant_type", "client_credentials")
				  .build();
				Request request = new Request.Builder()
				  .url("https://api.twitter.com/oauth2/token")
				  .method("POST", body)
				  .addHeader("grant_type", "client_credentials")
				  .addHeader("Authorization", "Basic enpqTTAzNURLU0I3SXR4VkZBZjR3ZEp1czpCb05LajlURlh1Nk5QbEdZQnMzamhkTkxhemY2SGs4bk0xYjloY3BoYXBqdkZiUGJyYw==")
				  .addHeader("Cookie", "personalization_id=\"v1_DBzOVtFkM7tMnoBMPB0HyA==\"; guest_id=v1%3A160329581248835835; ct0=8261518177c3a24545ae70d2b1eed84f")
				  .build();
				Response response = client.newCall(request).execute();
				// basic log4j configurator  
				  BasicConfigurator.configure();  
				 // logger.info("Twitter1");  
				  //logger.info(response.body().string()); 
				  String[] parts = response.body().string().split(":");
				  String bear = parts[2];
				  String bearer = bear.substring(1, bear.length() - 2);
				  //logger.info(bearer); 
				  String auth = "Bearer "+bearer;
				 // logger.info(auth);
				return auth;
	}
	
	public static String[] getTweets(String auth) throws IOException {
				//logger.info(auth);
				OkHttpClient client = new OkHttpClient().newBuilder()
						.build();
				Request request = new Request.Builder()
				  .url("https://api.twitter.com/1.1/search/tweets.json?q=ipl")
				  .method("GET", null)
				  .addHeader("Authorization",auth)
				  .addHeader("Cookie", "personalization_id=\"v1_DBzOVtFkM7tMnoBMPB0HyA==\"; guest_id=v1%3A160329581248835835; ct0=8261518177c3a24545ae70d2b1eed84f")
				  .build();
				Response response = client.newCall(request).execute();
				BasicConfigurator.configure();  
				  //logger.info("Twitter2");  
				 // logger.info(response.body().string()); 
				  
				  String jsonString = response.body().string() ; //assign your JSON String here
				  JSONObject obj = new JSONObject(jsonString);
				  
				 // String tweet = obj.getJSONObject("statuses").getString("text");
				  
				  JSONArray arr = obj.getJSONArray("statuses");
				  String[] tweet= new String[arr.length()];
				  for (int i = 0; i < arr.length(); i++)
				  {
				      tweet[i] = arr.getJSONObject(i).getString("text");
				      
				  }
				  //logger.info(tweet[0]);
				  	  
				  return tweet;
	}
	
	public static void printTweet(String[] tweets) {
		
		//logger.info("Twitter3");  
		
		for (int i = 0; i < tweets.length; i++)
		  {
			System.out.print(i+1+" ");
			System.out.println(tweets[i]);
		  }
	}
	
	public static String[] getTweets() throws IOException {
		String auth=getOauth();
		String[] tweets=getTweets(auth);
		//printTweet(tweets);
		return tweets;
	}
	
	

}
