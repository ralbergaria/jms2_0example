package rafaelalbergaria.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class OldMessageConsumerMDB implements MessageListener {

	public void onMessage(Message arg0) {
		Context context = null;
		ConnectionFactory cf;
		Connection connection = null;
		Destination topic;

		try {
			context = new InitialContext ();
			cf = (ConnectionFactory) context.lookup ("jms/connectionFactory");
			topic = (Destination) context.lookup ("java:global/jms/jmsTopic");
			connection = cf.createConnection();	
			connection.start();
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session.createConsumer(topic);
			TextMessage textMessage = (TextMessage) consumer.receive();
			String body = textMessage.getText();
			System.out.println(body);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
