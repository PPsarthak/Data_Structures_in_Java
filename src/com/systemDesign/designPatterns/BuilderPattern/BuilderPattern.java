package com.systemDesign.designPatterns.BuilderPattern;

/**
 * Builder is a creational design pattern that lets you construct complex objects step by step.
 * The pattern allows you to produce different types and representations of an object using the same construction code.
 */
public class BuilderPattern {
    private String firstName;
    private String lastName;
    private int age;
    BuilderPattern(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
    }
    static class Builder{
        private String firstName;
        private String lastName;
        private int age;
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public BuilderPattern build() {
            return new BuilderPattern(this);
        }
    }
}
