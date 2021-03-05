package model;

//Add a workout to your calendar, get workout, remove workout.

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


//An arraylist of workouts
public class WorkoutCalendar implements Writable {

    private ArrayList<Workout> workouts;
    private String name;

    //EFFECTS: constructs an empty workout calendar (list of workouts)
    public WorkoutCalendar() {
        this.workouts = new ArrayList<>();


    }

    //EFFECTS: constructs an empty workout calendar (list of workouts) with a name
    public WorkoutCalendar(String name) {
        this.workouts = new ArrayList<>();
        this.name = name;


    }

    //MODIFIES: this
    //EFFECTS: sets the name of the WorkoutCalendar
    public void setName(String name) {
        this.name = name + "'s " + "Running Plan";

    }


    //EFFECTS: returns name of WorkoutCalendar
    public String getName() {
        return name;
    }

    //EFFECTS: returns the workouts in the WorkoutCalendar
    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }


    //EFFECTS: returns the workout at a given index.
    public Workout getWorkout(int i) {
        return workouts.get(i);
    }


    //EFFECTS: Returns the number of workouts in the WorkoutCalendar
    public int numWorkouts() {
        return workouts.size();
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


    //EFFECTS: produces true if workout calendar is empty
    public Boolean workoutCalendarIsEmpty() {
        return workouts.isEmpty();
    }



    //EFFECTS: returns all the workouts in the running calendar as a string.
    public String workoutCalendarToString() {

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


    //EFFECTS: returns workoutCalendar as json object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("workouts", workoutsToJson());
        return json;
    }


    //EFFECTS: returns workouts in WorkoutCalendar as JSON array
    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workout workout : workouts) {
            jsonArray.put(workout.toJson());
        }
        return jsonArray;
    }


}



