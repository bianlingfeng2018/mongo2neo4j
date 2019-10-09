package com.example.mongo2neo4j.beans.accident;

import org.springframework.data.mongodb.core.mapping.Field;

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

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public DescriptionCommonPart getAccidentOverview() {
        return AccidentOverview;
    }

    public void setAccidentOverview(DescriptionCommonPart accidentOverview) {
        AccidentOverview = accidentOverview;
    }

    public DescriptionCommonPart getAccidentBoardFindings() {
        return AccidentBoardFindings;
    }

    public void setAccidentBoardFindings(DescriptionCommonPart accidentBoardFindings) {
        AccidentBoardFindings = accidentBoardFindings;
    }

    public DescriptionCommonPart getAccidentBoardRecommendations() {
        return AccidentBoardRecommendations;
    }

    public void setAccidentBoardRecommendations(DescriptionCommonPart accidentBoardRecommendations) {
        AccidentBoardRecommendations = accidentBoardRecommendations;
    }

    public DescriptionCommonPart getRelevantRegulationsPolicyBackground() {
        return RelevantRegulationsPolicyBackground;
    }

    public void setRelevantRegulationsPolicyBackground(DescriptionCommonPart relevantRegulationsPolicyBackground) {
        RelevantRegulationsPolicyBackground = relevantRegulationsPolicyBackground;
    }

    public DescriptionCommonPart getPrevailingCulturalOrganizationalFactors() {
        return PrevailingCulturalOrganizationalFactors;
    }

    public void setPrevailingCulturalOrganizationalFactors(DescriptionCommonPart prevailingCulturalOrganizationalFactors) {
        PrevailingCulturalOrganizationalFactors = prevailingCulturalOrganizationalFactors;
    }

    public DescriptionCommonPart getKeySafetyIssues() {
        return KeySafetyIssues;
    }

    public void setKeySafetyIssues(DescriptionCommonPart keySafetyIssues) {
        KeySafetyIssues = keySafetyIssues;
    }

    public DescriptionCommonPart getSafetyAssumptions() {
        return SafetyAssumptions;
    }

    public void setSafetyAssumptions(DescriptionCommonPart safetyAssumptions) {
        SafetyAssumptions = safetyAssumptions;
    }

    public DescriptionCommonPart getPrecursors() {
        return Precursors;
    }

    public void setPrecursors(DescriptionCommonPart precursors) {
        Precursors = precursors;
    }

    public DescriptionCommonPart getResultingSafetyInitiatives() {
        return ResultingSafetyInitiatives;
    }

    public void setResultingSafetyInitiatives(DescriptionCommonPart resultingSafetyInitiatives) {
        ResultingSafetyInitiatives = resultingSafetyInitiatives;
    }

    public DescriptionCommonPart getAirworthinessDirectivesIssued() {
        return AirworthinessDirectivesIssued;
    }

    public void setAirworthinessDirectivesIssued(DescriptionCommonPart airworthinessDirectivesIssued) {
        AirworthinessDirectivesIssued = airworthinessDirectivesIssued;
    }

    public DescriptionCommonPart getCommonThemes() {
        return CommonThemes;
    }

    public void setCommonThemes(DescriptionCommonPart commonThemes) {
        CommonThemes = commonThemes;
    }

    public DescriptionCommonPart getRelatedAccidentsIncidents() {
        return RelatedAccidentsIncidents;
    }

    public void setRelatedAccidentsIncidents(DescriptionCommonPart relatedAccidentsIncidents) {
        RelatedAccidentsIncidents = relatedAccidentsIncidents;
    }

    public DescriptionCommonPart getLessonsLearned() {
        return LessonsLearned;
    }

    public void setLessonsLearned(DescriptionCommonPart lessonsLearned) {
        LessonsLearned = lessonsLearned;
    }
}
