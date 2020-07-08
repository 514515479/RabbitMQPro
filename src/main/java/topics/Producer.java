package topics;

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
 * Topics通配符模式：
 *     Topic类型与Direct相比，都是可以根据RoutingKey把消息路由到不同的队列。只不过Topic类型Exchange可以让队列在绑定Routing key 的时候使用通配符
 * 通配符规则：
 *     #：匹配一个或多个词
 *     *：匹配不多不少恰好1个词
 * 举例：
 *     item.#：能够匹配item.insert.abc 或者 item.insert
 *     item.*：只能匹配item.insert
 *
 * 适用场景
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
        channel.exchangeDeclare(ConnectUtil.TOPIC_EXCHAGE, BuiltinExchangeType.TOPIC);

        //路由key insert 要发送的消息
        String msg1 = "Hello RabbitMQ:insert" ;

        /**
         * 参数一：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数二：路由key，简单模式可以传递队列名称
         * 参数三：消息的其他属性
         * 参数四：消息内容
         */
        channel.basicPublish(ConnectUtil.TOPIC_EXCHAGE, "item.insert", null, msg1.getBytes());

        //路由key update 要发送的消息
        String msg2 = "Hello RabbitMQ:update" ;

        /**
         * 参数一：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数二：路由key，简单模式可以传递队列名称
         * 参数三：消息的其他属性
         * 参数四：消息内容
         */
        channel.basicPublish(ConnectUtil.TOPIC_EXCHAGE, "item.update", null, msg2.getBytes());

        System.out.println("消息已发送...");
        channel.close();
        connection.close();
    }
}
