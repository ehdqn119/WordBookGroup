package com.example.wordbook;

import com.example.wordbook.Configuraiton.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class TodayTemperatureApplication {


    public static void main(String[] args) {
        SpringApplication.run(TodayTemperatureApplication.class, args);
    }

}
