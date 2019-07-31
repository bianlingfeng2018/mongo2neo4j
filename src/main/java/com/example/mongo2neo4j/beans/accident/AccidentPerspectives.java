package com.example.mongo2neo4j.beans.accident;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class AccidentPerspectives {
    @Field("Airplane Life Cycle")
    String airplaneLifeCycle;
    @Field("Accident Threat Categories")
    String AccidentThreatCategories;
    @Field("Groupings")
    String Groupings;
    @Field("Accident Common Themes")
    String AccidentCommonThemes;
}
