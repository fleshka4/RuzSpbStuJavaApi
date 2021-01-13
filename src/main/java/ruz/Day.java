package ruz;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Day {
    private final int weekDay;
    private final String date;
    private final ArrayList<Lesson> lessons;

    public Day(int weekDay, String date, ArrayList<Lesson> lessons) {
        this.weekDay = weekDay;
        this.date = date;
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Day{" +
                "weekDay=" + weekDay +
                ", date='" + date + '\'' +
                ", lessons=" + lessons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return getWeekDay() == day.getWeekDay() && getDate().equals(day.getDate()) && Objects.equals(getLessons(), day.getLessons());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWeekDay(), getDate(), getLessons());
    }

    public int getWeekDay() {
        return weekDay;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public static Day parseJSON(JSONObject jsonObject) {
        return new Day(Integer.parseInt(jsonObject.get("weekday").toString()),
                jsonObject.get("date").toString(),
                RuzSpbStu.getLessons(jsonObject));
    }
}
