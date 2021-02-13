package model;

//Add a workout to your calendar, get workout, remove workout

import java.util.ArrayList;
import java.util.GregorianCalendar;


//represent a list of dates and workouts
public class WorkoutCalendar {

    private ArrayList<Workout> workouts;

    //EFFECTS: constructs an empty workout calendar (list of workouts)
    public WorkoutCalendar() {
        this.workouts = new ArrayList<>();

    }

    //MODIFIES: this
    //EFFECTS: adds a new workout or race to the workout calendar
    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public Workout getWorkout(int i) {
        return workouts.get(i);
    }

    //EFFECTS: produces true if workout calendar is empty
    public Boolean workoutCalendarIsEmpty() {
        return workouts.isEmpty();
    }



    //EFFECTS: returns all the workouts in the running calendar as a string.
    public String getRunningPlan() {

        ArrayList<String> workoutStringList = new ArrayList<>();

        for (Workout workout : workouts) {
            String workoutString = workout.workoutToString();
            int workoutInt = workouts.indexOf(workout) + 1;
            String workoutNumber = Integer.toString(workoutInt);
            String workoutStringAndNumber = "Workout Number: " + workoutNumber + "\n " + workoutString;
            workoutStringList.add(workoutStringAndNumber);
        }
        return String.join("\n \n ", workoutStringList);
    }


}



