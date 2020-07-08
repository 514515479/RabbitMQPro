package simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectUtil;

/**
 * @Author: tobi
 * @Date: 2020/7/8 19:17
 *
 * 生产者
 **/
public class Producer {
    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = ConnectUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        /**
         * 创建队列
         * 参数一：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数二：是否定义持久化队列
         * 参数三：是否独占本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其它参数
         */
        channel.queueDeclare(ConnectUtil.SIMPLE_QUEUE, true, false, false, null);
        //要发送的消息
        String msg = "Hello RabbitMQ!";
        channel.basicPublish("", ConnectUtil.SIMPLE_QUEUE, null, msg.getBytes());
        System.out.println("消息已发送...");
        channel.close();
        connection.close();
    }
}
