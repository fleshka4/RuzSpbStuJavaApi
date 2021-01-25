package com.fleshka4.spbstu.ruz.api.models;

import org.json.simple.JSONObject;

import java.util.Objects;

public class Room {
    private final int id;
    private final String name;

    public Room(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
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
        Room room = (Room) o;
        return getId() == room.getId() &&
                getName().equals(room.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public static Room parseJSON(JSONObject jsonObject) {
        return new Room(Integer.parseInt(jsonObject.get("id").toString()),
                jsonObject.get("name").toString());
    }
}
