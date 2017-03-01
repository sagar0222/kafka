/**
 * 
 */
package com.test.kafka.consumer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author SAGAR
 *
 */
public class MyKafkaConsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyKafkaConsumer myKafkaConsumer = new MyKafkaConsumer();
		myKafkaConsumer.consume();
	}
	
	private KafkaConsumer<String, String> consumer ;
	
	private static final String topicName = "TestTopic";
	
	public MyKafkaConsumer(){
		  
		  Properties props = new Properties();
	      
	      props.put("bootstrap.servers", "localhost:9092");
	      props.put("group.id", "testConsumerGroup");
	      //props.put("enable.auto.commit", "true");
	      props.put("auto.commit.interval.ms", "1000");
	      props.put("session.timeout.ms", "30000");
	      props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	      props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	      consumer = new KafkaConsumer<String, String>(props);
	}
	
	
	public void consume(){
		//Kafka Consumer subscribes list of topics here.
	     consumer.subscribe(Arrays.asList(topicName));
	     
	     try {
			while (true) {
			     ConsumerRecords<String, String> records = consumer.poll(100);
			     //System.out.println("records -->"+records);
			     
			     Iterator<ConsumerRecord<String, String>> it = records.iterator() ;
			     while(it.hasNext()){
			    	 ConsumerRecord<String, String> record = it.next();
			    	 System.out.println("MyKafkaConsumer.consume ********");
			    	 System.out.printf("offset = %d, key = %s, value = %s\n", 
						        record.offset(), record.key(), record.value());
			     }
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			  consumer.close();
		}
	}
	
	public void shutdown() {
	    consumer.wakeup();
	  }

}
