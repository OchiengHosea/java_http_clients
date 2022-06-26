package org.example;

import java.lang.reflect.Field;

public class Book {
    private String name;
    private String description;

    public Book(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String toJson() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (Field field : Book.class.getDeclaredFields()){
            field.setAccessible(true);
            try {
                stringBuilder.append(String.format("\"%s\":\"%s\",", field.getName(), String.valueOf(field.get(this))));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
