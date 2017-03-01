/**
 * 
 */
package com.test.kafka.producer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author SAGAR
 *
 */
public class TestMyKafkaProducer {
	
	@Test
	public void testproduceSync(){
		MyKafkaProducer producer = new MyKafkaProducer();
		producer.produceSync();
		Assert.assertTrue(true);
		
	}
	
	@Test
	public void testproduceAsync(){
		MyKafkaProducer producer = new MyKafkaProducer();
		producer.produceAsync();
		Assert.assertTrue(true);
	}

}
