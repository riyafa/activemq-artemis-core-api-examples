package org.riyafa.core.examples;

import org.apache.activemq.artemis.api.core.RoutingType;
import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientMessage;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;
import org.apache.activemq.artemis.reader.TextMessageUtil;

/**
 * Demonstrates that all Messages, Queues and Addresses can have Routing Types.
 *
 * @author Riyafa Abdul Hameed
 */
public class RoutingTypeForAll {
    public static void main(String[] args) throws Exception {
        try (
                ServerLocator locator = ActiveMQClient.createServerLocator("tcp://localhost:61616");
                ClientSessionFactory factory = locator.createSessionFactory();
                ClientSession session = factory.createSession()
        ) {

            // Creating a non-durable message and setting the routing type
            ClientMessage message = session.createMessage(false);
            message.setRoutingType(RoutingType.ANYCAST);
            TextMessageUtil.writeBodyText(message.getBodyBuffer(), new SimpleString("Hello world!"));

            // Creating an address with a Routing Type
            String addressName = "routing_type";
            session.createAddress(new SimpleString(addressName), RoutingType.ANYCAST, true);

            // Creating a queue with a Routing Type
            session.createQueue(addressName, RoutingType.ANYCAST, "routing_type");

        }
    }
}
