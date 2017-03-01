/**
 * 
 */
package com.test.kafka.consumer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author SAGAR
 *
 */
public class TestMyKafkaConsumer {
	
	@Test
	public void testproduceSync(){
		MyKafkaConsumer consumer = new MyKafkaConsumer();
		consumer.consume();
		//consumer.shutdown();
		Assert.assertTrue(true);
		
	}
	
	
}
