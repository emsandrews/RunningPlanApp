package ui;

import model.Workout;
import model.WorkoutType;
import model.WorkoutCalendar;
import java.util.Scanner;


public class RunningPlanApp {
    private Scanner input;
    private WorkoutCalendar workoutCalendar;

    //EFFECTS: runs the Running Plan App
    public RunningPlanApp() {
        runApp();
    }

    //EFFECTS: initializes scanner
    private void init() {
        input = new Scanner(System.in);
        workoutCalendar = new WorkoutCalendar();
    }


    //MODIFIES: this
    //EFFECTS: processes user inputs
    private void runApp() {

        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Logging Off!");
    }


    //MODIFIES: this
    //EFFECTS: processes user commands
    private void processCommand(String command) {


        if (command.equals("new")) {
            createNewWorkout();
        } else if (command.equals("race")) {
            createNewRace();
        } else if (command.equals("workout")) {
            checkWorkout();
        } else if (command.equals("complete")) {
            completeWorkout();
        } else if (command.equals("comment")) {
            commentWorkout();
        } else if (command.equals("view")) {
            viewPlan();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n" + "~~~~Main Menu~~~~");
        System.out.println("Select from:");
        System.out.println("\t new -> Add a new workout");
        System.out.println("\t race -> Add a new race");
        System.out.println("\t workout -> View a workout");
        System.out.println("\t complete -> Complete a workout");
        System.out.println("\t comment -> Comment on a workout");
        System.out.println("\t view -> View your running plan");
        System.out.println("\t quit -> Log out");
    }

    //MODIFIES: this
    //EFFECTS: allows user to add a new workout to their plan for a specific day
    private void createNewWorkout() {
        System.out.println("Enter the year you would like to complete this workout:");
        int year = input.nextInt();

        System.out.println("Enter the month you would like to complete this workout (1-12):");
        int month = input.nextInt();

        System.out.println("Enter the day you would like to complete this workout (1-31):");
        int day = input.nextInt();

        System.out.println("   //Enter the type of workout:\n" + " 1 -> SPEED,\n" + " 2 -> MEDIUM,\n"
                + " 3 -> LONG,\n" + " 4 -> HILLS,\n" + " 5 -> CROSSTRAIN,\n" + " 6 -> REST,\n"
                + " 7 -> STRETCH,\n" + " 8 -> RACE");
        int value = input.nextInt();
        WorkoutType type = WorkoutType.valueOf(value);

        System.out.println("Enter the distance you will run (in Kilometers):");
        double distance = input.nextDouble();

        Workout createdWorkout = new Workout(year, month, day, type, distance, "");
        workoutCalendar.addWorkout(createdWorkout);
        String workoutString = createdWorkout.workoutToString();

        System.out.println("\n " + "Your workout has been added: " + "\n " + workoutString);
    }

    //MODIFIES: this
    //EFFECTS: allows user to add a new race to their plan for a specific day
    private void createNewRace() {
        System.out.println("Enter the year this race will take place:");
        int year = input.nextInt();

        System.out.println("Enter the month this race will take place(1-12):");
        int month = input.nextInt();

        System.out.println("Enter the day this race will take place (1-31):");
        int day = input.nextInt();

        System.out.println("Enter the distance you will run (in Kilometers):");
        double distance = input.nextDouble();

        System.out.println("Enter the name of the race:");
        String comment = input.next();

        int value = 8;
        WorkoutType type = WorkoutType.valueOf(value);

        Workout createdRace = new Workout(year, month, day, type, distance, comment);
        workoutCalendar.addWorkout(createdRace);
        String raceString = createdRace.raceToString();

        System.out.println("\n " + "Your race has been added: " + "\n " + raceString);
    }



    //EFFECTS: allows user to check their workout on a specific day
    private void checkWorkout() {
        System.out.println("What year will this workout take place?");
        int year = input.nextInt();

        System.out.println("What month will this workout take place (1-12)?");
        int month = input.nextInt();

        System.out.println("What day will this workout take place (1-31)?");
        int day = input.nextInt();

        Workout workoutOnDay = workoutCalendar.findWorkoutOnDay(year, month, day);
        String workoutString = workoutOnDay.workoutToString();

        System.out.println("\n " + "Here is your workout:" + "\n " + workoutString);
    }


    //EFFECTS: allows user to mark workout as complete
    private void completeWorkout() {
        System.out.println("In what year did you complete this workout?");
        int year = input.nextInt();

        System.out.println("In what month did you complete this workout (1-12)?");
        int month = input.nextInt();

        System.out.println("On what day did you complete this workout (1-31)?");
        int day = input.nextInt();

        Workout workoutOnDay = workoutCalendar.findWorkoutOnDay(year, month, day);
        workoutOnDay.setWorkoutStatusComplete();
        String workoutString = workoutOnDay.workoutToString();

        System.out.println("\n" + "Your workout status has been set to complete!" + "\n " + workoutString);
    }

    //EFFECTS: allows user to add a comment to their workout
    private void commentWorkout() {
        System.out.println("In what year did you complete this workout?");
        int year = input.nextInt();

        System.out.println("In what month did you complete this workout (1-12)?");
        int month = input.nextInt();

        System.out.println("On what day did you complete this workout (1-31)?");
        int day = input.nextInt();

        System.out.println("Enter your comment: ");
        String comment = input.next();

        Workout workoutOnDay = workoutCalendar.findWorkoutOnDay(year, month, day);
        workoutOnDay.setWorkoutComment(comment);
        String workoutString = workoutOnDay.workoutToString();

        System.out.println("\n " + "Your comment has been added" + "\n " + workoutString);
    }


    //EFFECTS: allows user to view their overall plan
    private void viewPlan() {
        String workoutCalendarString = workoutCalendar.getRunningPlan();
        System.out.println("\n " + "Here is your running plan: " + "\n " + workoutCalendarString);
    }


}

