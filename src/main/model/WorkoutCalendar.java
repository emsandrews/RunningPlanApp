package model;

//Add a workout to your calendar, get workout, remove workout

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


//represent a list of dates and workouts
public class WorkoutCalendar {

    private ArrayList<Workout> workouts;

    //EFFECTS: constructs an empty workout calendar (list of workouts)
    public WorkoutCalendar() {
        this.workouts = new ArrayList<>();

    }

    //MODIFIES: this
    //EFFECTS: adds a new workout or race to the calendar
    public void addWorkout(Date day, String type, int distance, String comment, Boolean status) {
        Workout workout = new Workout();
        workouts.add(workout);
    }

    //EFFECTS: returns workout on a given date
    public void getWorkoutOnDay(Date day) {
        if (day == workout.date) {
            return workout.workouts();
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: removes workout from given date
    public void removeWorkout(Date day) {
        Workout workout =
        this.workout.remove();

    }

    //EFFECTS returns list of upcoming workouts
    public void getWorkoutCalendar() {
    }

    //EFFECTS returns percent of workouts completed
    public void getPercentWorkoutsComplete() {

    }

    //EFFECTS returns total Kms completed so far
    public void getTotalKms() {

    }

    //EFFECTS returns number of days left until the next race
    public void daysToNextRace() {

    }


}



