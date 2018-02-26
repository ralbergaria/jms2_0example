package rafaelalbergaria;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import rafaelalbergaria.jms.NewMessageConsumerMDB;
import rafaelalbergaria.jms.NewMessageConsumerSync;
import rafaelalbergaria.jms.NewMessagerSender;
import rafaelalbergaria.jms.OldConsumerMessageSync;
import rafaelalbergaria.jms.OldMessageConsumerMDB;
import rafaelalbergaria.jms.OldMessageSender;

@RunWith(Arquillian.class)
public class JMSTest {
	@EJB
	NewMessagerSender senderNew;
	
	@EJB
	OldMessageSender senderOld;
	
	@Deployment
	public static JavaArchive createTestArchive() {

		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(NewMessageConsumerMDB.class, NewMessageConsumerSync.class, 
						NewMessagerSender.class, OldConsumerMessageSync.class, OldMessageConsumerMDB.class, OldMessageSender.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	
	@Test
	public void messageTest() {
		senderNew.sendMessage("Hi this is my first JMS 2.0");
		senderOld.sendMessage("I don't need use you anymore old message!");
	}
}
