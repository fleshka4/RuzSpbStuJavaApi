package ruz;

import org.json.simple.JSONObject;

import java.util.Objects;

public class TypeObj {
    private final int id;
    private final String name;
    private final String abbr;

    public TypeObj(int id, String name, String abbr) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
    }

    @Override
    public String toString() {
        return "TypeObj{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbr='" + abbr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeObj typeObj = (TypeObj) o;
        return getId() == typeObj.getId() && getName().equals(typeObj.getName()) && getAbbr().equals(typeObj.getAbbr());
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

    public static TypeObj parseJSON(JSONObject jsonObject) {
        return new TypeObj(Integer.parseInt(jsonObject.get("id").toString()),
                jsonObject.get("name").toString(),
                jsonObject.get("abbr").toString());
    }
}
