/**
 * 
 */
package com.test.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author SAGAR
 *
 */
public class MyKafkaProducerCallback implements Callback{

	public void onCompletion(RecordMetadata metadata, Exception exception) {
		System.out.println("MyKafkaProducerCallback.Record Metadata :\n**********************" 
				+ "Message is sent to Topic : "+ metadata.topic() 
				+ " in Partition : " + metadata.partition() 
				+ " and Offset : " + metadata.offset());
		
		if(null != exception){
			System.out.println("Exception occured during message commit : "+exception);
			//Put your business logic to handle exception scenario.
		}
		
	}

}
