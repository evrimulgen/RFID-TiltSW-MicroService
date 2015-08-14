package io.pivotal.sensor;

import io.pivotal.sensor.messaging.RFIDReceiver;
import io.pivotal.sensor.messaging.TiltSwitchReceiver;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class RfidMicroServiceApplication {
	
	
	private String rfidQueue;
	
	private String tiltQueue;
	
	private String exchange;

	private String rfidRoutingKey;

	private String tiltRoutingKey;
	
		//commented out for use of spring cloud config
//	final static String rfidQueue = "arduino-rfid-event-queue";
	//final static String tiltQueue = "arduino-tilt-event-queue";
//	
//	commented out temporarily
//	@Autowired
//	AnnotationConfigApplicationContext context;


	@Autowired
	RabbitTemplate rabbitTemplate; 
	
    public static void main(String[] args) {
        SpringApplication.run(RfidMicroServiceApplication.class, args);
    }
    
	@Bean
	Queue queueRFID() { //println there for testing
		System.out.println(rfidQueue+"----");
		return new Queue(rfidQueue, true);
	}
	
	@Bean
	Queue queueTilt() {
		return new Queue(tiltQueue, true);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange, true, false);
	}

	@Bean
	Binding bindingRFID(Queue queueRFID, TopicExchange exchangeRFID) {
		return BindingBuilder.bind(queueRFID).to(exchangeRFID).with(rfidRoutingKey);
	}
	
	@Bean
	Binding bindingTilt(Queue queueTilt, TopicExchange exchangeTilt) {
		return BindingBuilder.bind(queueTilt).to(exchangeTilt).with(tiltRoutingKey);
	}
	
	@Bean
	SimpleMessageListenerContainer containerRFID(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapterRFID) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(rfidQueue);
		container.setMessageListener(listenerAdapterRFID);
		return container;
	}
	
	@Bean
	SimpleMessageListenerContainer containerTilt(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapterTilt) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(tiltQueue);
		container.setMessageListener(listenerAdapterTilt);
		return container;
	}
	
	@Bean
	RFIDReceiver receiverRFID() {
        return new RFIDReceiver();
    }
	
	@Bean
	TiltSwitchReceiver receiverTilt() {
        return new TiltSwitchReceiver();
    }

	@Bean
	MessageListenerAdapter listenerAdapterRFID(RFIDReceiver receiverRFID) {
		return new MessageListenerAdapter(receiverRFID, "receiveMessage");
	}
	
	@Bean
	MessageListenerAdapter listenerAdapterTilt(TiltSwitchReceiver receiverTilt) {
		return new MessageListenerAdapter(receiverTilt, "receiveMessage");
	}

//	@Bean
//	TopicExchange exchangeRFID() {
//		return new TopicExchange("arduino-rfid-exchange", true, false);
//	}
//	
//	@Bean
//	TopicExchange exchangeTilt() {
//		return new TopicExchange("arduino-tilt-exchange", true, false);
//	}
//	
//	@Bean
//	Binding bindingRFIDWithRFIDExchange(TopicExchange exchangeSensor, TopicExchange exchangeRFID) {
//		return BindingBuilder.bind(exchangeSensor).to(exchangeRFID).with("arduino-rfid-exchange");
//	}
//	
//	@Bean
//	Binding bindingTiltWithTiltExchange(TopicExchange exchangeSensor, TopicExchange exchangeTilt) {
//		return BindingBuilder.bind(exchangeSensor).to(exchangeTilt).with("arduino-tilt-exchange");
	
	 @Autowired
	    void setEnvironment(Environment e) { //used to test reading of values
	    
		 	rfidQueue = e.getProperty("rfid.queueNameRFID");
			tiltQueue = e.getProperty("rfid.queueNameTilt");
			exchange = e.getProperty("rfid.exchangeName");
			rfidRoutingKey = e.getProperty("rfid.routingKeyRFID");
			tiltRoutingKey = e.getProperty("rfid.routingKeyTilt");
		 
	    	System.out.println(e.getProperty("rfid.queueNameRFID"));
	    	System.out.println(e.getProperty("rfid.queueNameTilt"));
	    	System.out.println(e.getProperty("rfid.exchangeName"));
	    	System.out.println(e.getProperty("rfid.routingKeyRFID"));
	    	System.out.println(e.getProperty("rfid.routingKeyTilt"));	

	    }

}

//whole class potentially not needed any more
@RestController
@RefreshScope
class queueNameRestController {
	
	@Value ("${rfid.queueNameRFID}")
	private String rfidQueue;
	
	String rfidQueue() {
		return this.rfidQueue;
	}
	
	@Value ("${rfid.queueNameTilt}")
	private String tiltQueue;
	
	String tiltQueue() {
		return this.tiltQueue;
	}
	
	@Value ("${rfid.exchangeName}")
	private String exchange;
	
	String exchange() {
		return this.exchange;
	}
	
	@Value ("${rfid.routingKeyRFID}")
	private String rfidRoutingKey;
	
	String rfidRoutingKey() {
		return this.rfidRoutingKey;
	}
	
	@Value ("${rfid.routingKeyTilt}")
	private String tiltRoutingKey;
	
	String tiltRoutingKey () {
		return this.tiltRoutingKey;
	}
	
}


