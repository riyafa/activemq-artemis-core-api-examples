package org.riyafa.core.examples;

import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientConsumer;
import org.apache.activemq.artemis.api.core.client.ClientMessage;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;

/**
 * A consumer that can be used to test your application. The queue with the name "multicast_queue" needs to be created
 * before running this program.
 *
 * @author Riyafa Abdul Hameed
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        try (
                ServerLocator locator = ActiveMQClient.createServerLocator("tcp://localhost:61616");
                ClientSessionFactory factory = locator.createSessionFactory();
                ClientSession session = factory.createSession();
                ClientConsumer consumer = session.createConsumer("multicast_queue")
        ) {

            session.start();
            ClientMessage msgReceived = consumer.receive();
            msgReceived.acknowledge();

        }
    }
}
