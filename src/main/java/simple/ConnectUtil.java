package simple;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: tobi
 * @Date: 2020/7/8 19:52
 *
 * 连接
 **/
public class ConnectUtil {

    //队列名称
    public static final String QUEUE_NAME = "simple_queue";

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
