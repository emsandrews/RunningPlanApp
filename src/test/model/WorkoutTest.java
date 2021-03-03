package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;


class WorkoutTest {
    TrainingWorkout trainingWorkoutOne;
    TrainingWorkout trainingWorkoutTwo;
    TrainingWorkout trainingWorkoutThree;
    RaceWorkout raceWorkout;

    @BeforeEach
    public void runBefore(){
        trainingWorkoutOne = new TrainingWorkout(2021, 2, 20, WorkoutType.SPEED, 12, "");
        trainingWorkoutTwo = new TrainingWorkout(2021, 2, 21, WorkoutType.LONG, 20, "");
        trainingWorkoutThree = new TrainingWorkout(2021, 2, 22, WorkoutType.LONG, 20, "great run");
        raceWorkout = new RaceWorkout(2021, 10, 10, WorkoutType.RACE, 42.2, "", "Boston Marathon");

    }

    //Workout Class Tests

    //Testing constructor trainingWorkout:

    @Test
    public void testTrainingWorkoutDateSet() {
        LocalDate date = LocalDate.of(2020, 1, 12);
        TrainingWorkout trainingWorkoutOnDay = new TrainingWorkout(2020, 1, 12, WorkoutType.SPEED, 12, null);
        assertEquals(date, trainingWorkoutOnDay.getDate());
    }


    @Test
    public void testTrainingWorkoutTypeSet() {
        WorkoutType type = WorkoutType.LONG;
        TrainingWorkout trainingWorkoutWithType = new TrainingWorkout(2020, 1, 12, type, 12, null);
        assertEquals(type, trainingWorkoutWithType.getWorkoutType());
    }


    @Test
    public void testTrainingWorkoutDistanceSet() {
        int distance = 10;
        TrainingWorkout trainingWorkoutWithDistance = new TrainingWorkout(2020, 1, 12, WorkoutType.SPEED, distance, null);
        assertEquals(distance, trainingWorkoutWithDistance.getDistance());
    }

    //Testing constructor raceWorkout:

    @Test
    public void testRaceWorkoutDateSet() {
        LocalDate date = LocalDate.of(2020, 1, 12);
        RaceWorkout raceWorkoutOnDay = new RaceWorkout(2020, 1, 12, WorkoutType.RACE, 12, null, "best race ever");
        assertEquals(date, raceWorkoutOnDay.getDate());
    }


    @Test
    public void testRaceWorkoutDistanceSet() {
        int distance = 10;
        RaceWorkout raceWorkoutWithDistance = new RaceWorkout(2020, 1, 12, WorkoutType.SPEED, distance, null, "best race ever");
        assertEquals(distance, raceWorkoutWithDistance.getDistance());
    }

    @Test
    public void testSetRaceName() {
        assertEquals("Boston Marathon", raceWorkout.getRaceName());
        raceWorkout.setRaceName("Chicago Marathon");
        assertEquals("Chicago Marathon", raceWorkout.getRaceName());
    }

    @Test
    public void testGetRaceName() {
        assertEquals("Boston Marathon", raceWorkout.getRaceName());
    }


    //Testing getters:

    @Test
    public void getDay() {
        assertEquals(20, trainingWorkoutOne.getDay());
    }

    @Test
    public void getMonth() {
        assertEquals(2, trainingWorkoutOne.getMonth());
    }

    @Test
    public void getYear() {
        assertEquals(2021, trainingWorkoutOne.getYear());
    }

    @Test
    public void testGetDate() {
        LocalDate date = trainingWorkoutOne.getDate();
        LocalDate testDate = LocalDate.of(2021, 2, 20);
        assertEquals(testDate, date);
    }

    @Test
    public void testGetDistance() {
        double distance = 12;
        assertEquals(distance, trainingWorkoutOne.getDistance());
    }

    @Test
    public void testGetComment() {
        String comment = "great run";
        assertEquals(comment, trainingWorkoutThree.getComment());
    }

    @Test
    public void testGetWorkoutType() {
        WorkoutType type = WorkoutType.SPEED;
        assertEquals(type, trainingWorkoutOne.getWorkoutType());
    }

    @Test
    public void testGetWorkoutStatus() {
        assertFalse(trainingWorkoutOne.getStatus());
    }

    //Testing setters:

    @Test
    public void testSetStatus() {
        assertFalse(trainingWorkoutOne.getStatus());
        trainingWorkoutOne.setStatus(true);
        assertTrue(trainingWorkoutOne.getStatus());
    }


    @Test
    public void testSetWorkoutComment() {
        this.trainingWorkoutOne.setWorkoutComment("great run");
        assertEquals("great run", trainingWorkoutOne.getComment());
    }


    @Test
    public void testSetWorkoutStatusComplete() {
        trainingWorkoutOne.setWorkoutStatusComplete();
        assertTrue(trainingWorkoutOne.getStatus());
    }

    //Testing other methods


    @Test
    public void testWorkoutStatusToStringTrue() {
        trainingWorkoutOne.setWorkoutStatusComplete();
        assertEquals("Complete", trainingWorkoutOne.workoutStatusToString());
    }

    @Test
    public void testWorkoutStatusToStringFalse() {

        assertEquals("Incomplete", trainingWorkoutOne.workoutStatusToString());
    }


    @Test
    public void testWorkoutToStringTraining() {
        String workoutString = trainingWorkoutOne.workoutToString();
        String testString = "Date: " + "20/02/2021" + "\n "
                + "Type: " + "SPEED" + "\n "
                + "Distance: " + "12.0" + "\n "
                + "Comment: " + "" + "\n "
                + "Status: " + "Incomplete";

        assertEquals(testString, workoutString);

    }

    @Test
    public void testRaceToStringRace() {
        String raceString = raceWorkout.workoutToString();
        String testString = "Date: " + "10/10/2021" + "\n "
                + "Type: " + "RACE" + "\n "
                + "RaceName: " + "Boston Marathon" + "\n "
                + "Distance: " + "42.2" + "\n "
                + "comment: " + "" + "\n "
                + "Status: " + "Incomplete";

        assertEquals(testString, raceString);

    }


}