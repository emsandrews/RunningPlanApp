package model;


import org.json.JSONObject;

import java.time.format.DateTimeFormatter;

//Race with a date, type, distance, comment, race name, and status.
public class RaceWorkout extends Workout {
    private String raceName = "";


    //EFFECTS: constructs a race workout with a year, month, day, type, distance, status, raceName and comment.
    public RaceWorkout(int year, int month, int day, WorkoutType type, double distance, String comment,
                       String raceName) {
        super(year, month, day, type, distance, comment);
        this.raceName = raceName;
    }

    //MODIFIES: this
    //EFFECTS: sets the name of the race
    public void setRaceName(String name) {
        this.raceName = name;
    }


    //EFFECTS: returns race name
    public String getRaceName() {
        return this.raceName;
    }


    @Override
    //EFFECTS: returns the race workout as a string
    public String workoutToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        String strDate = this.date.format(formatter);
        String type = this.type.toString();
        String distance = Double.toString(this.distance);
        String status = workoutStatusToString();

        return "Date: " + strDate + "\n "
                + "Type: " + type + "\n "
                + "RaceName: " + this.raceName + "\n "
                + "Distance: " + distance + "\n "
                + "comment: " + this.comment + "\n "
                + "Status: " + status;
    }

    @Override
    //EFFECTS: creates JSON object with raceWorkout
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        int workoutTypeInt = type.getValue();
        json.put("year", year);
        json.put("month", month);
        json.put("day", day);
        json.put("type", workoutTypeInt);
        json.put("raceName", raceName);
        json.put("distance", distance);
        json.put("comment", comment);
        json.put("status", status);
        return json;
    }

}
