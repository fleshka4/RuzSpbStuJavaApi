package ruz;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class RuzSpbStu {
    private static final String LINK = "https://ruz.spbstu.ru/api/v1/ruz/";

    public RuzSpbStu() {

    }

    private static JSONObject request(String requestLink) {
        try {
            URL ruz = new URL(requestLink);
            URLConnection yc = ruz.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;
            StringBuilder result = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            JSONParser ruzPars = new JSONParser();
            JSONObject jsonObject = (JSONObject) ruzPars.parse(result.toString());
            in.close();
            return jsonObject;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Faculty> getFaculties() {
        try {
            ArrayList<Faculty> answer = new ArrayList<>();
            JSONObject jsonObject = request(LINK + "faculties");
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("faculties");
            JSONParser jsonParser = new JSONParser();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ok = (JSONObject) jsonParser.parse(jsonArray.get(i).toString());
                answer.add(Faculty.parseJSON(ok));
            }
            return answer;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        }
    }

    public static Faculty getFacultyById(int id) {
        try {
            JSONObject jsonObject = request(LINK + "faculties/" + id);
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Faculty.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString());
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Group> getGroupsbyFacultyId(int id) {
        try {
            if (Objects.requireNonNull(request(LINK + "faculties/" + id)).get("error") != null) {
                throw new RuzApiException(Objects.requireNonNull(request(LINK +
                        "faculties/" + id)).get("text").toString());
            }
            return getGroupArrayList(request(LINK + "faculties/" + id + "/groups"));
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Group> searchGroupsByName(String name) {
        try {
            if (Objects.requireNonNull(request(LINK + "search/groups?q=" + name)).get("groups") == null) {
                throw new RuzApiException("Групп не найдено");
            }
            return getGroupArrayList(request(LINK + "search/groups?q=" + name));
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Group> getGroupArrayList(JSONObject jsonObject) {
        try {
            ArrayList<Group> answer = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("groups");
            JSONParser jsonParser = new JSONParser();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ok = (JSONObject) jsonParser.parse(jsonArray.get(i).toString());
                answer.add(Group.parseJSON(ok));
            }
            return answer;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Teacher> getTeachers() {
        return getTeacherArrayList(request(LINK + "teachers"));
    }

    public static Teacher getTeacherById(int id) {
        try {
            JSONObject jsonObject = request(LINK + "teachers/" + id);
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Teacher.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString());
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Teacher> searchTeachersByName(String name) {
        try {
            if (Objects.requireNonNull(request(LINK + "search/teachers?q=" + name))
                    .get("teachers") == null) {
                throw new RuzApiException("Ни один преподаватель не найден");
            }
            return getTeacherArrayList(request(LINK + "search/teachers?q=" + name));
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Teacher> getTeacherArrayList(JSONObject jsonObject) {
        try {
            ArrayList<Teacher> answer = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("teachers");
            JSONParser jsonParser = new JSONParser();
            if (jsonArray == null) return null;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ok = (JSONObject) jsonParser.parse(jsonArray.get(i).toString());
                answer.add(Teacher.parseJSON(ok));
            }
            return answer;
        } catch (ParseException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Building> getBuildings() {
        try {
            ArrayList<Building> answer = new ArrayList<>();
            JSONObject jsonObject = request(LINK + "buildings");
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("buildings");
            JSONParser jsonParser = new JSONParser();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ok = (JSONObject) jsonParser.parse(jsonArray.get(i).toString());
                answer.add(Building.parseJSON(ok));
            }
            return answer;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        }
    }

    public static Building getBuildingById(int id) {
        try {
            JSONObject jsonObject = request(LINK + "buildings/" + id);
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Building.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString());
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static Building findBuildingByAuditoryId(int id) {
        ArrayList<Building> buildings = getBuildings();
        for (int i = 0; i < Objects.requireNonNull(buildings).size(); i++) {
            ArrayList<Auditory> auditories = getAuditoriesByBuildingId(buildings.get(i).getId());
            for (int j = 0; j < Objects.requireNonNull(auditories).size(); j++) {
                if (id == auditories.get(j).getId())
                    return buildings.get(i);
            }
        }
        return null;
    }

    public static ArrayList<Auditory> getAuditoriesByBuildingId(int id) {
        try {
            if (Objects.requireNonNull(request(LINK + "buildings/" + id)).get("error") != null) {
                throw new RuzApiException(Objects.requireNonNull(request(LINK + "buildings/" + id))
                        .get("text").toString());
            }
            JSONObject jsonObject = request(LINK + "buildings/" + id + "/rooms");
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("rooms");
            return parseAuditories(jsonArray);
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Auditory> getAuditories(JSONObject jsonObject) {
        return parseAuditories((JSONArray) Objects.requireNonNull(jsonObject).get("auditories"));
    }

    private static ArrayList<Auditory> parseAuditories(JSONArray jsonArray) {
        try {
            ArrayList<Auditory> answer = new ArrayList<>();
            JSONParser jsonParser = new JSONParser();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ok = (JSONObject) jsonParser.parse(jsonArray.get(i).toString());
                answer.add(Auditory.parseJSON(ok));
            }
            return answer;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Lesson> getLessons(JSONObject jsonObject) {
        try {
            ArrayList<Lesson> answer = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("lessons");
            JSONParser jsonParser = new JSONParser();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ok = (JSONObject) jsonParser.parse(jsonArray.get(i).toString());
                answer.add(Lesson.parseJSON(ok));
            }
            return answer;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Day> getDays(JSONObject jsonObject) {
        try {
            ArrayList<Day> answer = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("days");
            JSONParser jsonParser = new JSONParser();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ok = (JSONObject) jsonParser.parse(jsonArray.get(i).toString());
                answer.add(Day.parseJSON(ok));
            }
            return answer;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        }
    }

    public static Schedule getScheduleByGroupId(int id) {
        try {
            JSONObject jsonObject = request(LINK + "scheduler/" + id);
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Schedule.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString() + "а");
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static Schedule getScheduleByGroupIdAndDate(int id, LocalDate date) {
        try {
            JSONObject jsonObject = request(LINK + "scheduler/" + id +
                    "?date=" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Schedule.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString() + "а");
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static Schedule getScheduleByTeacherId(int id) {
        try {
            JSONObject jsonObject = request(LINK + "teachers/" + id + "/scheduler");
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Schedule.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString());
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static Schedule getScheduleByTeacherIdAndDate(int id, LocalDate date) {
        try {
            JSONObject jsonObject = request(LINK + "teachers/" + id +
                    "/scheduler?date=" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Schedule.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString());
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static Schedule getScheduleByAuditoryId(int id) {
        try {
            JSONObject jsonObject = request(LINK + "buildings/" +
                    findBuildingByAuditoryId(id) + "/rooms/" + id + "/scheduler");
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Schedule.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString());
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }

    public static Schedule getScheduleByAuditoryIdAndDate(int id, LocalDate date) {
        try {
            JSONObject jsonObject = request(LINK + "buildings/" +
                    findBuildingByAuditoryId(id) + "/rooms/" + id + "/scheduler?date=" +
                    date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if (Objects.requireNonNull(jsonObject).get("error") == null) {
                return Schedule.parseJSON(jsonObject);
            } else {
                throw new RuzApiException(jsonObject.get("text").toString());
            }
        } catch (RuzApiException ruzApiException) {
            ruzApiException.printStackTrace();
            return null;
        }
    }
}
