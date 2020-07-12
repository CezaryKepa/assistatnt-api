package com.businessassistant.assistantapi;

import com.businessassistant.assistantapi.client.ClientMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AssistantApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistantApiApplication.class, args);
    }
    @Bean
    public ClientMapper clientMapper() {
        return new ClientMapper();
    }
}
