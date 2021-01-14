package com.fleshka4.spbstu.ruz.api.models;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Week {
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final boolean odd;

    public Week(LocalDate dateStart, LocalDate dateEnd, boolean odd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.odd = odd;
    }

    @Override
    public String toString() {
        return "Week{" +
                "date_start='" + dateStart + '\'' +
                ", date_end='" + dateEnd + '\'' +
                ", odd=" + odd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Week week = (Week) o;
        return isOdd() == week.isOdd() && getDateStart().equals(week.getDateStart()) &&
                getDateEnd().equals(week.getDateEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateStart(), getDateEnd(), isOdd());
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public boolean isOdd() {
        return odd;
    }

    public static Week parseJSON(JSONObject jsonObject) {
        return new Week(LocalDate.parse((CharSequence) jsonObject.get("date_start"),
                DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                LocalDate.parse((CharSequence) jsonObject.get("date_end"),
                        DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                Boolean.parseBoolean(jsonObject.get("is_odd").toString()));
    }
}
