package com.fleshka4.spbstu.ruz.api.models;

import org.json.simple.JSONObject;

import java.util.Objects;

public class Auditory {
    private final int id;
    private final String name;

    public Auditory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Auditory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditory auditory = (Auditory) o;
        return getId() == auditory.getId() &&
                getName().equals(auditory.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public static Auditory parseJSON(JSONObject jsonObject) {
        return new Auditory(Integer.parseInt(jsonObject.get("id").toString()),
                jsonObject.get("name").toString());
    }
}
