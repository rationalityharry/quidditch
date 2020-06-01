package ru.quidditch.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.quidditch.webapp.core.MailSender;

@Configuration
public class MailConfig {

    @Bean
    public MailSender getMailSender() {
        return new MailSender();
    }
}
