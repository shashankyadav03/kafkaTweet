package com.twitterkafka.tweet;

import java.io.IOException;

import org.json.JSONArray;

import twitterApi.twitterSetup;

 
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	//Tweets
    	String[] tweets=twitterSetup.getTweets();
       //twitterSetup.printTweet(tweets);
       //String[] tweets= {"Hello","World"};
    	//String s = tweets.toString();
    	
       kproducer.create(tweets);
       
       Kconsumer.create();
       
    	
    }
}
