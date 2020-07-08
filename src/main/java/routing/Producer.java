package routing;

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
 * Routing路由模式：
 *     1.队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由key）
 *     2.消息的发送方在向 Exchange发送消息时，也必须指定消息的 RoutingKey。
 *     3.Exchange不再把消息交给每一个绑定的队列，而是根据消息的RoutingKey进行判断，只有队列的Routingkey与消息的 Routing key完全一致，才会接收到消息
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
        channel.exchangeDeclare(ConnectUtil.DIRECT_EXCHAGE, BuiltinExchangeType.DIRECT);

        /**
         * 创建队列
         * 参数一：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数二：是否定义持久化队列
         * 参数三：是否独占本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其它参数
         */
        channel.queueDeclare(ConnectUtil.DIRECT_QUEUE_INSERT, true, false, false, null);
        channel.queueDeclare(ConnectUtil.DIRECT_QUEUE_UPDATE, true, false, false, null);

        //队列绑定交换机
        channel.queueBind(ConnectUtil.DIRECT_QUEUE_INSERT, ConnectUtil.DIRECT_EXCHAGE, "insert");
        channel.queueBind(ConnectUtil.DIRECT_QUEUE_UPDATE, ConnectUtil.DIRECT_EXCHAGE, "update");

        //路由key insert 要发送的消息
        String msg1 = "Hello RabbitMQ:insert" ;

        /**
         * 参数一：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数二：路由key，简单模式可以传递队列名称
         * 参数三：消息的其他属性
         * 参数四：消息内容
         */
        channel.basicPublish(ConnectUtil.DIRECT_EXCHAGE, "insert", null, msg1.getBytes());

        //路由key update 要发送的消息
        String msg2 = "Hello RabbitMQ:update" ;

        /**
         * 参数一：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数二：路由key，简单模式可以传递队列名称
         * 参数三：消息的其他属性
         * 参数四：消息内容
         */
        channel.basicPublish(ConnectUtil.DIRECT_EXCHAGE, "update", null, msg2.getBytes());

        System.out.println("消息已发送...");
        channel.close();
        connection.close();
    }
}
