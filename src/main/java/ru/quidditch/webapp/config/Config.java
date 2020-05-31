package ru.quidditch.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"ru.quidditch.webapp.controller", "ru.quidditch.webapp.data"})
public class Config {


}
