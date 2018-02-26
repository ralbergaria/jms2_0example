package rafaelalbergaria.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class OldConsumerMessageSync {
	public String consumerMessage() {
		Context context = null;
		ConnectionFactory cf = null;
		Destination topic = null;
		Connection connection = null;
		try {
			context = new InitialContext ();
			cf = (ConnectionFactory) context.lookup ("jms/connectionFactory");
			topic = (Destination) context.lookup ("java:global/jms/jmsTopic");
			connection = cf.createConnection();
			Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			MessageConsumer messageConsumer = session.createConsumer(topic);
			connection.start();
			TextMessage textMessage = (TextMessage)messageConsumer.receive();
			return textMessage.getText();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
