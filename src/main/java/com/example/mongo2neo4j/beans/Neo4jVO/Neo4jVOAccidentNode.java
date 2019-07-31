package com.example.mongo2neo4j.beans.Neo4jVO;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.Map;

@Data
@NodeEntity(label = "AccidentNode")
public class Neo4jVOAccidentNode {
    // AccidentNode 公有属性
    @Id
    @GeneratedValue
    Long id;
    @Property("id")
    String bdkeId;
    @Property("name")
    String name;
    @Property("type")
    String type;

    // AccidentNode 顶级属性
    @Property("url")
    String url;
    @Property("fullDescriptions")
    String fullDescriptions;
    @Property("video")
    String videos;

    // AccidentNode 子节点属性
    @Properties(delimiter = "_", prefix = "BDKE")
    Map<String, String> properties;

    // 出边、入边
    @Relationship(type = "type") private Neo4jVOType isA;
    @Relationship(type = "hasDescription") private Neo4jVOHasDescription hasDescription;
}
