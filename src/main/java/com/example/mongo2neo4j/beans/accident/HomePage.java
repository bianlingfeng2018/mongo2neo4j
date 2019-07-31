package com.example.mongo2neo4j.beans.accident;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
@Data
public class HomePage extends DescriptionCommonPart{
    @Field("Accident Perspectives:")
    AccidentPerspectives accidentPerspectives;
}
