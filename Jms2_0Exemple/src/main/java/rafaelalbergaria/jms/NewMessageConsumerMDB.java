package rafaelalbergaria.jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Simple exemple for a Asynchronous JMS.
 * @author Rafael
 *
 */
public class NewMessageConsumerMDB implements MessageListener {
	//Used JMSContext injection to specify the JNDI name of the JMS ConnectionFactory.
	//JMSContext encapsulates all complexity.
	@Inject
	@JMSConnectionFactory("jms/connectionFactory")
	private JMSContext context;
	
	//Setting jms queue. You could use Queue here if your consumer are It.
	@Resource(mappedName="java:global/jms/jmsTopic")
	Destination topic;

	public void onMessage(Message arg0) {
		JMSConsumer consumer = context.createConsumer(topic);
		System.out.println(consumer.receiveBody(String.class));
	}

}
