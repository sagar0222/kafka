/**
 * 
 */
package com.test.kafka.producer;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author SAGAR
 *
 */
public class MyKafkaProducer {
	
	/**. Kafka Configuration **/
	 ProducerConfig config = null;
	 
	 private static final String topicName = "TestTopic";
	 Producer<String, String> producer= null;
	 
	 String tempMessage_1 = "MyKafkaProducer.produceSync.mesage : ";
	 String tempMessage_2 = "MyKafkaProducer.produceAsync.mesage : ";
	
	/**.
	 * Constructor to set necessary producer configuration parameters.
	 */
	public MyKafkaProducer (){

	    Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put("group.id", "test");
	    props.put("enable.auto.commit", "true");
	    props.put("auto.commit.interval.ms", "1000");
	    props.put("session.timeout.ms", "30000");
	    props.put("acks", "all");
	    props.put("retries", 0);
	    props.put("partition.assignment.strategy", "range");
	    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");  
	    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	    
	    
		
		this.producer = new KafkaProducer<String, String>(props);
		
	}
	
	/**.
	 * This method sends message to kafka broker in synchronous fashion.
	 */
	public void produceSync() {

		long runtime = new Date().getTime();

		try {
			ProducerRecord<String, String> data = new ProducerRecord<String, String>(topicName,
					tempMessage_1.concat(String.valueOf(runtime)));

			RecordMetadata recordMetadata = producer.send(data).get();
			System.out.println("Record Metadata :\n**********************" 
							+ "Message is sent to Topic : "+ recordMetadata.topic() 
							+ " in Partition : " + recordMetadata.partition() 
							+ " and Offset : " + recordMetadata.offset());
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			producer.close();
		}
	}
	
	
	/**.
	 * This method sends message to kafka broker in asynchronous fashion.
	 */
	public void produceAsync() {

		long runtime = new Date().getTime();

		ProducerRecord<String, String> data = new ProducerRecord<String, String>(topicName,
				tempMessage_2.concat(String.valueOf(runtime)));

		producer.send(data, new MyKafkaProducerCallback());

		producer.close();

	}


}
