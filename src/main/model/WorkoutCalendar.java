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


    //EFFECTS: returns the workout that matches a given date.
    public Workout findWorkoutOnDay(int year, int month, int day) {
        for (int i = 0; i < workouts.size(); i++) {
            Workout workout = workouts.get(i);
            long workoutTime = workout.getDate().getTime();
            long inputTime = new GregorianCalendar(year, month - 1, day).getTime().getTime();
            if (workoutTime == inputTime) {
                return workout;
            }
        }
        return null;
    }


    //MODIFIES: this
    //EFFECTS: returns all the workouts in the running calendar as a string.
    public String getRunningPlan() {

        ArrayList<String> workoutStringList = new ArrayList<>();

        for (int i = 0; i < workouts.size(); i++) {
            Workout workout = workouts.get(i);
            String workoutString = workout.workoutToString();
            workoutStringList.add(workoutString);
        }
        return String.join("\n \n ", workoutStringList);
    }


}



