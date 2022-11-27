package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Instant;
import java.util.Properties;
import java.util.Random;
import java.util.TimerTask;

public class OrderProducer extends TimerTask {

   private void  publishMessage(KafkaProducer producer) {

        ProducerRecord<String, Integer> record = new ProducerRecord<>("Restaurant_Order", "Paneer Butter Masala "+ Instant.now(), new Random().nextInt(50));

        try {
            producer.send(record,new OrderCallback());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }

    }

    @Override
    public void run() {

        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");

        KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);
        publishMessage(producer);
    }
}
