package model;

import java.util.Date;
import java.util.GregorianCalendar;

//Workout with a date, type, distance, comment, and status.
public class Workout {
    private GregorianCalendar date;
    private WorkoutType type;
    private int distance;
    private String comment;
    private Boolean status;



    //EFFECTS: Constructs a workout
    public void workout(GregorianCalendar date, WorkoutType type, int distance, String comment, Boolean status) {
        this.date = new GregorianCalendar();
        this.type = type;
        this.distance = distance;
        this.comment = comment;
        this.status = false;
    }

    //MODIFIES: this
    //EFFECTS: changes workout status to "true" indicating workout is complete for a given date
    public void markWorkoutComplete() {
        this.status = true;


    }

    //MODIFIES: this
    //EFFECTS: adds a comment to a workout on a given date
    public void commentOnWorkout(Date date, String usercomment) {
        this.comment = usercomment;

    }


}
