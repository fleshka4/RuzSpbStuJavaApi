package com.fleshka4.spbstu.ruz.api;

import org.json.simple.JSONObject;

import java.util.Objects;

public class Faculty {
    private final int id;
    private final String name;
    private final String abbr;

    public Faculty(int id, String name, String abbr) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbr='" + abbr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return getId() == faculty.getId() &&
                getName().equals(faculty.getName()) &&
                getAbbr().equals(faculty.getAbbr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAbbr());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return abbr;
    }

    public static Faculty parseJSON(JSONObject jsonObject) {
        return new Faculty(Integer.parseInt(jsonObject.get("id").toString()),
                jsonObject.get("name").toString(), jsonObject.get("abbr").toString());
    }
}
