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
import java.util.ArrayList;
import java.util.Objects;

public class RuzSpbStu {
    private static final String LINK = "https://ruz.spbstu.ru/api/v1/ruz/";

    public RuzSpbStu() {

    }

    public static JSONObject request(String requestLink) {
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

    public ArrayList<Faculty> getFaculties() {
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

    public Faculty getFacultyById(int id) {
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

    public ArrayList<Group> getGroupsbyFacultyId(int id) {
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

    public ArrayList<Group> searchGroupsByName(String name) {
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

    public ArrayList<Group> getGroupArrayList(JSONObject jsonObject) {
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

    public ArrayList<Teacher> getTeachers() {
        return getTeacherArrayList(request(LINK + "teachers"));
    }

    public Teacher getTeacherById(int id) {
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

    public ArrayList<Teacher> searchTeachersByName(String name) {
        return Objects.requireNonNull(request(LINK +
                "search/teachers?q=" + name)).get("teachers") == null ? null
                : getTeacherArrayList(request(LINK + "search/teachers?q=" + name));
    }

    public ArrayList<Teacher> getTeacherArrayList(JSONObject jsonObject) {
        try {
            ArrayList<Teacher> answer = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("teachers");
            JSONParser jsonParser = new JSONParser();
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

    public ArrayList<Building> getBuildings() {
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

    public Building getBuildingById(int id) {
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

    public ArrayList<Auditory> getAuditoriesbyBuildingId(int id) {
        try {
            ArrayList<Auditory> answer = new ArrayList<>();
            if (Objects.requireNonNull(request(LINK + "buildings/" + id)).get("error") != null) {
                throw new RuzApiException(Objects.requireNonNull(request(LINK + "buildings/" + id))
                        .get("text").toString());
            }
            JSONObject jsonObject = request(LINK + "buildings/" + id + "/rooms");
            JSONArray jsonArray = (JSONArray) Objects.requireNonNull(jsonObject).get("rooms");
            JSONParser jsonParser = new JSONParser();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ok = (JSONObject) jsonParser.parse(jsonArray.get(i).toString());
                answer.add(Auditory.parseJSON(ok));
            }
            return answer;
        } catch (ParseException | RuzApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}
