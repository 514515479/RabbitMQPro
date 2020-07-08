package boot.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: tobi
 * @Date: 2020/7/9 2:12
 *
 * 监听队列的消息（消费者）
 **/
@Component
public class Listener {

    @RabbitListener(queues = RabbitMQConfig.BOOT_TOPIC_QUEUE)
    public void listen(String msg) {
        System.out.println("消费者收到的消息为：" + msg);
    }
}
