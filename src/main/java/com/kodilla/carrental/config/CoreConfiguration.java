package com.kodilla.carrental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

@Configuration
public class CoreConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        MappingJackson2HttpMessageConverter textJsonConverter = new MappingJackson2HttpMessageConverter();
        textJsonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(textJsonConverter);

        return restTemplate;
    }
}
