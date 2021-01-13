package ruz;

import org.json.simple.JSONObject;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class Lesson {
    private final String subject;
    private final String subjectShort;
    private final int type;
    private final String additionalInfo;
    private final LocalTime timeStart;
    private final LocalTime timeEnd;
    private final TypeObj typeObj;
    private final int parity;
    private final ArrayList<Group> groups;
    private final ArrayList<Teacher> teachers;
    private final ArrayList<Auditory> auditories;
    private final String webinarUrl;
    private final String lmsUrl;

    public Lesson(String subject, String subjectShort, int type,
                  String additionalInfo, LocalTime timeStart,
                  LocalTime timeEnd, TypeObj typeObj,
                  int parity, ArrayList<Group> groups, ArrayList<Teacher> teachers,
                  ArrayList<Auditory> auditories, String webinarUrl, String lmsUrl) {
        this.subject = subject;
        this.subjectShort = subjectShort;
        this.type = type;
        this.additionalInfo = additionalInfo;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.typeObj = typeObj;
        this.parity = parity;
        this.groups = groups;
        this.teachers = teachers;
        this.auditories = auditories;
        this.webinarUrl = webinarUrl;
        this.lmsUrl = lmsUrl;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "subject='" + subject + '\'' +
                ", subjectShort='" + subjectShort + '\'' +
                ", type=" + type +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", typeObj=" + typeObj +
                ", parity=" + parity +
                ", groups=" + groups +
                ", teachers=" + teachers +
                ", auditories=" + auditories +
                ", webinarUrl='" + webinarUrl + '\'' +
                ", lmsUrl='" + lmsUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return getType() == lesson.getType() && getParity() == lesson.getParity() &&
                getSubject().equals(lesson.getSubject()) &&
                getSubjectShort().equals(lesson.getSubjectShort()) &&
                getAdditionalInfo().equals(lesson.getAdditionalInfo()) &&
                getTimeStart().equals(lesson.getTimeStart()) &&
                getTimeEnd().equals(lesson.getTimeEnd()) &&
                getTypeObj().equals(lesson.getTypeObj()) && Objects.equals(getGroups(),
                lesson.getGroups()) && Objects.equals(getTeachers(),
                lesson.getTeachers()) && Objects.equals(getAuditories(),
                lesson.getAuditories()) &&
                getWebinarUrl().equals(lesson.getWebinarUrl()) && getLmsUrl().equals(lesson.getLmsUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubject(), getSubjectShort(),
                getType(), getAdditionalInfo(), getTimeStart(),
                getTimeEnd(), getTypeObj(), getParity(), getGroups(),
                getTeachers(), getAuditories(), getWebinarUrl(), getLmsUrl());
    }

    public String getSubject() {
        return subject;
    }

    public String getSubjectShort() {
        return subjectShort;
    }

    public int getType() {
        return type;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public TypeObj getTypeObj() {
        return typeObj;
    }

    public int getParity() {
        return parity;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Auditory> getAuditories() {
        return auditories;
    }

    public String getWebinarUrl() {
        return webinarUrl;
    }

    public String getLmsUrl() {
        return lmsUrl;
    }

    public static Lesson parseJSON(JSONObject jsonObject) {
        return new Lesson(jsonObject.get("subject").toString(),
                jsonObject.get("subject_short").toString(),
                Integer.parseInt(jsonObject.get("type").toString()),
                jsonObject.get("additional_info").toString(),
                LocalTime.parse(jsonObject.get("time_start").toString()),
                LocalTime.parse(jsonObject.get("time_end").toString()),
                TypeObj.parseJSON((JSONObject) jsonObject.get("typeObj")),
                Integer.parseInt(jsonObject.get("parity").toString()),
                RuzSpbStu.getGroupArrayList(jsonObject),
                RuzSpbStu.getTeacherArrayList(jsonObject),
                RuzSpbStu.getAuditories(jsonObject),
                jsonObject.get("webinar_url").toString(),
                jsonObject.get("lms_url").toString());
    }
}
