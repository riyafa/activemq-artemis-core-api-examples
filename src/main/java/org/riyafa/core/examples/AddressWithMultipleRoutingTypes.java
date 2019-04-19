package org.riyafa.core.examples;

import org.apache.activemq.artemis.api.core.RoutingType;
import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;

import java.util.EnumSet;

/**
 * This demonstrates that an Address can have more than one Routing type. That is an address can be both ANYCAST and
 * MULTICAST.
 *
 * @author Riyafa Abdul Hameed
 */
public class AddressWithMultipleRoutingTypes {
    public static void main(String[] args) throws Exception {
        try (
                ServerLocator locator = ActiveMQClient.createServerLocator("tcp://localhost:61616");
                ClientSessionFactory factory = locator.createSessionFactory();
                ClientSession session = factory.createSession()
        ) {

            // Creating an address with multiple Routing Types
            String addressName = "multi_routing_type";
            session.createAddress(new SimpleString(addressName), EnumSet.of(RoutingType.ANYCAST, RoutingType.MULTICAST),
                                  true);

        }
    }
}
