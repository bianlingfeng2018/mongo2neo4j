package com.example.mongo2neo4j.beans.Neo4jVO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;



@RelationshipEntity(type = "hasDescription")
public class Neo4jVOHasDescription {
  @Id
  @GeneratedValue
  Long id;

  @Property(name = "id")
  String bdkeId;

  @JsonIgnore
  @StartNode
  Neo4jVOAccidentNode start;

  @EndNode
  Neo4jVOAccidentNode end;


  public Long getId() {
    return id;
  }

  public String getBdkeId() {
    return bdkeId;
  }

  public Neo4jVOAccidentNode getStart() {
    return start;
  }

  public Neo4jVOAccidentNode getEnd() {
    return end;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setBdkeId(String bdkeId) {
    this.bdkeId = bdkeId;
  }

  public void setStart(Neo4jVOAccidentNode start) {
    this.start = start;
  }

  public void setEnd(Neo4jVOAccidentNode end) {
    this.end = end;
  }

  @Override
  public String toString() {
    return "Neo4jVOHasDescription{" +
            "id=" + id +
            ", bdkeId='" + bdkeId + '\'' +
            ", start=" + start +
            ", end=" + end +
            '}';
  }
}
