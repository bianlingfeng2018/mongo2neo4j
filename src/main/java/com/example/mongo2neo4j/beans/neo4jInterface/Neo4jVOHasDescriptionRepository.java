package com.example.mongo2neo4j.beans.neo4jInterface;


import com.example.mongo2neo4j.beans.Neo4jVO.Neo4jVOHasDescription;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface Neo4jVOHasDescriptionRepository extends Neo4jRepository<Neo4jVOHasDescription, Long> {
  List<Neo4jVOHasDescription> findByBdkeId(String bdkeId);

  @Query("match ()-[r:hasDescription]->() where id(r)={id} return r.id as bdkeId,id(r) as id")
  HasDescriptionData findById(@Param("id") String id);

  @Query("match ()-[r:hasDescription]->() where id(r)={id} delete r")
  void deleteById(@Param("id") Long id);

  @Query("match (n:AccidentNode)-[r:hasDescription]->(m:AccidentNode) where id(r)={id} return n,r,m")
  Collection<Neo4jVOHasDescription> findCompleteEdgeById(@Param("id") long id);
}

