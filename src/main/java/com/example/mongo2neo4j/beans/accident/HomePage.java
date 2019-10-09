package com.example.mongo2neo4j.beans.accident;

import org.springframework.data.mongodb.core.mapping.Field;
public class HomePage extends DescriptionCommonPart{
    @Field("Accident Perspectives:")
    AccidentPerspectives accidentPerspectives;

    public AccidentPerspectives getAccidentPerspectives() {
        return accidentPerspectives;
    }

    public void setAccidentPerspectives(AccidentPerspectives accidentPerspectives) {
        this.accidentPerspectives = accidentPerspectives;
    }
}
