package rafaelalbergaria.jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;

/**
 * Simple exemple for a Synchronous JMS.
 * @author Rafael
 *
 */
public class NewMessageConsumerSync {
	//Used JMSContext injection to specify the JNDI name of the JMS ConnectionFactory.
	//JMSContext encapsulates all complexity.
	@Inject
	@JMSConnectionFactory("jms/connectionFactory")
	private JMSContext context;
	
	//Setting jms queue. You could use Queue here if your consumer are It. 
	@Resource(mappedName="java:global/jms/jmsTopic")
	Destination topic;

	public String consumerMessage() {
		JMSConsumer consumer = context.createConsumer(topic);
		return consumer.receiveBody(String.class);
	}
}
