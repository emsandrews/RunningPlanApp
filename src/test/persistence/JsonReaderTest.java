package persistence;

import model.TrainingWorkout;
import model.Workout;
import model.WorkoutType;
import model.WorkoutCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    private TrainingWorkout trainingWorkoutOne;
    private TrainingWorkout trainingWorkoutTwo;

    @BeforeEach
    public void runBefore() {
        trainingWorkoutOne = new TrainingWorkout(2021, 2, 20, WorkoutType.SPEED, 12, "");
        trainingWorkoutTwo = new TrainingWorkout(2021, 2, 21, WorkoutType.LONG, 20, "great run");
        trainingWorkoutTwo.setWorkoutStatusComplete();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutCalendar workoutCalendar = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkoutCalendar() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkoutCalendar.json");
        try {
            WorkoutCalendar workoutCalendar = reader.read();
            assertEquals("", workoutCalendar.getName());
            assertEquals(0, workoutCalendar.numWorkouts());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkoutCalendar() {
        JsonReader reader = new JsonReader("./data/testReaderTrainingWorkoutCalendar.json");
        try {
            WorkoutCalendar workoutCalendar = reader.read();
            assertEquals("Emily's Running Plan", workoutCalendar.getName());
            ArrayList<Workout> workouts = workoutCalendar.getWorkouts();
            assertEquals(2, workoutCalendar.numWorkouts());
            checkTrainingWorkout(20, 2, 2021, WorkoutType.SPEED, 12, "", false, trainingWorkoutOne);
            checkTrainingWorkout(21, 2, 2021, WorkoutType.LONG, 20, "great run", true, trainingWorkoutTwo);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
