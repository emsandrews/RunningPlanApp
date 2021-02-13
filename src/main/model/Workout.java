package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

//Workout with a date, type, distance, comment, and status.
public class Workout extends runningWorkout {
    private Date date = null;
    private WorkoutType type = null;
    private double distance = 0;
    private String comment = "";
    private Boolean status = false;


    //REQUIRES: valid month (1-12), and valid day (1-31)
    //EFFECTS: Constructs a workout
    public Workout(int year, int month, int day, WorkoutType type, double distance, String comment) {
        this.date = new GregorianCalendar(year, month - 1, day).getTime();
        this.type = type;
        this.distance = distance;
        this.comment = comment;
    }

    //EFFECTS returns workout date
    public Date getDate() {
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        String strDate = formatter.format(this.date);
        String type = this.type.toString();
        String distance = Double.toString(this.distance);
        String status = workoutStatusToString();

        return "Date: " + strDate + "\n "
                + "Type: " + type + "\n "
                + "Distance: " + distance + "\n "
                + "Comment: " + this.comment + "\n "
                + "Status: " + status;

    }


    //EFFECTS: returns the workout as a string
    public String raceToString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        String strDate = formatter.format(this.date);
        String type = this.type.toString();
        String distance = Double.toString(this.distance);
        String status = workoutStatusToString();

        return "Date: " + strDate + "\n "
                + "Type: " + type + "\n "
                + "Distance: " + distance + "\n "
                + "Race Name: " + this.comment + "\n "
                + "Status: " + status;

    }

}
