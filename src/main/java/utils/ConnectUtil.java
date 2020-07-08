package utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: tobi
 * @Date: 2020/7/8 19:52
 *
 * 连接
 **/
public class ConnectUtil {

    //简单队列
    public static final String SIMPLE_QUEUE = "simple_queue";

    //work_queues工作模式队列
    public static final String WORK_QUEUES = "work_queue";

    //交换机名称（fanout）
    public static final String FANOUT_EXCHAGE = "fanout_exchange";

    //Publish/Subscribe发布与订阅模式队列
    public static final String FANOUT_QUEUES_1 = "fanout_queue1";
    public static final String FANOUT_QUEUES_2 = "fanout_queue2";

    //交换机名称（direct）
    public static final String DIRECT_EXCHAGE = "direct_exchange";
    //rounting模式队列
    public static final String DIRECT_QUEUE_INSERT = "direct_queue_insert";
    //rounting模式队列
    public static final String DIRECT_QUEUE_UPDATE = "direct_queue_update";

    //交换机名称（topics）
    public static final String TOPIC_EXCHAGE = "topic_exchange";
    //topics模式队列
    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    //topics模式队列
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";

    public static Connection getConnection() throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址
        connectionFactory.setHost("123");
        //端口
        connectionFactory.setPort(5672);
        //用户名
        connectionFactory.setUsername("123");
        //密码
        connectionFactory.setPassword("123");
         //创建连接
        return connectionFactory.newConnection();
    }
}
