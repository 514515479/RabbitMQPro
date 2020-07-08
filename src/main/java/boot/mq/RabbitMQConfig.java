package boot.mq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tobi
 * @Date: 2020/7/9 1:53
 *
 * RabbitMQ配置类
 **/
@Configuration
public class RabbitMQConfig {

    //交换机名称（topics）
    public static final String BOOT_TOPIC_EXCHAGE = "boot_topic_exchange";
    //topics模式队列
    public static final String BOOT_TOPIC_QUEUE = "boot_topic_queue";

    //声明交换机
    @Bean("bootTopicExchange")
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(BOOT_TOPIC_EXCHAGE).durable(true).build();  //durabe() 是否持久化
    }

    //声明队列
    @Bean("bootTopicQueue")
    public Queue topicQueue() {
        return QueueBuilder.durable(BOOT_TOPIC_QUEUE).build();  //durabe() 是否持久化
    }

    //绑定交换机和队列
    @Bean
    public Binding queueExchange() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("item.#").noargs();
    }
}
