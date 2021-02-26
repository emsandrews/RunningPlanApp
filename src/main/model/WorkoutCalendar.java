package model;

//Add a workout to your calendar, get workout, remove workout

import java.util.ArrayList;


//represent a list of dates and workouts
public class WorkoutCalendar {

    private ArrayList<Workout> workouts;
    private TrainingWorkout trainingWorkout;
    private RaceWorkout raceWorkout;

    //EFFECTS: constructs an empty workout calendar (list of workouts)
    public WorkoutCalendar() {
        this.workouts = new ArrayList<>();


    }

    //MODIFIES: this
    //EFFECTS: adds a new workout or race to the workout calendar
    public void addTrainingWorkout(TrainingWorkout trainingWorkout) {
        workouts.add(trainingWorkout);
    }

    //MODIFIES: this
    //EFFECTS: adds a new workout or race to the workout calendar
    public void addRaceWorkout(RaceWorkout raceWorkout) {
        workouts.add(raceWorkout);
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



