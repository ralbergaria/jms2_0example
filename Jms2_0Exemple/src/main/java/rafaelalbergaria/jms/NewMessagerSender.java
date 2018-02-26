package rafaelalbergaria.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 * This is a single method to send a message
 * @author Rafael
 *
 */
@Stateless
public class NewMessagerSender {
	//Used JMSContext injection to specify the JNDI name of the JMS ConnectionFactory.
	//JMSContext encapsulates all complexity.
	@Inject
	@JMSConnectionFactory("jms/connectionFactory")
	JMSContext context;
	
	//Setting jms queue. You could use Queue here if your consumer are It.
	@Resource(mappedName="java:global/jms/jmsTopic")
	Topic topic;

	public void sendMessage(String message) {
		context.createProducer().send(topic, message);
	}
}
