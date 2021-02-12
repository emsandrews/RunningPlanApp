package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;


class WorkoutModelTest {
    Workout workoutOne;
    Workout workoutTwo;
    WorkoutCalendar workouts;
    ArrayList<Workout> testCalendar;

    @BeforeEach
    public void runBefore(){
        workoutOne = new Workout(2021, 2, 20, WorkoutType.SPEED, 12, "");
        workoutTwo = new Workout(2021, 2, 30, WorkoutType.LONG, 20, "");
        workouts = new WorkoutCalendar();
        testCalendar = new ArrayList<>();
    }

    @Test
    public void testWorkoutAdded() {
        testCalendar.add(workoutOne);
        workouts.addWorkout(workoutOne);
        int year = 2021;
        int month = 2;
        int day = 20;
        assertEquals(testCalendar.get(0), workouts.findWorkoutOnDay(year, month, day));

    }


    @Test
    public void testFindWorkoutOnDay() {
        workouts.addWorkout(workoutOne);
        assertEquals(workoutOne, workouts.findWorkoutOnDay(2021, 2, 20));

    }

    @Test
    public void testGetRunningPlan() {
        workouts.addWorkout(workoutOne);
        workouts.addWorkout(workoutTwo);
        String runningPlan = workouts.getRunningPlan();
        String testString = "Date: " + "20/02/2021" + "\n "
        + "Type: " + "SPEED" + "\n "
        + "Distance: " + "12" + "\n "
        + "Comment: " + "\n "
        + "Status: " + "Incomplete" + "\n \n "

        + "Date: " + "02/03/2021" + "\n "
        + "Type: " + "LONG" + "\n "
        + "Distance: " + "20" + "\n "
        + "Comment: " + "\n "
        + "Status: " + "Incomplete";

        assertEquals(testString, runningPlan);

    }


    @Test
    public void testCommentAddedToWorkout() {
        this.workoutOne.setWorkoutComment("great run");
        assertEquals("great run", workoutOne.getComment());
    }


    @Test
    public void testWorkoutStatusUpdated() {
        workoutOne.setWorkoutStatusComplete();
        assertTrue(workoutOne.getStatus());
    }


    @Test
    public void testWorkoutDateSet() {
        Date date = new GregorianCalendar(2020, 2 - 1, 12).getTime();
        Workout workoutOnDay = new Workout(2020, 2, 12, WorkoutType.SPEED, 12, null);
        assertEquals(date, workoutOnDay.getDate());
    }


    @Test
    public void testWorkoutTypeSet() {
        WorkoutType type = WorkoutType.LONG;
        Workout workoutWithType = new Workout(2020, 2, 12, type, 12, null);
        assertEquals(type, workoutWithType.getWorkoutType());
    }


    @Test
    public void testWorkoutDistanceSet() {
        int distance = 10;
        Workout workoutWithDistance = new Workout(2020, 2, 12, WorkoutType.SPEED, distance, null);
        assertEquals(distance, workoutWithDistance.getDistance());

    }


    @Test
    public void testWorkoutToString() {
        String workoutString = workoutOne.workoutToString();
        String testString = "Date: " + "20/02/2021" + "\n "
                + "Type: " + "SPEED" + "\n "
                + "Distance: " + "12" + "\n "
                + "Comment: " + "" + "\n "
                + "Status: " + "Incomplete";

        assertEquals(testString, workoutString);

    }

    @Test
    public void testRaceToString() {
        String raceString = workoutOne.raceToString();
        String testString = "Date: " + "20/02/2021" + "\n "
                + "Type: " + "SPEED" + "\n "
                + "Distance: " + "12" + "\n "
                + "Race Name: " + "" + "\n "
                + "Status: " + "Incomplete";

        assertEquals(testString, raceString);

    }

    @Test
    public void testGetWorkoutTypeFromValue() {
        WorkoutType type = WorkoutType.valueOf(3);
        Workout workoutWithType = new Workout(2020, 2, 12, type, 12, null);
        assertEquals(type, workoutWithType.getWorkoutType());

    }

    @Test
    public void testGetValue() {
        WorkoutType workoutType = WorkoutType.SPEED;
        assertEquals(1, workoutType.getValue());
    }

}