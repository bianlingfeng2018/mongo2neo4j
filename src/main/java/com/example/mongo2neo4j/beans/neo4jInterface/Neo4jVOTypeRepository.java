package com.example.mongo2neo4j.beans.neo4jInterface;

import com.example.mongo2neo4j.beans.Neo4jVO.Neo4jVOType;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface Neo4jVOTypeRepository extends Neo4jRepository<Neo4jVOType, Long> {
}
