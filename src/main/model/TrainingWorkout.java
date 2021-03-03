package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//Training workout with a date, type, distance, comment, and status.
public class TrainingWorkout extends Workout {

    //EFFECTS: constructs a training workout with a year, month, day, type, distance, status, and comment.
    public TrainingWorkout(int year, int month, int day, WorkoutType type, double distance, String comment) {
        super(year, month, day, type, distance, comment);
    }

}
