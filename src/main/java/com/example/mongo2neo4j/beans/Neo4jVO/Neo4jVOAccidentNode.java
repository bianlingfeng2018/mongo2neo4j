package com.example.mongo2neo4j.beans.Neo4jVO;

import org.neo4j.ogm.annotation.*;

import java.util.List;
import java.util.Map;


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
  @Relationship(type = "type")
  private List<Neo4jVOType> isA;

  @Relationship(type = "hasDescription")
  private List<Neo4jVOHasDescription> hasDescription;

  public List<Neo4jVOType> getIsA() {
    return isA;
  }

  public void setIsA(List<Neo4jVOType> isA) {
    this.isA = isA;
  }

  public List<Neo4jVOHasDescription> getHasDescription() {
    return hasDescription;
  }

  public void setHasDescription(List<Neo4jVOHasDescription> hasDescription) {
    this.hasDescription = hasDescription;
  }

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getFullDescriptions() {
    return fullDescriptions;
  }

  public void setFullDescriptions(String fullDescriptions) {
    this.fullDescriptions = fullDescriptions;
  }

  public String getVideos() {
    return videos;
  }

  public void setVideos(String videos) {
    this.videos = videos;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }

  @Override
  public String toString() {
    return "Neo4jVOAccidentNode{" +
            "id=" + id +
            ", bdkeId='" + bdkeId + '\'' +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", url='" + url + '\'' +
            ", fullDescriptions='" + fullDescriptions + '\'' +
            ", videos='" + videos + '\'' +
            ", properties=" + properties +
//            ", Neo4jVOType.size is " + isA.size() +
//            ", Neo4jVOHasDescription.size is " + hasDescription.size() +
            '}';
  }
}
