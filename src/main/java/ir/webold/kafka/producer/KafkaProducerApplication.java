package ir.webold.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class KafkaProducerApplication {
	public final DateFormat dateFormat =new SimpleDateFormat("YYYY/MM/dd hh:mm:ss");
	    @Value("${kafka.topic.name}")
	    private String topicName;
		public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,SamplePojo> template){
		return args -> {
			template.send(topicName,new SamplePojo("hossein","mahdevar"));

		};
	}

	public static class SamplePojo{
			private String name;
			private String family;

		public SamplePojo(String name, String family) {
			this.name = name;
			this.family = family;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFamily() {
			return family;
		}

		public void setFamily(String family) {
			this.family = family;
		}
	}

}
