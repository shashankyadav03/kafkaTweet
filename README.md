# kafkaTweet
kafkaTweet
kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic firsttopic
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic firsttopic --group mygroup
zookeeper-server-start.sh config/zookeeper.properties 
kafka-server-start.sh config/server.properties 

