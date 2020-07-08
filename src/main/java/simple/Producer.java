package simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: tobi
 * @Date: 2020/7/8 19:17
 *
 * 生产者
 **/
public class Producer {
    private static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址
        connectionFactory.setHost("111.229.241.164");
        //端口
        connectionFactory.setPort(5672);
        //用户名
        connectionFactory.setUsername("user");
        //密码
        connectionFactory.setPassword("nuttertools");
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        /**
         * 创建队列
         * 参数一：队列名称
         * 参数二：是否定义持久化队列
         * 参数三：是否独占本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其它参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //要发送的消息
        String msg = "Hello RabbitMQ!";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("消息已发送...");
        channel.close();
        connection.close();
    }
}
