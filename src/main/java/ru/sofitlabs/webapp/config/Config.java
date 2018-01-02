package ru.sofitlabs.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"ru.sofitlabs.webapp.controller", "ru.sofitlabs.webapp.data", "ru.sofitlabs.webapp.storage"})
public class Config {
}
