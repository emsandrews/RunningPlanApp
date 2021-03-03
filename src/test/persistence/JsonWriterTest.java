package persistence;


import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    private TrainingWorkout trainingWorkoutOne;
    private TrainingWorkout trainingWorkoutTwo;
    private RaceWorkout raceWorkout;

    @BeforeEach
    public void runBefore() {
        trainingWorkoutOne = new TrainingWorkout(2021, 2, 20, WorkoutType.SPEED, 12, "");
        trainingWorkoutTwo = new TrainingWorkout(2021, 2, 21, WorkoutType.LONG, 20, "great run");
        trainingWorkoutTwo.setWorkoutStatusComplete();
        raceWorkout = new RaceWorkout(2021, 10, 10, WorkoutType.RACE, 42.2, "", "Boston Marathon");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            WorkoutCalendar workoutCalendar = new WorkoutCalendar("Emily's Running Plan");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkoutCalendar() {
        try {
            WorkoutCalendar workoutCalendar = new WorkoutCalendar("Emily's Running Plan");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkoutCalendar.json");
            writer.open();
            writer.write(workoutCalendar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutCalendar.json");
            workoutCalendar = reader.read();
            assertEquals("Emily's Running Plan", workoutCalendar.getName());
            assertEquals(0, workoutCalendar.numWorkouts());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterTrainingWorkoutCalendar() {
        try {
            WorkoutCalendar workoutCalendar = new WorkoutCalendar("Emily's Running Plan");
            workoutCalendar.addTrainingWorkout(trainingWorkoutOne);
            workoutCalendar.addRaceWorkout(raceWorkout);
            JsonWriter writer = new JsonWriter("./data/testWriterTrainingWorkoutCalendar.json");
            writer.open();
            writer.write(workoutCalendar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTrainingWorkoutCalendar.json");
            workoutCalendar = reader.read();
            assertEquals("Emily's Running Plan", workoutCalendar.getName());
            ArrayList<Workout> workouts = workoutCalendar.getWorkouts();
            assertEquals(2, workouts.size());
            checkTrainingWorkout(20, 2, 2021, WorkoutType.SPEED, 12, "", false, trainingWorkoutOne);
            checkRaceWorkout(10, 10, 2021, WorkoutType.RACE, 42.2, "", false, "Boston Marathon", raceWorkout);

         //  raceWorkout = new RaceWorkout(2021, 10, 10, WorkoutType.RACE, 42.2, "", "Boston Marathon");


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
