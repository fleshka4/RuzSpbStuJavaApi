package com.fleshka4.spbstu.ruz.api.models;

import org.json.simple.JSONObject;

import java.util.Objects;

public class Group {
    private final int idGroup;
    private final String nameGroup;
    private final int level;
    private final String type;
    private final int kind;
    private final String spec;
    private final int year;

    public Group(int idGroup, String nameGroup, int level,
                 String type, int kind, String spec, int year) {
        this.idGroup = idGroup;
        this.nameGroup = nameGroup;
        this.level = level;
        this.type = type;
        this.kind = kind;
        this.spec = spec;
        this.year = year;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }

    public int getKind() {
        return kind;
    }

    public String getSpec() {
        return spec;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return getIdGroup() == group.getIdGroup() &&
                getLevel() == group.getLevel() &&
                getKind() == group.getKind() &&
                getYear() == group.getYear() &&
                getNameGroup().equals(group.getNameGroup()) &&
                getType().equals(group.getType()) &&
                getSpec().equals(group.getSpec());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdGroup(), getNameGroup(), getLevel(),
                getType(), getKind(), getSpec(), getYear());
    }

    @Override
    public String toString() {
        return "Group{" +
                "idGroup=" + idGroup +
                ", nameGroup='" + nameGroup + '\'' +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", kind=" + kind +
                ", spec='" + spec + '\'' +
                ", year=" + year +
                '}';
    }

    public static Group parseJSON(JSONObject jsonObject) {
        return new Group(Integer.parseInt(jsonObject.get("id").toString()),
                jsonObject.get("name").toString(), Integer.parseInt(jsonObject.get("level").toString()),
                jsonObject.get("type").toString(), Integer.parseInt(jsonObject.get("kind").toString()),
                jsonObject.get("spec").toString(), Integer.parseInt(jsonObject.get("year").toString()));
    }
}
