package com.example.mongo2neo4j.beans.accident;


import java.util.List;
public class DescriptionCommonPart {
    String url;
    String description;
    List<Pictures> pictures;
    List<Pdfs> pdfs;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(List<Pictures> pictures) {
        this.pictures = pictures;
    }

    public List<Pdfs> getPdfs() {
        return pdfs;
    }

    public void setPdfs(List<Pdfs> pdfs) {
        this.pdfs = pdfs;
    }
}
