package org.riyafa.core.examples;

import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientMessage;
import org.apache.activemq.artemis.api.core.client.ClientProducer;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;
import org.apache.activemq.artemis.reader.TextMessageUtil;

/**
 * A producer that can be used to test your application. The address with the name "multicast_address" need to be
 * created before running this.
 *
 * @author Riyafa Abdul Hameed
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        try (
                ServerLocator locator = ActiveMQClient.createServerLocator("tcp://localhost:61616");
                ClientSessionFactory factory = locator.createSessionFactory();
                ClientSession session = factory.createSession();
                ClientProducer producer = session.createProducer("multicast_address")
        ) {

            ClientMessage message = session.createMessage(false);
            TextMessageUtil.writeBodyText(message.getBodyBuffer(), new SimpleString("Hello world!"));
            producer.send(message);
        }
    }
}
