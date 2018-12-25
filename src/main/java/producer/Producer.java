package producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Scanner;

public class Producer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        try {
            producer.start();
            Message msg;
            SendResult result;
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("请输入");
                String str = sc.next();
                if ("q".equals(str.trim())) {
                    return;
                }
                msg = new Message("test-topic",
                        "push",
                        "1",
                        str.getBytes());
                result = producer.send(msg);
                System.out.println(result.getSendStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
