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

    //交换机名称
    public static final String FANOUT_EXCHAGE = "fanout_exchange";

    //Publish/Subscribe发布与订阅模式队列
    public static final String FANOUT_QUEUES_1 = "fanout_queue1";
    public static final String FANOUT_QUEUES_2 = "fanout_queue2";





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
