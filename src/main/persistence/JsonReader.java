package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workoutCalendar from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads workoutCalendar from file and returns it
    //throws IOException if an error occurs reading data from file.
    public WorkoutCalendar read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutCalendar(jsonObject);
    }


    //EFFECTS: reads source file as a string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS parses workoutCalendar from JSON object and returns it
    private WorkoutCalendar parseWorkoutCalendar(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkoutCalendar workoutCalendar = new WorkoutCalendar(name);
        addWorkouts(workoutCalendar, jsonObject);
        return workoutCalendar;
    }


    //MODIFIES: workoutCalendar
    //EFFECTS: parses workouts from JSON object and adds them to workoutCalendar
    private void addWorkouts(WorkoutCalendar workoutCalendar, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workouts");
        for (Object json : jsonArray) {
            JSONObject nextWorkout = (JSONObject) json;
            WorkoutType type = WorkoutType.valueOf(nextWorkout.getInt("type"));

            if (type.getValue() == 8) {
                addRaceWorkout(workoutCalendar, nextWorkout);
            } else {
                addTrainingWorkout(workoutCalendar, nextWorkout);
            }
        }
    }

    //MODIFIES: workoutCalendar
    //EFFECTS: parses trainingWorkouts from JSON object and adds it to workoutCalendar
    private void addTrainingWorkout(WorkoutCalendar workoutCalendar, JSONObject jsonObject) {
        int year = jsonObject.getInt("year");
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        WorkoutType type = WorkoutType.valueOf(jsonObject.getInt("type"));
        double distance = jsonObject.getDouble("distance");
        String comment = jsonObject.getString("comment");
        Boolean status = jsonObject.getBoolean("status");
        TrainingWorkout trainingWorkout = new TrainingWorkout(year, month, day, type, distance, comment);
        trainingWorkout.setStatus(status);
        workoutCalendar.addTrainingWorkout(trainingWorkout);

    }

    //MODIFIES: workoutCalendar
    //EFFECTS: parses raceWorkouts from JSON object and adds it to workoutCalendar
    private void addRaceWorkout(WorkoutCalendar workoutCalendar, JSONObject jsonObject) {
        int year = jsonObject.getInt("year");
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        WorkoutType type = WorkoutType.valueOf(jsonObject.getInt("type"));
        String raceName = jsonObject.getString("raceName");
        double distance = jsonObject.getDouble("distance");
        String comment = jsonObject.getString("comment");
        Boolean status = jsonObject.getBoolean("status");
        RaceWorkout raceWorkout = new RaceWorkout(year, month, day, type, distance, comment, raceName);
        raceWorkout.setStatus(status);
        workoutCalendar.addRaceWorkout(raceWorkout);

    }



}
