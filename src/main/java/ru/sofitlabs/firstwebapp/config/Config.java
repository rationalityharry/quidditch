package ru.sofitlabs.firstwebapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"ru.sofitlabs.firstwebapp.controller", "ru.sofitlabs.firstwebapp.data", "ru.sofitlabs.firstwebapp.storage"})
public class Config {
}
