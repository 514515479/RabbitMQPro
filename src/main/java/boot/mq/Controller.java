package boot.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tobi
 * @Date: 2020/7/9 2:06
 *
 * 测试类
 **/
@RestController
@RequestMapping("/mq")
public class Controller {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(@RequestParam("msg") String msg, @RequestParam("key") String rountingKey) {
        /**
         * 发送消息
         * 参数一：交换机名称
         * 参数二：路由key
         * 参数三：发送的消息
         */
        rabbitTemplate.convertAndSend(RabbitMQConfig.BOOT_TOPIC_EXCHAGE, rountingKey, msg);
        return "发送成功！";
    }
}
