package com.example.mongo2neo4j.beans.neo4jInterface;

import com.example.mongo2neo4j.beans.Neo4jVO.Neo4jVOAccidentNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface Neo4jVOAccidentNodeRepository extends Neo4jRepository<Neo4jVOAccidentNode, String> {
    Neo4jVOAccidentNode findByName(String name);
}
