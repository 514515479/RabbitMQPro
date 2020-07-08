package publishSubscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectUtil;

/**
 * @Author: tobi
 * @Date: 2020/7/8 19:17
 *
 * 生产者
 *
 * Publish/Subscribe发布与订阅模式：
 *     1、每个消费者监听自己的队列。
 *     2、生产者将消息发给broker，由交换机将消息转发到绑定此交换机的每个队列，每个绑定交换机的队列都将接收到消息
 * 适用场景
 *     对于任务过重或任务较多情况使用工作队列可以提高任务处理的速度。
 *
 * 交换机exchange类型：
 *     1.Fanout：广播，将消息交给所有绑定到交换机的队列
 *     2.Direct：定向，把消息交给符合指定routing key 的队列
 *     3.Topic：通配符，把消息交给符合routing pattern（路由模式） 的队列
 *
 **/
public class Producer {
    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = ConnectUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();

        /**
         * 创建交换机
         * 参数一：交换机名称
         * 参数二：交换机类型（fanout、topic、direct、headers）
         */
        channel.exchangeDeclare(ConnectUtil.FANOUT_EXCHAGE, BuiltinExchangeType.FANOUT);

        /**
         * 创建队列
         * 参数一：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数二：是否定义持久化队列
         * 参数三：是否独占本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其它参数
         */
        channel.queueDeclare(ConnectUtil.FANOUT_QUEUES_1, true, false, false, null);
        channel.queueDeclare(ConnectUtil.FANOUT_QUEUES_2, true, false, false, null);
        //要发送的消息
        for (int i = 0; i < 30; i++) {
            String msg = "Hello RabbitMQ:" + i;
            channel.basicPublish(ConnectUtil.FANOUT_EXCHAGE, "", null, msg.getBytes());
        }
        System.out.println("消息已发送...");
        channel.close();
        connection.close();
    }
}
