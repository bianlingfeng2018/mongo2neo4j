package com.example.mongo2neo4j.beans.Neo4jVO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "type")
public class Neo4jVOType {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getBdkeId() {
        return bdkeId;
    }

    public void setBdkeId(String bdkeId) {
        this.bdkeId = bdkeId;
    }

    public Neo4jVOAccidentNode getStart() {
        return start;
    }

    public void setStart(Neo4jVOAccidentNode start) {
        this.start = start;
    }

    public Neo4jVOAccidentNode getEnd() {
        return end;
    }

    public void setEnd(Neo4jVOAccidentNode end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Neo4jVOType{" +
                "id=" + id +
                ", bdkeId='" + bdkeId + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
