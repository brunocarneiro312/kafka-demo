package br.com.pleromati;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Exemplo simples de Producer utilizando Kafka
 *
 * @author brunocarneiro
 */
public class Consumer {

    private final String BOOTSTRAP_SERVERS;
    private final String GROUP_1;
    private final String TEST_TOPIC;
    private final String STRATEGY;

    public Consumer(Properties kafkaProperties) {
        BOOTSTRAP_SERVERS = kafkaProperties.getProperty("kafka_bootstrap_servers");
        GROUP_1 = kafkaProperties.getProperty("kafka_group");
        TEST_TOPIC = kafkaProperties.getProperty("kafka_topic");
        STRATEGY = kafkaProperties.getProperty("kafka_strategy");
    }

    public static void main(String[] args) {
        try (InputStream in = new FileInputStream("src/main/resources/kafka.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            Consumer consumer = new Consumer(properties);
            consumer.consume();
        }
        catch (Exception e) {
            System.err.println("Não foi possível ler o arquivo kafka.properties");
        }
    }

    private void consume() {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getProperties())) {
            consumer.subscribe(Collections.singletonList(TEST_TOPIC));
            while (true) {
                consumer.poll(Duration.ofDays(7)).forEach(System.out::println);
            }
        }
        catch (Exception e) {
            System.err.println("Não foi possível consumir a mensagem.");
            e.printStackTrace();
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_1);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, STRATEGY);
        return properties;
    }
}
