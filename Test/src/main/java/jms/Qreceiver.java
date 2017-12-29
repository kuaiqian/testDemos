package jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.command.ActiveMQQueue;

public class Qreceiver implements MessageListener {
    public QueueConnection connection;

    public QueueSession session;

    public MessageConsumer consumer;

    public Qreceiver(String jndiPath, String queue) throws NamingException {
        Context ctx = new InitialContext();
        QueueConnectionFactory conncetionFactory = (QueueConnectionFactory) ctx.lookup(jndiPath);
        try {
            connection = conncetionFactory.createQueueConnection();
            connection.start();
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            ActiveMQQueue destination = new ActiveMQQueue(queue);
            consumer = session.createConsumer(destination);
            // 异步监听器
             consumer.setMessageListener(this);
        }catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println(tm.getText());
            // session.commit();
        }catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
