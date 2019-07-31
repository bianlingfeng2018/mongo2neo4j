package com.example.mongo2neo4j.beans.accident;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "Transport_Airplane_Site_Map")
public class Accident {
    @Id
    String id;
    String name;
    String url;
    Descriptions descriptions;
    @Field("Related Videos and Animations")
    List<RelatedVideosAndAnimations> relatedVideosAndAnimations;
}
