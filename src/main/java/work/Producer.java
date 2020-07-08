package work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectUtil;

/**
 * @Author: tobi
 * @Date: 2020/7/8 19:17
 *
 * 生产者
 *
 * work_queues工作模式：
 *     多个消费端以轮询的方式共同消费同一个队列中的消息（不重复消费）
 * 适用场景
 *     对于任务过重或任务较多情况使用工作队列可以提高任务处理的速度。
 **/
public class Producer {
    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = ConnectUtil.getConnection();
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
        channel.queueDeclare(ConnectUtil.WORK_QUEUES, true, false, false, null);
        //要发送的消息
        for (int i = 0; i < 30; i++) {
            String msg = "Hello RabbitMQ:" + i;
            channel.basicPublish("", ConnectUtil.WORK_QUEUES, null, msg.getBytes());
        }
        System.out.println("消息已发送...");
        channel.close();
        connection.close();
    }
}
