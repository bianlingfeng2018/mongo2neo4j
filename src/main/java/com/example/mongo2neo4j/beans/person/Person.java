package com.example.mongo2neo4j.beans.person;

public class Person {

  private String id;
  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "com.example.mongo2neo4j.beans.person.Person [id=" + id + ", name=" + name + ", age=" + age + "]";
  }
}