package com.example.mongo2neo4j.beans.accident;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Descriptions {
    @Field(value = "Home page")
    HomePage homePage;
    @Field(value = "Accident Overview")
    DescriptionCommonPart AccidentOverview;
    @Field(value = "Accident Board Findings")
    DescriptionCommonPart AccidentBoardFindings;
    @Field(value = "Accident Board Recommendations")
    DescriptionCommonPart AccidentBoardRecommendations;
    @Field(value = "Relevant Regulations / Policy / Background")
    DescriptionCommonPart RelevantRegulationsPolicyBackground;
    @Field(value = "Prevailing Cultural / Organizational Factors")
    DescriptionCommonPart PrevailingCulturalOrganizationalFactors;
    @Field(value = "Key Safety Issue(s)")
    DescriptionCommonPart KeySafetyIssues;
    @Field(value = "Safety Assumptions")
    DescriptionCommonPart SafetyAssumptions;
    @Field(value = "Precursors")
    DescriptionCommonPart Precursors;
    @Field(value = "Resulting Safety Initiatives")
    DescriptionCommonPart ResultingSafetyInitiatives;
    @Field(value = "Airworthiness Directives (ADs) Issued")
    DescriptionCommonPart AirworthinessDirectivesIssued;
    @Field(value = "Common Themes")
    DescriptionCommonPart CommonThemes;
    @Field(value = "Related Accidents / Incidents")
    DescriptionCommonPart RelatedAccidentsIncidents;
    @Field(value = "Lessons Learned")
    DescriptionCommonPart LessonsLearned;
}
