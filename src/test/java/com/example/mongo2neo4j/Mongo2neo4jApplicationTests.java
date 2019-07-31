package com.example.mongo2neo4j;

import com.alibaba.fastjson.JSON;
import com.example.mongo2neo4j.beans.Neo4jVO.*;
import com.example.mongo2neo4j.beans.accident.*;
import com.example.mongo2neo4j.beans.neo4jInterface.*;
import com.example.mongo2neo4j.beans.person.Person;
import com.mongodb.MongoClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.neo4j.driver.v1.Values.parameters;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mongo2neo4jApplicationTests {
    // mongo test
    @Autowired
    MongoClient mongoClient;
    private static final Log log = LogFactory.getLog(Mongo2neo4jApplication.class);

    @Test
    public void javaRegisterMongoClientTest() {
        MongoOperations mongoOps = new MongoTemplate(mongoClient, "database");
        mongoOps.insert(new Person("Alice", 34));
        log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Person.class));
//        mongoOps.dropCollection("person");
    }

    @Test
    public void CRUDTest() {
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "database"));

        Person p = new Person("Joe", 24);

        // Insert is used to initially store the object into the database.
        mongoOps.insert(p);
        log.info("Insert: " + p);

        // Find
        p = mongoOps.findById(p.getId(), Person.class);
        log.info("Found: " + p);

        // Update
        mongoOps.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        p = mongoOps.findOne(query(where("name").is("Joe")), Person.class);
        log.info("Updated: " + p);

        // Delete
//        mongoOps.remove(p);

        // Check that deletion worked
        List<Person> people = mongoOps.findAll(Person.class);
        log.info("Number of people = " + people.size());

//        mongoOps.dropCollection(Person.class);
    }

    @Test
    public void findAccidentTest() {
        MongoOperations mongoOps = new MongoTemplate(mongoClient, "accidentdb");
//        MongoCollection<Document> collection = mongoOps.getCollection("Transport_Airplane_Site_Map");
//        MongoCursor<Document> iterator = collection.find().iterator();
//        while (iterator.hasNext()){
//            JSONObject jsonObject = JSON.parseObject(iterator.next().toJson());
//            System.out.println(jsonObject.getString("_id"));
//        }
        List<Accident> accidents = mongoOps.findAll(Accident.class);
        int i = 0;
        for (Accident a : accidents) {
//            String s = a.getDescriptions().getHomePage().getAccidentPerspectives().getAirplaneLifeCycle();
            String s = a.getDescriptions().getHomePage().getDescription().replaceAll("\\n", "<br>");
            System.out.println(s);
            if (++i == 1) {
                break;
            }
        }
    }

    // neo4j test
    @Autowired
    Neo4jVOAccidentNodeRepository neo4jVOAccidentNodeRepository;
    @Autowired
    Neo4jVOTypeRepository neo4jVOTypeRepository;
    @Autowired
    Neo4jVOHasDescriptionRepository neo4jVOHasDescriptionRepository;

    @Test
    public void driverTest() {
        Driver driver = null;
        Session session = null;
        try {
            driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123456"));
            session = driver.session();
            StatementResult result = session.run("MATCH (n)" +
                            "RETURN n",
                    parameters());
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> map = record.asMap();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (driver != null) {
                driver.close();
            }
        }
    }

    @Test
    public void neo4jRepositorySaveTest() {
        Neo4jVOAccidentNode vo = new Neo4jVOAccidentNode();
        vo.setBdkeId("http://www.semanticweb.org/bianlingfeng/ontologies/2019/4/accident#测试事故4");
        vo.setName("测试事故4");
        vo.setType("NamedIndividual");
        vo.setUrl("测试url4");
        HomePage homePage = new HomePage();
        ArrayList<Pdfs> pdfsArrayList = new ArrayList<>();
        Pdfs pdfs = new Pdfs();
        pdfs.setName("pdfName");
        pdfs.setUrl("pdfUrl");
        pdfsArrayList.add(pdfs);
        homePage.setPdfs(pdfsArrayList);
        AccidentPerspectives perspectives = new AccidentPerspectives();
        perspectives.setAccidentCommonThemes("主題");
        perspectives.setAccidentThreatCategories("威胁");
        perspectives.setAirplaneLifeCycle("生命周期");
        perspectives.setGroupings("分组");
        homePage.setAccidentPerspectives(perspectives);
        homePage.setDescription("测试描述");
        homePage.setUrl("描述页面url");
        Descriptions desc = new Descriptions();
        desc.setHomePage(homePage);
        vo.setFullDescriptions(JSON.toJSONString(desc));
        Neo4jVOAccidentNode save = neo4jVOAccidentNodeRepository.save(vo);
        System.out.println(save.getId());
    }

    @Test
    public void neo4jRepositorySaveRelationshipTest() {
        Neo4jVOAccidentNode start = new Neo4jVOAccidentNode();
        start.setId(27L);
        Neo4jVOAccidentNode end = new Neo4jVOAccidentNode();
        end.setId(1189L);
        Neo4jVOType isA = new Neo4jVOType();
        isA.setStart(start);
        isA.setEnd(end);
        Neo4jVOType a = neo4jVOTypeRepository.save(isA);
        System.out.println(a.getId());
    }

    @Test
    public void neo4jRepositorySaveStartNodeRelationshipTest() {
        Neo4jVOAccidentNode vo = new Neo4jVOAccidentNode();
        vo.setBdkeId("http://www.semanticweb.org/bianlingfeng/ontologies/2019/4/accident#测试事故7");
        vo.setName("测试事故7");
        vo.setType("NamedIndividual");
        vo.setUrl("测试url");
        HomePage homePage = new HomePage();
        ArrayList<Pdfs> pdfsArrayList = new ArrayList<>();
        Pdfs pdfs = new Pdfs();
        pdfs.setName("pdfName");
        pdfs.setUrl("pdfUrl");
        pdfsArrayList.add(pdfs);
        homePage.setPdfs(pdfsArrayList);
        AccidentPerspectives perspectives = new AccidentPerspectives();
        perspectives.setAccidentCommonThemes("主題");
        perspectives.setAccidentThreatCategories("威胁");
        perspectives.setAirplaneLifeCycle("生命周期");
        perspectives.setGroupings("分组");
        homePage.setAccidentPerspectives(perspectives);
        homePage.setDescription("测试描述");
        homePage.setUrl("描述页面url");
        Descriptions desc = new Descriptions();
        desc.setHomePage(homePage);
        vo.setFullDescriptions(JSON.toJSONString(desc));
        System.out.println(JSON.toJSONString(desc));
        Neo4jVOType isA = new Neo4jVOType();
        isA.setBdkeId("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        Neo4jVOAccidentNode end = new Neo4jVOAccidentNode();
        end.setId(1189L);
        isA.setStart(vo);
        isA.setEnd(end);
        Neo4jVOType a = neo4jVOTypeRepository.save(isA);
        System.out.println(a.getId());
    }

    @Test
    public void mongo2neo4jByRepository() {
        MongoOperations mongoOps = new MongoTemplate(mongoClient, "accidentdb");
        List<Accident> accidents = mongoOps.findAll(Accident.class);
        int i = 0;
        for (Accident a : accidents) {
            Long id = saveNode(a);
//            if (++i == 1) {
//                break;
//            }
        }
    }

    private Descriptions d;
    private String baseUrl = "http://www.semanticweb.org/bianlingfeng/ontologies/2019/4/accident#";
    private String[] params = {
            "事故",
            "事故梗概",
            "事故委员会调查结果",
            "事故委员会建议",
            "事故适航指令",
            "事故共同主题",
            "事故关键安全问题",
            "事故教训",
            "事故前车之鉴",
            "事故文化组织因素",
            "事故相关事件",
            "事故安全举措",
            "事故相关法规_政策_背景",
            "事故安全假设",
            "事故概述"
    };  // 事故的id，事故梗概的id，...

    private Long saveNode(Accident a) {
        // get node from mongodb
        String id = baseUrl + a.getId();
        String name = a.getName();
        String url = a.getUrl();
        String videos = JSON.toJSONString(a.getRelatedVideosAndAnimations());
        d = a.getDescriptions();
        // reform node properties
        HomePage homePage = d.getHomePage();
        DescriptionCommonPart accidentBoardFindings = d.getAccidentBoardFindings();
        DescriptionCommonPart accidentBoardRecommendations = d.getAccidentBoardRecommendations();
        DescriptionCommonPart accidentOverview = d.getAccidentOverview();
        DescriptionCommonPart airworthinessDirectivesIssued = d.getAirworthinessDirectivesIssued();
        DescriptionCommonPart keySafetyIssues = d.getKeySafetyIssues();
        DescriptionCommonPart lessonsLearned = d.getLessonsLearned();
        DescriptionCommonPart precursors = d.getPrecursors();
        DescriptionCommonPart prevailingCulturalOrganizationalFactors = d.getPrevailingCulturalOrganizationalFactors();
        DescriptionCommonPart relevantRegulationsPolicyBackground = d.getRelevantRegulationsPolicyBackground();
        DescriptionCommonPart resultingSafetyInitiatives = d.getResultingSafetyInitiatives();
        DescriptionCommonPart safetyAssumptions = d.getSafetyAssumptions();
        DescriptionCommonPart commonThemes = d.getCommonThemes();
        DescriptionCommonPart relatedAccidentsIncidents = d.getRelatedAccidentsIncidents();
        homePage.setDescription(getHTMLString(homePage));
        accidentBoardFindings.setDescription(getHTMLString(accidentBoardFindings));
        accidentBoardRecommendations.setDescription(getHTMLString(accidentBoardRecommendations));
        accidentOverview.setDescription(getHTMLString(accidentOverview));
        airworthinessDirectivesIssued.setDescription(getHTMLString(airworthinessDirectivesIssued));
        keySafetyIssues.setDescription(getHTMLString(keySafetyIssues));
        lessonsLearned.setDescription(getHTMLString(lessonsLearned));
        precursors.setDescription(getHTMLString(precursors));
        prevailingCulturalOrganizationalFactors.setDescription(getHTMLString(prevailingCulturalOrganizationalFactors));
        relevantRegulationsPolicyBackground.setDescription(getHTMLString(relevantRegulationsPolicyBackground));
        resultingSafetyInitiatives.setDescription(getHTMLString(resultingSafetyInitiatives));
        safetyAssumptions.setDescription(getHTMLString(safetyAssumptions));
        commonThemes.setDescription(getHTMLString(commonThemes));
        relatedAccidentsIncidents.setDescription(getHTMLString(relatedAccidentsIncidents));
        d.setHomePage(homePage);
        d.setAccidentBoardFindings(accidentBoardFindings);
        d.setAccidentBoardRecommendations(accidentBoardRecommendations);
        d.setAccidentOverview(accidentOverview);
        d.setAirworthinessDirectivesIssued(airworthinessDirectivesIssued);
        d.setCommonThemes(commonThemes);
        d.setKeySafetyIssues(keySafetyIssues);
        d.setLessonsLearned(lessonsLearned);
        d.setPrecursors(precursors);
        d.setPrevailingCulturalOrganizationalFactors(prevailingCulturalOrganizationalFactors);
        d.setRelatedAccidentsIncidents(relatedAccidentsIncidents);
        d.setResultingSafetyInitiatives(resultingSafetyInitiatives);
        d.setRelevantRegulationsPolicyBackground(relevantRegulationsPolicyBackground);
        d.setSafetyAssumptions(safetyAssumptions);
        // save current node and relationship
        Neo4jVOAccidentNode node = new Neo4jVOAccidentNode();
        node.setBdkeId(id);
        node.setName(name);
        node.setType("NamedIndividual");
        node.setUrl(url);
        node.setVideos(videos);
        node.setFullDescriptions(getJSONString(d));
        Neo4jVOAccidentNode start = neo4jVOAccidentNodeRepository.save(node);
//        Neo4jVOAccidentClass end = new Neo4jVOAccidentClass();
//        end.setId(params[0]);
        Neo4jVOAccidentNode end = neo4jVOAccidentNodeRepository.findByName(params[0]);
        Neo4jVOType isA = new Neo4jVOType();
        isA.setBdkeId(baseUrl + "type");
        isA.setStart(start);
        isA.setEnd(end);
//        System.out.println(isA);
        Neo4jVOType e = neo4jVOTypeRepository.save(isA);
        // save linked out node and relationship
        // homePage
        Neo4jVOAccidentNode start2 = new Neo4jVOAccidentNode();
        start2.setBdkeId(baseUrl + "HomePage For " + name);
        start2.setName("HomePage For " + name);
        start2.setType("NamedIndividual");
        HashMap<String, String> properties = new HashMap<>();
        properties.put("url", homePage.getUrl());
        properties.put("description", homePage.getDescription());
        properties.put("pictures", getJSONString(homePage.getPictures()));
        properties.put("pdfs", getJSONString(homePage.getPdfs()));
        properties.put("perspective", getJSONString(homePage.getAccidentPerspectives()));
        start2.setProperties(properties);
        neo4jVOAccidentNodeRepository.save(start2);
//        Neo4jVODescriptionClass end2 = new Neo4jVODescriptionClass();
//        end2.setId(params[1]);
        Neo4jVOAccidentNode end2 = neo4jVOAccidentNodeRepository.findByName(params[1]);
        Neo4jVOType isA2 = new Neo4jVOType();
        isA2.setBdkeId(baseUrl + "type");
        isA2.setStart(start2);
        isA2.setEnd(end2);
//        System.out.println(isA2);
        neo4jVOTypeRepository.save(isA2);
        Neo4jVOHasDescription hasDescription = new Neo4jVOHasDescription();
        hasDescription.setBdkeId(baseUrl + "有事故梗概");
        hasDescription.setStart(start);
        hasDescription.setEnd(start2);
        neo4jVOHasDescriptionRepository.save(hasDescription);
        // descriptionCommonPart ...
        saveDescriptionCommonPart("AccidentBoardFindings", name, accidentBoardFindings, start, 2, "有事故委员会调查结果");
        saveDescriptionCommonPart("AccidentBoardRecommendations", name, accidentBoardRecommendations, start, 3, "有事故委员会建议");
        saveDescriptionCommonPart("AirworthinessDirectivesIssued", name, airworthinessDirectivesIssued, start, 4, "有事故适航指令");
        saveDescriptionCommonPart("CommonThemes", name, commonThemes, start, 5, "有事故共同主题");
        saveDescriptionCommonPart("KeySafetyIssues", name, keySafetyIssues, start, 6, "有事故关键安全问题");
        saveDescriptionCommonPart("LessonsLearned", name, lessonsLearned, start, 7, "有事故教训");
        saveDescriptionCommonPart("Precursors", name, precursors, start, 8, "有事故前车之鉴");
        saveDescriptionCommonPart("PrevailingCulturalOrganizationalFactors", name, prevailingCulturalOrganizationalFactors, start, 9, "有事故文化组织因素");
        saveDescriptionCommonPart("RelatedAccidentsIncidents", name, relatedAccidentsIncidents, start, 10, "有事故相关事件");
        saveDescriptionCommonPart("ResultingSafetyInitiatives", name, resultingSafetyInitiatives, start, 11, "有事故安全举措");
        saveDescriptionCommonPart("RelevantRegulationsPolicyBackground", name, relevantRegulationsPolicyBackground, start, 12, "有事故相关法规_政策_背景");
        saveDescriptionCommonPart("SafetyAssumptions", name, safetyAssumptions, start, 13, "有事故安全假设");
        saveDescriptionCommonPart("AccidentOverview", name, accidentOverview, start, 14, "有事故概述");
        return start.getId();
    }

    private void saveDescriptionCommonPart(String namePrefix, String nameSuffix, DescriptionCommonPart part, Neo4jVOAccidentNode start, int i, String relationship) {
        Neo4jVOAccidentNode start2 = new Neo4jVOAccidentNode();
        start2.setBdkeId(baseUrl + namePrefix + " For " + nameSuffix);
        start2.setName(namePrefix + " For " + nameSuffix);
        start2.setType("NamedIndividual");
        HashMap<String, String> properties = new HashMap<>();
        properties.put("url", part.getUrl());
        properties.put("description", part.getDescription());
        properties.put("pictures", getJSONString(part.getPictures()));
        properties.put("pdfs", getJSONString(part.getPdfs()));
        start2.setProperties(properties);
        neo4jVOAccidentNodeRepository.save(start2);
//        Neo4jVODescriptionClass end2 = new Neo4jVODescriptionClass();
//        end2.setId(params[i]);
        Neo4jVOAccidentNode end2 = neo4jVOAccidentNodeRepository.findByName(params[i]);
        Neo4jVOType isA2 = new Neo4jVOType();
        isA2.setBdkeId(baseUrl + "type");
        isA2.setStart(start2);
        isA2.setEnd(end2);
//        System.out.println(isA2);
        neo4jVOTypeRepository.save(isA2);
        Neo4jVOHasDescription hasDescription = new Neo4jVOHasDescription();
        hasDescription.setBdkeId(baseUrl + relationship);
        hasDescription.setStart(start);
        hasDescription.setEnd(start2);
        neo4jVOHasDescriptionRepository.save(hasDescription);
    }

    private String getHTMLString(DescriptionCommonPart d) {
        return d.getDescription().replaceAll("\\n", "<br><br>");
    }

    private String getJSONString(Object o) {
        return JSON.toJSONString(o);
    }

    @Test
    public void mongo2neo4jByDriver() {
//        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123456"));
//        Session session = driver.session();
//
//        MongoOperations mongoOps = new MongoTemplate(mongoClient, "accidentdb");
//        List<Accident> accidents = mongoOps.findAll(Accident.class);
//        int i = 0;
//        for (Accident a : accidents) {
//            String s = a.getDescriptions().getHomePage().getDescription().replaceAll("\\n", "<br>");
//            System.out.println(s);
//            if (++i == 1) {
//                break;
//            }
//        }
//
//        session.run("MERGE (n:`AccidentNode` {id:{id},name:{name}});",  // 添加起点（不可重复）
//                parameters("id", "",
//                        "name", ""));
//
//        session.run("MERGE (n:`AccidentNode` {id:{id},name:{name}});",  // 添加终点（不可重复）
//                parameters("id", "",
//                        "name", ""));
//
//        session.run("MATCH (src:`AccidentNode` {id:{p1}}), (dest:`AccidentNode` {id:{p2}}) MERGE (src)-[e:`" + "e-name" + "` {id:{p3}}]->(dest);",  // 添加边（不可重复）
//                parameters(
//                        "p1", "",
//                        "p2", "",
//                        "p3", "e-url"));
    }

    @Test
    public void neo4jFindTest(){
        Neo4jVOAccidentNode a = neo4jVOAccidentNodeRepository.findByName("事故");
        System.out.println(a);
    }
}
