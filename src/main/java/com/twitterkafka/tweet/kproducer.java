package com.twitterkafka.tweet;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONArray;

public class kproducer {
	public static Properties setProperty() {
		Properties properties = new Properties();
//		properties.setProperty("bootstrap.servers","localhost:9092");
//		properties.setProperty("key.serializer",StringSerializer.class.getName());
//		properties.setProperty("value.serializer",StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		
		return properties;
		
	}
	public static void create(String[] tweets) {
		KafkaProducer<String,String> producer = new KafkaProducer<String,String>(setProperty());
		
		for(int i=0;i<tweets.length;i++) {
			ProducerRecord<String,String> record =new ProducerRecord<String,String>("firsttopic",tweets[i]);
			producer.send(record);
		}
		
		producer.close();
		
	}

}
