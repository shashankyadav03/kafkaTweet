package com.twitterkafka.tweet;

import java.io.IOException;

import twitterApi.twitterSetup;


public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	//Tweets
       String[] tweets=twitterSetup.getTweets();
       //twitterSetup.printTweet(tweets);
       //String[] tweets= {"Hello","World"};
       
       kproducer.create(tweets);
       
       Kconsumer.create();
    }
}
