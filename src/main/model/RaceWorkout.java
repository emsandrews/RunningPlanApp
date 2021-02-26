package model;


import java.time.format.DateTimeFormatter;

//Race with a date, type, distance, comment, race name, and status.
public class RaceWorkout extends Workout {
    private String raceName = "";


    public RaceWorkout(int year, int month, int day, WorkoutType type, double distance, String comment,
                       String raceName) {
        super(year, month, day, type, distance, comment);
        this.raceName = raceName;
    }


    @Override
    //EFFECTS: returns the race workout as a string
    public String workoutToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        String strDate = this.date.format(formatter);
        String type = this.type.toString();
        String distance = Double.toString(this.distance);
        String status = workoutStatusToString();

        return "Date: " + strDate + "\n "
                + "Type: " + type + "\n "
                + "RaceName: " + this.raceName + "\n "
                + "Distance: " + distance + "\n "
                + "comment: " + this.comment + "\n "
                + "Status: " + status;
    }

}
