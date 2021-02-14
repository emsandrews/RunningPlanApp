package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;


class WorkoutModelTest {
    Workout workoutOne;
    Workout workoutTwo;
    Workout workoutThree;
    WorkoutCalendar workouts;
    ArrayList<Workout> testCalendar;

    @BeforeEach
    public void runBefore(){
        workoutOne = new Workout(2021, 2, 20, WorkoutType.SPEED, 12, "");
        workoutTwo = new Workout(2021, 2, 21, WorkoutType.LONG, 20, "");
        workoutThree = new Workout(2021, 2, 22, WorkoutType.LONG, 20, "great run");
        workouts = new WorkoutCalendar();
        testCalendar = new ArrayList<>();
    }

    //WorkoutCalendar Class Tests

    @Test
    public void testWorkoutAdded() {
        testCalendar.add(workoutOne);
        workouts.addWorkout(workoutOne);

        assertEquals(testCalendar.get(0), workouts.getWorkout(0));

    }

    @Test
    public void testGetRunningPlan() {
        workouts.addWorkout(workoutOne);
        workouts.addWorkout(workoutTwo);
        String runningPlan = workouts.getRunningPlan();
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
                + "Status: " + "Incomplete";

        assertEquals(testString, runningPlan);
    }

    @Test
    public void testGetWorkout() {
        workouts.addWorkout(workoutOne);
        workouts.addWorkout(workoutTwo);
        workouts.addWorkout(workoutTwo);
        assertEquals(workoutOne, workouts.getWorkout(0));
    }

    @Test
    public void testWorkoutCalendarIsEmptyTrue() {
        assertTrue(workouts.workoutCalendarIsEmpty());
    }

    @Test
    public void testWorkoutCalendarIsEmptyFalse() {
        workouts.addWorkout(workoutOne);
        workouts.addWorkout(workoutTwo);
        workouts.addWorkout(workoutTwo);

        assertFalse(workouts.workoutCalendarIsEmpty());
    }

    //Workout Class Tests

    @Test
    public void testSetWorkoutComment() {
        this.workoutOne.setWorkoutComment("great run");
        assertEquals("great run", workoutOne.getComment());
    }


    @Test
    public void testSetWorkoutStatusComplete() {
        workoutOne.setWorkoutStatusComplete();
        assertTrue(workoutOne.getStatus());
    }


    @Test
    public void testWorkoutDateSet() {
        LocalDate date = LocalDate.of(2020, 1, 12);
        Workout workoutOnDay = new Workout(2020, 1, 12, WorkoutType.SPEED, 12, null);
        assertEquals(date, workoutOnDay.getDate());
    }


    @Test
    public void testWorkoutTypeSet() {
        WorkoutType type = WorkoutType.LONG;
        Workout workoutWithType = new Workout(2020, 1, 12, type, 12, null);
        assertEquals(type, workoutWithType.getWorkoutType());
    }


    @Test
    public void testWorkoutDistanceSet() {
        int distance = 10;
        Workout workoutWithDistance = new Workout(2020, 1, 12, WorkoutType.SPEED, distance, null);
        assertEquals(distance, workoutWithDistance.getDistance());
    }

    @Test
    public void testGetDate() {
        LocalDate date = workoutOne.getDate();
        LocalDate testDate = LocalDate.of(2021, 2, 20);
        assertEquals(testDate, date);
    }

    @Test
    public void testGetDistance() {
        double distance = 12;
        assertEquals(distance, workoutOne.getDistance());
    }

    @Test
    public void testGetComment() {
        String comment = "great run";
        assertEquals(comment, workoutThree.getComment());
    }

    @Test
    public void testGetWorkoutType() {
        WorkoutType type = WorkoutType.SPEED;
        assertEquals(type, workoutOne.getWorkoutType());
    }

    @Test
    public void testGetWorkoutStatus() {
        assertFalse(workoutOne.getStatus());
    }

    @Test
    public void testWorkoutStatusToStringTrue() {
        workoutOne.setWorkoutStatusComplete();
        assertEquals("Complete", workoutOne.workoutStatusToString());
    }

    @Test
    public void testWorkoutStatusToStringFalse() {

        assertEquals("Incomplete", workoutOne.workoutStatusToString());
    }


    @Test
    public void testWorkoutToString() {
        String workoutString = workoutOne.workoutToString();
        String testString = "Date: " + "20/02/2021" + "\n "
                + "Type: " + "SPEED" + "\n "
                + "Distance: " + "12.0" + "\n "
                + "Comment: " + "" + "\n "
                + "Status: " + "Incomplete";

        assertEquals(testString, workoutString);

    }

    @Test
    public void testRaceToString() {
        String raceString = workoutOne.raceToString();
        String testString = "Date: " + "20/02/2021" + "\n "
                + "Type: " + "SPEED" + "\n "
                + "Distance: " + "12.0" + "\n "
                + "Race Name: " + "" + "\n "
                + "Status: " + "Incomplete";

        assertEquals(testString, raceString);

    }

    //WorkoutTypeEnum Tests

    @Test
    public void testGetWorkoutTypeFromValue() {
        WorkoutType type = WorkoutType.valueOf(3);
        Workout workoutWithType = new Workout(2020, 1, 12, type, 12, null);
        assertEquals(type, workoutWithType.getWorkoutType());

    }

    @Test
    public void testGetValue() {
        WorkoutType workoutType = WorkoutType.SPEED;
        assertEquals(1, workoutType.getValue());
    }



}