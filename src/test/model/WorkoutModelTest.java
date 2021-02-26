package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;


class WorkoutModelTest {
    TrainingWorkout trainingWorkoutOne;
    TrainingWorkout trainingWorkoutTwo;
    TrainingWorkout trainingWorkoutThree;
    RaceWorkout raceWorkout;
    WorkoutCalendar workouts;
    ArrayList<TrainingWorkout> testCalendar;

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
    public void testWorkoutAdded() {
        testCalendar.add(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutOne);

        assertEquals(testCalendar.get(0), workouts.getWorkout(0));

    }

    @Test
    public void testGetRunningPlan() {
        workouts.addTrainingWorkout(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        workouts.addRaceWorkout(raceWorkout);
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

    @Test
    public void testGetWorkout() {
        workouts.addTrainingWorkout(trainingWorkoutOne);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        workouts.addTrainingWorkout(trainingWorkoutTwo);
        assertEquals(trainingWorkoutOne, workouts.getWorkout(0));
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

    //Workout Class Tests

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


    @Test
    public void testWorkoutDateSet() {
        LocalDate date = LocalDate.of(2020, 1, 12);
        TrainingWorkout trainingWorkoutOnDay = new TrainingWorkout(2020, 1, 12, WorkoutType.SPEED, 12, null);
        assertEquals(date, trainingWorkoutOnDay.getDate());
    }


    @Test
    public void testWorkoutTypeSet() {
        WorkoutType type = WorkoutType.LONG;
        TrainingWorkout trainingWorkoutWithType = new TrainingWorkout(2020, 1, 12, type, 12, null);
        assertEquals(type, trainingWorkoutWithType.getWorkoutType());
    }


    @Test
    public void testWorkoutDistanceSet() {
        int distance = 10;
        TrainingWorkout trainingWorkoutWithDistance = new TrainingWorkout(2020, 1, 12, WorkoutType.SPEED, distance, null);
        assertEquals(distance, trainingWorkoutWithDistance.getDistance());
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

    //WorkoutTypeEnum Tests

    @Test
    public void testGetWorkoutTypeFromValue() {
        WorkoutType type = WorkoutType.valueOf(3);
        TrainingWorkout trainingWorkoutWithType = new TrainingWorkout(2020, 1, 12, type, 12, null);
        assertEquals(type, trainingWorkoutWithType.getWorkoutType());

    }

    @Test
    public void testGetValue() {
        WorkoutType workoutType = WorkoutType.SPEED;
        assertEquals(1, workoutType.getValue());
    }



}