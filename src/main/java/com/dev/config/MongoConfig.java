package com.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.dev.model.converter.DateToTimestampConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new DateToTimestampConverter());
        return new MongoCustomConversions(converters);
    }

    // @Bean
    // public MongoTemplate mongoTemplate() {
    //     String connectionString = "mongodb://mongo:b3A6d16b3gE5h46a2gFGb1Hcefa3cfE1@viaduct.proxy.rlwy.net:24699/test";
    //     return new MongoTemplate(new SimpleMongoClientDatabaseFactory(connectionString));
    // }
}

