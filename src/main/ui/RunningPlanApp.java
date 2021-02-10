package ui;

import model.Workout;
import model.WorkoutType;
import model.WorkoutCalendar;

import java.util.ArrayList;
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

        System.out.println("Welcome to your running plan, what would you like to do?");

        if (command.equals("new")) {
            createNewWorkout();
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
        System.out.println("Select from:");
        System.out.println("new -> Add a new workout");
        System.out.println("workout -> View a workout");
        System.out.println("complete -> Complete a workout");
        System.out.println("comment -> Comment on a workout");
        System.out.println("view -> View your running plan");
        System.out.println("quit -> Log out");
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
        int distance = input.nextInt();

        System.out.println("Creating workout...");
        Workout createdWorkout = new Workout(year, month, day, type, distance, "");
        workoutCalendar.addWorkout(createdWorkout);
        String workoutString = createdWorkout.workoutToString();

        System.out.println("Your workout has been added: " + "\n " + workoutString);
    }



    //EFFECTS: allows user to check their workout on a specific day
    private void checkWorkout() {
        System.out.println("What year will this workout take place?");
        int year = input.nextInt();

        System.out.println("What month will this workout take place (1-12)?");
        int month = input.nextInt();

        System.out.println("What day will this workout take place (1-31)?");
        int day = input.nextInt();

        System.out.println("Retrieving Workout...");
        Workout workoutOnDay = workoutCalendar.findWorkoutOnDay(year, month, day);
        String workoutString = workoutOnDay.workoutToString();


        System.out.println("Here is your workout:" + "\n " + workoutString);
    }


    //EFFECTS: allows user to mark workout as complete
    private void completeWorkout() {
        System.out.println("In what year did you complete this workout?");
        int year = input.nextInt();

        System.out.println("In what month did you complete this workout (1-12)?");
        int month = input.nextInt();

        System.out.println("On what day did you complete this workout (1-31)?");
        int day = input.nextInt();

        System.out.println("Updating workout status...");
        Workout workoutOnDay = workoutCalendar.findWorkoutOnDay(year, month, day);
        workoutOnDay.setWorkoutStatusComplete();

        System.out.println("Your workout status has been set to complete!");
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

        System.out.println("Adding comment to workout...");
        Workout workoutOnDay = workoutCalendar.findWorkoutOnDay(year, month, day);
        workoutOnDay.setWorkoutComment(comment);

        System.out.println("Your comment has been added");
    }


    //EFFECTS: allows user to view their overall plan
    private void viewPlan() {
        String workoutCalendarString = workoutCalendar.getRunningPlan();
        System.out.println("Here is your running plan: " + "\n " + workoutCalendarString);
    }


}

