package org.riyafa.core.examples;

import org.apache.activemq.artemis.api.core.RoutingType;
import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;

/**
 * Demonstrates that when an ANYCAST queue is created and bound to a MULTICAST address then the address would be
 * modified to support ANYCAST queues.
 *
 * @author Riyafa Abdul Hameed
 */
public class MulticastAddressAnycastQueue {
    public static void main(String[] args) throws Exception {
        try (
                ServerLocator locator = ActiveMQClient.createServerLocator("tcp://localhost:61616");
                ClientSessionFactory factory = locator.createSessionFactory();
                ClientSession session = factory.createSession()
        ) {

            // Creating an address with a MULTICAST Routing Type
            String addressName = "multicast_routing_type";
            session.createAddress(new SimpleString(addressName), RoutingType.MULTICAST, true);

            // Creating a queue with ANYCAST Routing Type
            session.createQueue(addressName, RoutingType.ANYCAST, "anycast_routing_type");

        }
    }
}
