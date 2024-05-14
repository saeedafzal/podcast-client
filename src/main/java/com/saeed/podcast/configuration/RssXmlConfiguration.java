package com.saeed.podcast.configuration;

import com.saeed.podcast.RssXmlRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RssXmlConfiguration {

    @Bean
    public RestTemplate rssXmlRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public RssXmlRepository rssXmlRepository(RestTemplate rssXmlRestTemplate) {
        return new RssXmlRepository(rssXmlRestTemplate);
    }
}
