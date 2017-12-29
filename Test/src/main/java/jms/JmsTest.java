package jms;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.naming.NamingException;

public class JmsTest {
    /**
     * @param args
     * @throws NamingException
     * @throws JMSException
     */
    public static void main(String[] args) throws NamingException, JMSException {
        Qreceiver qreceiver = new Qreceiver("ConnectionFactory", "TEST");
//        TextMessage message = (TextMessage) qreceiver.consumer.receive(10000);
//        System.out.println(message.getText());
    }
}
