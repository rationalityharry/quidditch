package ru.sofitlabs.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "ru.sofitlabs.webapp.config")
public class WebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }
}
