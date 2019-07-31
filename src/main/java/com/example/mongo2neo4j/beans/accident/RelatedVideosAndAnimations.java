package com.example.mongo2neo4j.beans.accident;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
@Data
public class RelatedVideosAndAnimations {
    String name;
    String url;
    @Field("download url")
    String downloadUrl;
}
