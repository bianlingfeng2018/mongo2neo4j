package com.example.mongo2neo4j.beans.neo4jInterface;

import com.example.mongo2neo4j.beans.Neo4jVO.Neo4jVOAccidentNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface Neo4jVOAccidentNodeRepository extends Neo4jRepository<Neo4jVOAccidentNode, String> {
  Neo4jVOAccidentNode findByName(String name);

  @Query("match (n:AccidentNode) where id(n)={id} return n")
  Neo4jVOAccidentNode findById(@Param(value = "id") Long id);

  Neo4jVOAccidentNode findByBdkeId(String bdkeId);

  Neo4jVOAccidentNode findByType(String type);

  Neo4jVOAccidentNode findByUrl(String url);

  Neo4jVOAccidentNode findByFullDescriptions(String fullDescription);

  Neo4jVOAccidentNode findByVideos(String videos);

  List<Neo4jVOAccidentNode> findByNameContaining(String str);

  @Query("match (n:AccidentNode {id:'xxx2019'}) delete n")
  void deleteByBdkeId(String name);

//  @Override  // 这里没有 Override 可能与 Neo4jRepository 指定的 id 类型不是 Long 有关
  @Query("match (n:AccidentNode) where id(n)={id} delete n")
  void deleteById(@Param("id") Long id);

  @Query("match (n:AccidentNode)-[r]->(m:AccidentNode) return n,r,m limit 10")
  Collection<Neo4jVOAccidentNode> graph();
}
