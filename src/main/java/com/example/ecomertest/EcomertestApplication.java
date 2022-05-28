package com.example.ecomertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class EcomertestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomertestApplication.class, args);
    }

}
