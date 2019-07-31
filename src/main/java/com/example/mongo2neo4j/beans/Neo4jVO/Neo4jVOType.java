package com.example.mongo2neo4j.beans.Neo4jVO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
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
}