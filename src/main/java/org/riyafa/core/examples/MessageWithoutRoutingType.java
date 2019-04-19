package org.riyafa.core.examples;

import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientMessage;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;
import org.apache.activemq.artemis.reader.TextMessageUtil;

/**
 * Demonstrates that messages are not required to have a Routing Type that is they can be without a Routing Type.
 *
 * @author Riyafa Abdul Hameed
 */
public class MessageWithoutRoutingType {
    public static void main(String[] args) throws Exception {
        try (
                ServerLocator locator = ActiveMQClient.createServerLocator("tcp://localhost:61616");
                ClientSessionFactory factory = locator.createSessionFactory();
                ClientSession session = factory.createSession()
        ) {
            // Creating a non-durable message without a routing type
            ClientMessage message = session.createMessage(false);
            TextMessageUtil.writeBodyText(message.getBodyBuffer(), new SimpleString("Hello world!"));

            // This will print null
            System.out.println(message.getRoutingType());
        }
    }
}
