package ruz;

import org.json.simple.JSONObject;

import java.util.Objects;

public class Building {
    private final int id;
    private final String name;
    private final String abbr;
    private final String address;

    public Building(int id, String name, String abbr, String address) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbr='" + abbr + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return getId() == building.getId() &&
                getName().equals(building.getName()) &&
                getAbbr().equals(building.getAbbr()) &&
                getAddress().equals(building.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAbbr(), getAddress());
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

    public String getAddress() {
        return address;
    }

    public static Building parseJSON(JSONObject jsonObject) {
        return new Building(Integer.parseInt(jsonObject.get("id").toString()),
                jsonObject.get("name").toString(),
                jsonObject.get("abbr").toString(),
                jsonObject.get("address").toString());
    }
}
