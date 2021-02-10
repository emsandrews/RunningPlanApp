package model;

import java.util.HashMap;
import java.util.Map;

//enumeration of possible workout types
public enum WorkoutType {
    SPEED(1),
    MEDIUM(2),
    LONG(3),
    HILLS(4),
    CROSSTRAIN(5),
    REST(5),
    STRETCH(6),
    RACE(7);


    private int value;
    private static Map map = new HashMap<>();

    //MODIFIES: this
    //EFFECTS: builds a hashmap of enumeration values and workoutType
    private WorkoutType(int value) {
        this.value = value;
    }

    static {
        for (WorkoutType workoutType : WorkoutType.values()) {
            map.put(workoutType.value, workoutType);
        }
    }

    //EFFECTS: returns the workoutType associated with a given value
    public static WorkoutType valueOf(int workoutType) {
        return (WorkoutType) map.get(workoutType);
    }

    //EFFECTS: returns the value of a given workoutType
    public int getValue() {
        return value;
    }
}


