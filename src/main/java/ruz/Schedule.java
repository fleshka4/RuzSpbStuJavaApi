package ruz;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Schedule {
    private final Week week;
    private final ArrayList<Day> days;

    public Schedule(Week week, ArrayList<Day> days) {
        this.week = week;
        this.days = days;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "week=" + week +
                ", days=" + days +
                '}';
    }

    public Week getWeek() {
        return week;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return getWeek().equals(schedule.getWeek()) && Objects.equals(getDays(), schedule.getDays());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWeek(), getDays());
    }

    public static Schedule parseJSON(JSONObject jsonObject) {
        return new Schedule(Week.parseJSON((JSONObject) jsonObject.get("week")),
                RuzSpbStu.getDays(jsonObject));
    }
}
