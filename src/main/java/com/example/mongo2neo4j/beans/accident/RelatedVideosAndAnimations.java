package com.example.mongo2neo4j.beans.accident;

import org.springframework.data.mongodb.core.mapping.Field;
public class RelatedVideosAndAnimations {
    String name;
    String url;
    @Field("download url")
    String downloadUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
