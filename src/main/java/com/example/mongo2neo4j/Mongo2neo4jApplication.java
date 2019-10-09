package com.example.mongo2neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableNeo4jRepositories
@SpringBootApplication
public class Mongo2neo4jApplication {

  public static void main(String[] args) {
    SpringApplication.run(Mongo2neo4jApplication.class, args);
  }

}
