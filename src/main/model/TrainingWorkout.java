package model;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//Training workout with a date, type, distance, comment, and status.
public class TrainingWorkout extends Workout {

    //EFFECTS: constructs a training workout with a year, month, day, type, distance, status, and comment.
    public TrainingWorkout(int year, int month, int day, WorkoutType type, double distance, String comment) {
        super(year, month, day, type, distance, comment);
    }


    @Override
    //EFFECTS: returns the workout as a string
    public String workoutToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        String strDate = this.date.format(formatter);
        String type = this.type.toString();
        String distance = Double.toString(this.distance);
        String status = workoutStatusToString();

        return "Date: " + strDate + "\n "
                + "Type: " + type + "\n "
                + "Distance: " + distance + "\n "
                + "Comment: " + this.comment + "\n "
                + "Status: " + status;

    }

    @Override
    //EFFECTS: creates Json Object with Workout
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        int workoutTypeInt = type.getValue();
        json.put("year", year);
        json.put("month", month);
        json.put("day", day);
        json.put("type", workoutTypeInt);
        json.put("distance", distance);
        json.put("comment", comment);
        json.put("status", status);
        return json;
    }

}
