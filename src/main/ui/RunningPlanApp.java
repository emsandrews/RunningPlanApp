package ui;

import model.*;

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
        }  else if (command.equals("view")) {
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
        System.out.println("\t view -> View your running plan");
        System.out.println("\t quit -> Log out");
    }

    //REQUIRES: valid year, valid month (1-12), and valid day (1-31)
    //MODIFIES: this
    //EFFECTS: allows user to add a new workout to their plan for a specific day
    private void createNewWorkout() {
        System.out.println("Enter the year you will complete this workout:");
        int year = input.nextInt();

        System.out.println("Enter the month you will complete this workout (1-12):");
        int month = input.nextInt();

        System.out.println("Enter the day you will complete this workout (1-31):");
        int day = input.nextInt();

        System.out.println("   //Enter the type of workout:\n" + " 1 -> Speed Workout,\n" + " 2 -> Medium Run,\n"
                + " 3 -> Long Run \n" + " 4 -> Hill Workout \n" + " 5 -> Cross Training \n" + " 6 -> Rest Day \n"
                + " 7 -> Stretch or Yoga \n");
        int value = input.nextInt();
        WorkoutType type = WorkoutType.valueOf(value);

        System.out.println("Enter the distance you will run in Kilometers (enter 0 if activity is not a run):");
        double distance = input.nextDouble();

        TrainingWorkout createdTrainingWorkout = new TrainingWorkout(year, month, day, type, distance, "");
        workoutCalendar.addTrainingWorkout(createdTrainingWorkout);
        String workoutString = createdTrainingWorkout.workoutToString();

        System.out.println("\n " + "Your workout has been added: " + "\n " + workoutString);
    }

    //REQUIRES: valid year, valid month (1-12), and valid day (1-31)
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

        input.nextLine();
        System.out.println("Enter the name of the race:");
        String raceName = input.nextLine();

        int value = 8;
        WorkoutType type = WorkoutType.valueOf(value);

        RaceWorkout createdRace = new RaceWorkout(year, month, day, type, distance, "", raceName);
        workoutCalendar.addRaceWorkout(createdRace);
        String raceString = createdRace.workoutToString();

        System.out.println("\n " + "Your race has been added: " + "\n " + raceString);
    }



    //EFFECTS: allows user to check their workout on a specific day
    private void checkWorkout() {
        if (workoutCalendar.workoutCalendarIsEmpty()) {
            System.out.println("No workouts to display, please add a workout");

        } else if (!workoutCalendar.workoutCalendarIsEmpty()) {
            String workoutCalendarString = workoutCalendar.getRunningPlan();
            System.out.println("\n " + "Here is your running plan: " + "\n " + "\n " + workoutCalendarString);

            System.out.println("\n " + "Enter the number of the workout you would like to view");
            int index = input.nextInt();

            Workout workoutOnDay = workoutCalendar.getWorkout(index - 1);
            String workoutString = workoutOnDay.workoutToString();

            System.out.println("\n " + "Here is your workout:" + "\n " + workoutString);
        }

    }


    //EFFECTS: allows user to mark workout as complete and leave a comment
    private void completeWorkout() {

        if (workoutCalendar.workoutCalendarIsEmpty()) {
            System.out.println("No workouts to display, please add a workout");

        } else if (!workoutCalendar.workoutCalendarIsEmpty()) {
            String workoutCalendarString = workoutCalendar.getRunningPlan();
            System.out.println("\n " + "Here is your running plan: " + "\n " + "\n " + workoutCalendarString);

            System.out.println("\n " + "Enter the number of the workout you completed");
            int index = input.nextInt();

            input.nextLine();
            System.out.println("Add a comment on your run: ");
            String comment = input.nextLine();

            Workout trainingWorkoutOnDay = workoutCalendar.getWorkout(index - 1);
            trainingWorkoutOnDay.setWorkoutStatusComplete();
            trainingWorkoutOnDay.setWorkoutComment(comment);
            String workoutString = trainingWorkoutOnDay.workoutToString();

            System.out.println("\n" + "Your workout has been completed!" + "\n " + workoutString);
        }

    }


    //EFFECTS: allows user to view their overall plan
    private void viewPlan() {

        if (workoutCalendar.workoutCalendarIsEmpty()) {
            System.out.println("No workouts to display, please add a workout");

        } else if (!workoutCalendar.workoutCalendarIsEmpty()) {
            String workoutCalendarString = workoutCalendar.getRunningPlan();
            System.out.println("\n " + "Here is your running plan: " + "\n " + "\n " + workoutCalendarString);
        }

    }


}

