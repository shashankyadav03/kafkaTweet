 package com.twitterkafka.tweet;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import elasticSearch.insert;






public class Kconsumer {
	public static org.slf4j.Logger logger =  LoggerFactory.getLogger(Kconsumer.class.getName());
	
	public static Properties setProperty() {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"mygroup2");
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");


		return properties;
		
	}
	
	public static void create() throws IOException {
		
				
		KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(setProperty());
		
		consumer.subscribe(Arrays.asList("firsttopic"));
		
		while(true) {
			ConsumerRecords<String, String> records= consumer.poll(Duration.ofMillis(100));
			
			for (ConsumerRecord<String, String>  record : records) {
				
				//logger.info("Key: "+record.key() + ", Value: "+record.value());
				//logger.info("Partition: "+record.partition() + ", Offset: "+record.offset());
				insert.insertData(record.value());
				
			}
			//consumer.close();
		}
	
		
		
		
	}
}
