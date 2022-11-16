package ir.webold.kafka.producer.config;

import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import ir.webold.kafka.producer.KafkaProducerApplication;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;
    public Map<String,Object> props(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return props;
    }
    @Bean
    public ProducerFactory<String, KafkaProducerApplication.SamplePojo> producerFactory(){
        return new DefaultKafkaProducerFactory<>(props());
    }
    @Bean
    public KafkaTemplate<String, KafkaProducerApplication.SamplePojo
            > kafkaTemplate(ProducerFactory<String, KafkaProducerApplication.SamplePojo> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
