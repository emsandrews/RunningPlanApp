package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//Workout with a date, type, distance, comment, and status.
public class TrainingWorkout extends Workout {

    public TrainingWorkout(int year, int month, int day, WorkoutType type, double distance, String comment) {
        super(year, month, day, type, distance, comment);
    }

}
