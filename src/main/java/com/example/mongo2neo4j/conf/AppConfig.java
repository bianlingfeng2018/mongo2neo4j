package com.example.mongo2neo4j.conf;

import com.mongodb.MongoClient;
import org.neo4j.driver.internal.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

    /*
     * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
     */
//    public @Bean
//    MongoClient mongoClient() {
//        return new MongoClient("localhost");
//    }
//
//    public @Bean
//    MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), "database");
//    }

    /*
     * Configuration for neo4j
     */
    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri("bolt://localhost")
                .credentials("neo4j", "123456")
                .build();
        return configuration;
    }
}