package br.com.pleromati;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Exemplo simples de Producer utilizando Kafka
 *
 * @author brunocarneiro
 */
public class Producer {

    private final String BOOTSTRAP_SERVERS;
    private final String TEST_TOPIC;

    public Producer(Properties kafkaProperties) {
        BOOTSTRAP_SERVERS = kafkaProperties.getProperty("kafka_bootstrap_servers");
        TEST_TOPIC = kafkaProperties.getProperty("kafka_topic");
    }

    public static void main(String[] args) {
        try (InputStream in = new FileInputStream("src/main/resources/kafka.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            Producer producer = new Producer(properties);
            producer.produce();
        }
        catch (Exception e) {
            System.err.println("Não foi possível ler o arquivo kafka.properties");
        }
    }

    /**
     * Produz mensagem no tópico
     */
    private void produce() {
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(getProperties())) {
            producer.send(getProducerRecord());
        }
        catch (Exception e) {
            System.err.println("Não foi possível produzir a mensagem.");
        }
    }

    /**
     * Obtém as propriedades do Kafka
     */
    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

    /**
     * Obtém producer record
     */
    private ProducerRecord<String, String> getProducerRecord() {
        return new ProducerRecord<>(TEST_TOPIC, "Bem-vindo ao Kafka!");
    }
}
