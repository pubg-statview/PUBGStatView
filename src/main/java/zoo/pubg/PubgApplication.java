package zoo.pubg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "zoo.pubg.service.api")
public class PubgApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubgApplication.class, args);
    }
}
