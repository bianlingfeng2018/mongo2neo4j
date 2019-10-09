package com.example.mongo2neo4j.beans.accident;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Transport_Airplane_Site_Map")
public class Accident {
    @Id
    String id;
    String name;
    String url;
    Descriptions descriptions;
    @Field("Related Videos and Animations")
    List<RelatedVideosAndAnimations> relatedVideosAndAnimations;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescriptions(Descriptions descriptions) {
        this.descriptions = descriptions;
    }

    public void setRelatedVideosAndAnimations(List<RelatedVideosAndAnimations> relatedVideosAndAnimations) {
        this.relatedVideosAndAnimations = relatedVideosAndAnimations;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Descriptions getDescriptions() {
        return descriptions;
    }

    public List<RelatedVideosAndAnimations> getRelatedVideosAndAnimations() {
        return relatedVideosAndAnimations;
    }
}
