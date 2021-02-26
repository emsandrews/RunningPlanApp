package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Workout {

    protected LocalDate date = null;
    protected WorkoutType type = null;
    protected double distance = 0;
    protected String comment = "";
    protected Boolean status = false;


    //REQUIRES: valid month (1-12), and valid day (1-31)
    //EFFECTS: Constructs a workout
    public Workout(int year, int month, int day, WorkoutType type, double distance, String comment) {
        this.date = LocalDate.of(year, month, day);
        this.type = type;
        this.distance = distance;
        this.comment = comment;
    }

    //EFFECTS returns workout date
    public LocalDate getDate() {
        return date;
    }

    //EFFECTS returns workout distance
    public double getDistance() {
        return distance;
    }

    //EFFECTS returns workout comment
    public String getComment() {
        return comment;
    }

    //EFFECTS returns workout type
    public WorkoutType getWorkoutType() {
        return type;
    }

    //EFFECTS returns workout status
    public Boolean getStatus() {
        return status;
    }

    //MODIFIES: this
    //EFFECTS: adds a comment to a workout on a given date
    public void setWorkoutComment(String userComment) {
        this.comment = userComment;

    }

    //MODIFIES: this
    //EFFECTS: changes workout status to "true" indicating workout is complete for a given date
    public void setWorkoutStatusComplete() {
        this.status = true;
    }


    //EFFECTS: changes the workout status from a boolean to a string
    public String workoutStatusToString() {

        if (this.status) {
            return "Complete";
        }
        return "Incomplete";
    }


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


}

