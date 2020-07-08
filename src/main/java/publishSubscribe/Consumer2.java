package publishSubscribe;

import com.rabbitmq.client.*;
import utils.ConnectUtil;

import java.io.IOException;

/**
 * @Author: tobi
 * @Date: 2020/7/8 19:49
 *
 * 消费者
 **/
public class Consumer2 {
    public static void main(String[] args) throws Exception{
        //创建连接
        Connection connection = ConnectUtil.getConnection();

        //创建通道
        Channel channel = connection.createChannel();
        //创建交换机
        channel.exchangeDeclare(ConnectUtil.FANOUT_EXCHAGE, BuiltinExchangeType.FANOUT);
        //创建队列
        channel.queueDeclare(ConnectUtil.FANOUT_QUEUES_2, true, false, false, null);
        //队列绑定交换机
        channel.queueBind(ConnectUtil.FANOUT_QUEUES_2, ConnectUtil.FANOUT_EXCHAGE, "");
        //监听消息
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            /**
             *
             * @param consumerTag 消息者标签，在channel.basicConsume时候可以指定
             * @param envelope 消息包内容，可从中获取消息id，消息routingkey，交换机，消息和重转标记（收到消息失败后是否需要重新发送）
             * @param properties 消息属性
             * @param body 消息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //路由key
                System.out.println("路由key为：" + envelope.getRoutingKey());
                //交换机
                System.out.println("交换机为：" + envelope.getExchange());
                //消息id
                System.out.println("消息id为：" + envelope.getDeliveryTag());
                //收到的消息
                System.out.println("接收到的消息：" + new String(body, "UTF-8"));
                System.out.println("================================================================");
            }
        };

        /**
         * 监听消息
         * 参数一：队列名称
         * 参数二：是否自动确认，设置为true表示消息接收到自动向mq回复接收到了，mq接收到回复后会删除消息；设置为false则需要手动确认
         */
        channel.basicConsume(ConnectUtil.FANOUT_QUEUES_2, true, consumer);

        //不关闭资源，应该一直监听消息
        //channel.close();
        //connection.close();
    }
}
