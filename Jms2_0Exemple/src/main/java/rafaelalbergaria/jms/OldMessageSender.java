package rafaelalbergaria.jms;

import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
/**
 * In this class I show how you did a old JMS sender.
 * @author Rafael
 *
 */
@Stateless
public class OldMessageSender {
	public void sendMessage(String message) {
		Context context = null;
		ConnectionFactory cf;
		Connection connection = null;
		Destination topic;
		try {
			context = new InitialContext ();
			cf = (ConnectionFactory) context.lookup ("jms/connectionFactory");
			topic = (Destination) context.lookup ("java:global/jms/jmsTopic");
			connection = cf.createConnection();
			Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(topic);
			TextMessage textMessage = session.createTextMessage(message);
			producer.send(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				context.close();
				connection.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
