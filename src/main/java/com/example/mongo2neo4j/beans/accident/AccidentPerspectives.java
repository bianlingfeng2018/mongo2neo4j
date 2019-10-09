package com.example.mongo2neo4j.beans.accident;

import org.springframework.data.mongodb.core.mapping.Field;

public class AccidentPerspectives {
    @Field("Airplane Life Cycle")
    String airplaneLifeCycle;
    @Field("Accident Threat Categories")
    String AccidentThreatCategories;
    @Field("Groupings")
    String Groupings;
    @Field("Accident Common Themes")
    String AccidentCommonThemes;

    public String getAirplaneLifeCycle() {
        return airplaneLifeCycle;
    }

    public void setAirplaneLifeCycle(String airplaneLifeCycle) {
        this.airplaneLifeCycle = airplaneLifeCycle;
    }

    public String getAccidentThreatCategories() {
        return AccidentThreatCategories;
    }

    public void setAccidentThreatCategories(String accidentThreatCategories) {
        AccidentThreatCategories = accidentThreatCategories;
    }

    public String getGroupings() {
        return Groupings;
    }

    public void setGroupings(String groupings) {
        Groupings = groupings;
    }

    public String getAccidentCommonThemes() {
        return AccidentCommonThemes;
    }

    public void setAccidentCommonThemes(String accidentCommonThemes) {
        AccidentCommonThemes = accidentCommonThemes;
    }
}
