package com.example.mongo2neo4j;

import com.example.mongo2neo4j.beans.Neo4jVO.Neo4jVOAccidentNode;
import com.example.mongo2neo4j.beans.Neo4jVO.Neo4jVOHasDescription;
import com.example.mongo2neo4j.beans.movies.Movie;
import com.example.mongo2neo4j.beans.neo4jInterface.HasDescriptionData;
import com.example.mongo2neo4j.beans.neo4jInterface.Neo4jVOAccidentNodeRepository;
import com.example.mongo2neo4j.beans.neo4jInterface.Neo4jVOHasDescriptionRepository;
import com.example.mongo2neo4j.beans.neo4jInterface.Neo4jVOTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jCRUDTest {
  @Autowired
  Neo4jVOAccidentNodeRepository accidentNodeRepository;

  @Autowired
  Neo4jVOTypeRepository typeRepository;

  @Autowired
  Neo4jVOHasDescriptionRepository hasDescriptionRepository;

  // ogm 的用法（待学习）
//  @Autowired
//  Session session;

  /*
   * 1、Repository 的 findById、deleteById 不好使，只能 save、delete 一个含有 id 属性的对象或者写 Query
   * 2、必须先 find 后通过 set 方法增加新的属性，然后再 save 点/边，否则会重新创建/覆盖之前的点/边，而不是把新的属性 merge 进去
   * 3、如果创建节点的时候指定 id，则不会创建成功，因为 id 是 neo4j 内部用的，创建时自动生成，我们只能读取
   */
  // Node
  // ==============================================================================================================
  @Test
  public void testRetrievalNode() {
//    Neo4jVOAccidentNode node = accidentNodeRepository.findByName("T1");
//    List<Neo4jVOAccidentNode> nodes = accidentNodeRepository.findByNameContaining("员");
//    for (Neo4jVOAccidentNode n : nodes) {
//      System.out.println(n.getName());
//    }

    Neo4jVOAccidentNode node = accidentNodeRepository.findById(6224L);
    System.out.println(node);
  }

  @Test
  public void testCreateNode() {
    Neo4jVOAccidentNode node = new Neo4jVOAccidentNode();
    node.setName("T2");
    node.setBdkeId("xxx2019");
    Neo4jVOAccidentNode savedNode = accidentNodeRepository.save(node);
    System.out.println(savedNode.toString());
  }

  @Test
  public void testDeleteNode() {
//    accidentNodeRepository.deleteByBdkeId("xxx2019");

//    Neo4jVOAccidentNode node = new Neo4jVOAccidentNode();
//    node.setId(7994L);
//    accidentNodeRepository.delete(node);

    accidentNodeRepository.deleteById(8178L);
  }

  @Test
  public void testFindAndDeleteNode() {
    List<Neo4jVOAccidentNode> nodes = accidentNodeRepository.findByNameContaining("T1");
    for (Neo4jVOAccidentNode node : nodes) {
      Neo4jVOAccidentNode n = new Neo4jVOAccidentNode();
      n.setId(node.getId());
      accidentNodeRepository.delete(n);
    }
  }

  @Test
  public void testFindAndMergeNode() {
    // 注意，如果不是先find再merge，而是直接new一个id已经存在的对象去save，那么最终效果是替换（旧的属性全部删除）而不是合并
    Neo4jVOAccidentNode node = accidentNodeRepository.findByBdkeId("xxx2019");
    node.setUrl("www.google.com");
    accidentNodeRepository.save(node);
  }

  // Edge
  // ==============================================================================================================
  @Test
  public void testRetrieveEdge() {
    List<Neo4jVOHasDescription> edge = hasDescriptionRepository.findByBdkeId(
            "http://www.semanticweb.org/bianlingfeng/ontologies/2019/4/accident#有事故概述");
    System.out.println(edge.size());
    System.out.println(edge.get(0).toString());
  }

  @Test
  public void testCountSameLabel() {
    long count = hasDescriptionRepository.count();
    System.out.println(count);
  }

  @Test
  public void testFindNodesAndCreateEdge() {
    Neo4jVOAccidentNode start = accidentNodeRepository.findById(8180L);
    Neo4jVOAccidentNode end = accidentNodeRepository.findById(8181L);
    Neo4jVOHasDescription edge = new Neo4jVOHasDescription();
    edge.setStart(start);
    edge.setEnd(end);
    edge.setBdkeId("xxx2019e");
    hasDescriptionRepository.save(edge);
  }

  @Test
  public void testEdgeResultMapping() {
    // 仅返回不带 startNode 和 endNode 信息的边
    // 与现有的类映射不上，所以要自定义结果映射
    // HasDescriptionData{id=19673, bdkeId='xxx2019e'}
    HasDescriptionData edge = hasDescriptionRepository.findById("19673");
  }

  @Test
  public void testFindCompleteEdge() {
    // 如何实现返回一个带有 startNode 和 endNode 的边？
    // 返回子图即可
    Collection<Neo4jVOHasDescription> c = hasDescriptionRepository.findCompleteEdgeById(19673L);
    Iterator<Neo4jVOHasDescription> objs = c.iterator();
    while (objs.hasNext()) {
      Neo4jVOHasDescription obj = objs.next();
      System.out.println(obj);
    }
  }

  @Test
  public void testDeleteEdge() {
    hasDescriptionRepository.deleteById(19672L);
  }

  // Graph
  // ==============================================================================================================
  @Test
  public void testGraph() {
    Collection<Neo4jVOAccidentNode> subGraph = accidentNodeRepository.graph();
    Iterator<Neo4jVOAccidentNode> iterator = subGraph.iterator();
    while (iterator.hasNext()) {
      Neo4jVOAccidentNode obj = iterator.next();
      // 注意，不一定每个 AccidentNode 都持有一个关系 type 或者 hasDescription，所以有可能为 null
      // 其他的关系诸如 subClassOf、subPropertyOf、someValuesFrom 等等还没有对应的类，所以也无法获得其信息
      System.out.println(
              obj.toString());
      System.out.println(obj.getIsA() == null ? null :
              obj.getIsA().get(0).toString());
      System.out.println(obj.getHasDescription() == null ? null :
              obj.getHasDescription().get(0).toString());
    }
  }
}
