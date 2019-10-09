package com.example.mongo2neo4j.beans.neo4jInterface;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class HasDescriptionData {
  Long id;
  String bdkeId;

  public String getBdkeId() {
    return bdkeId;
  }

  public void setBdkeId(String bdkeId) {
    this.bdkeId = bdkeId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
