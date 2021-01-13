package ruz;

import org.json.simple.JSONObject;

import java.util.Objects;

public class Teacher {
    private final int id;
    private final int oid;
    private final String fullName;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String grade;
    private final String chair;

    public Teacher(int id, int oid, String fullName, String firstName,
                   String middleName, String lastName, String grade, String chair) {
        this.id = id;
        this.oid = oid;
        this.fullName = fullName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.grade = grade;
        this.chair = chair;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", oid=" + oid +
                ", full_name='" + fullName + '\'' +
                ", first_name='" + firstName + '\'' +
                ", middle_name='" + middleName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", grade='" + grade + '\'' +
                ", chair='" + chair + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return getId() == teacher.getId() && getOid() == teacher.getOid() &&
                getFullName().equals(teacher.getFullName()) &&
                getFirstName().equals(teacher.getFirstName()) &&
                getMiddleName().equals(teacher.getMiddleName()) &&
                getLastName().equals(teacher.getLastName()) &&
                getGrade().equals(teacher.getGrade()) && getChair().equals(teacher.getChair());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOid(), getFullName(), getFirstName(),
                getMiddleName(), getLastName(), getGrade(), getChair());
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public int getOid() {
        return oid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGrade() {
        return grade;
    }

    public String getChair() {
        return chair;
    }

    public static Teacher parseJSON(JSONObject jsonObject) {
        return new Teacher(Integer.parseInt(jsonObject.get("id").toString()),
                Integer.parseInt(jsonObject.get("oid").toString()),
                jsonObject.get("full_name").toString(),
                jsonObject.get("first_name").toString(),
                jsonObject.get("middle_name").toString(), jsonObject.get("last_name").toString(),
                jsonObject.get("grade").toString(), jsonObject.get("chair").toString());
    }
}
