package com.example.mongo2neo4j.beans.accident;

import lombok.Data;

import java.util.List;
@Data
public class DescriptionCommonPart {
    String url;
    String description;
    List<Pictures> pictures;
    List<Pdfs> pdfs;
}
