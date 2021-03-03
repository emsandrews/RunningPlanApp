package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;


class WorkoutTypeTest {

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