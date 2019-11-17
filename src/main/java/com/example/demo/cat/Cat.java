package com.example.demo.cat;

public class Cat {
    private Integer id;
    private String name;
    private String color;
    private Integer age;
    private Float weight;

    public Cat() {
    }

    public Cat(
            Integer id,
            String name,
            String color,
            Integer age,
            Float weight
    ) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

}
