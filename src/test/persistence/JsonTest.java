package persistence;


import model.WorkoutType;
import model.Workout;
import model.RaceWorkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkTrainingWorkout(int day, int month, int year, WorkoutType type, double distance, String comment, Boolean status, Workout workout) {
        assertEquals(day, workout.getDay());
        assertEquals(month, workout.getMonth());
        assertEquals(year, workout.getYear());
        assertEquals(type.getValue(), workout.getWorkoutType().getValue());
        assertEquals(distance, workout.getDistance());
        assertEquals(comment, workout.getComment());
        assertEquals(status, workout.getStatus());
    }

    protected void checkRaceWorkout(int day, int month, int year, WorkoutType type, double distance, String comment, Boolean status, String raceName, RaceWorkout raceWorkout) {
        assertEquals(day, raceWorkout.getDay());
        assertEquals(month, raceWorkout.getMonth());
        assertEquals(year, raceWorkout.getYear());
        assertEquals(raceName, raceWorkout.getRaceName());
        assertEquals(type.getValue(), raceWorkout.getWorkoutType().getValue());
        assertEquals(distance, raceWorkout.getDistance());
        assertEquals(comment, raceWorkout.getComment());
        assertEquals(status, raceWorkout.getStatus());
    }
}

