package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


class WorkoutCalendarTest {
    TrainingWorkout trainingWorkoutOne;
    TrainingWorkout trainingWorkoutTwo;
    TrainingWorkout trainingWorkoutThree;
    RaceWorkout raceWorkout;
    WorkoutCalendar workouts;
    ArrayList<Workout> testCalendar;

    @BeforeEach
    public void runBefore(){
        trainingWorkoutOne = new TrainingWorkout(2021, 2, 20, WorkoutType.SPEED, 12, "");
        trainingWorkoutTwo = new TrainingWorkout(2021, 2, 21, WorkoutType.LONG, 20, "");
        trainingWorkoutThree = new TrainingWorkout(2021, 2, 22, WorkoutType.LONG, 20, "great run");
        raceWorkout = new RaceWorkout(2021, 10, 10, WorkoutType.RACE, 42.2, "", "Boston Marathon");
        workouts = new WorkoutCalendar();
        testCalendar = new ArrayList<>();
    }

    //WorkoutCalendar Class Tests

    @Test
    public void testWorkoutCalendarConstructor() {
        WorkoutCalendar workoutCalendar = new WorkoutCalendar();
        workoutCalendar.addTrainingWorkout(trainingWorkoutOne);
        workoutCalendar.addRaceWorkout(raceWorkout);
        assertEquals(2, workoutCalendar.numWorkouts());
        assertEquals(trainingWorkoutOne, workoutCalendar.getWorkout(0));
        assertEquals(raceWorkout, workoutCalendar.getWorkout(1));

    }

    @Test
    public void testWorkoutCalendarStringConstructor() {
        WorkoutCalendar workoutCalendar = new WorkoutCalendar("Emily");
        workoutCalendar.addTrainingWorkout(trainingWorkoutOne);
        workoutCalendar.addRaceWorkout(raceWorkout);
        assertEquals(2, workoutCalendar.numWorkouts());
        assertEquals(trainingWorkoutOne, workoutCalendar.getWorkout(0));
        assertEquals(raceWorkout, workoutCalendar.getWorkout(1));
        assertEquals("Emily", workoutCalendar.getName());
    }

    @Test
    public void testSetName() {
        workouts.setName("Emily");
        assertEquals("Emily's Running Plan", workouts.getName());
        workouts.setName("Bob");
        assertEquals("Bob's Running Plan", workouts.getName());
    }

    @Test
    public void testGetName() {
        workouts.setName("Emily");
        String name = "Emily's Running Plan";
        assertEquals(name, workouts.getName());

    }

    @Test
    public void testGetWorkouts() {
        workouts.addRaceWorkout(raceWorkout);
        workouts.addTrainingWorkout(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        ArrayList<Workout> testWorkouts = workouts.getWorkouts();
        assertEquals(3, testWorkouts.size());
        assertEquals(raceWorkout, testWorkouts.get(0));
        assertEquals(trainingWorkoutOne, testWorkouts.get(1));
        assertEquals(trainingWorkoutTwo, testWorkouts.get(2));

    }

    @Test
    public void testGetWorkout() {
        workouts.addTrainingWorkout(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        assertEquals(trainingWorkoutOne, workouts.getWorkout(0));
    }

    @Test
    public void numWorkouts() {
        workouts.addRaceWorkout(raceWorkout);
        workouts.addTrainingWorkout(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        assertEquals(3, workouts.numWorkouts());

    }

    @Test
    public void testAddTrainingWorkout() {
        testCalendar.add(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutOne);

        assertEquals(testCalendar.get(0), workouts.getWorkout(0));

    }

    @Test
    public void testAddRaceWorkout() {
        testCalendar.add(raceWorkout);
        workouts.addRaceWorkout(raceWorkout);

        assertEquals(testCalendar.get(0), workouts.getWorkout(0));

    }


    @Test
    public void testWorkoutCalendarIsEmptyTrue() {
        assertTrue(workouts.workoutCalendarIsEmpty());
    }


    @Test
    public void testWorkoutCalendarIsEmptyFalse() {
        workouts.addTrainingWorkout(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        workouts.addTrainingWorkout(trainingWorkoutTwo);

        assertFalse(workouts.workoutCalendarIsEmpty());
    }


    @Test
    public void testWorkoutCalendarToString() {
        workouts.addTrainingWorkout(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        workouts.addRaceWorkout(raceWorkout);
        String runningPlan = workouts.workoutCalendarToString();
        String testString = "Workout Number: 1" + "\n "
                + "Date: " + "20/02/2021" + "\n "
                + "Type: " + "SPEED" + "\n "
                + "Distance: " + "12.0" + "\n "
                + "Comment: " + "\n "
                + "Status: " + "Incomplete" + "\n \n "

                + "Workout Number: 2" + "\n "
                + "Date: " + "21/02/2021" + "\n "
                + "Type: " + "LONG" + "\n "
                + "Distance: " + "20.0" + "\n "
                + "Comment: " + "\n "
                + "Status: " + "Incomplete" + "\n \n "

                + "Workout Number: 3" + "\n "
                + "Date: " + "10/10/2021" + "\n "
                + "Type: " + "RACE" + "\n "
                + "RaceName: " + "Boston Marathon" + "\n "
                + "Distance: " + "42.2" + "\n "
                + "comment: " + "" + "\n "
                + "Status: " + "Incomplete";

        assertEquals(testString, runningPlan);
    }


}