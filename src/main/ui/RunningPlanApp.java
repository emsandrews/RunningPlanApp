package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class RunningPlanApp {
    private static final String JSON_STORE = "./data/workoutCalendar.json";
    private Scanner input;
    private WorkoutCalendar workoutCalendar;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String name = "";

    //EFFECTS: runs the Running Plan App, throws FileNotFoundException
    public RunningPlanApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }


    //EFFECTS: initializes scanner creates WorkoutCalendar with a name.
    private void init() {
        input = new Scanner(System.in);
        workoutCalendar = new WorkoutCalendar(this.name);
        initPlan();

    }

    //MODIFIES: this
    //EFFECTS: loads plan from file or creates new plan with a given name.
    private void initPlan() {
        System.out.println("Welcome to the Running Plan App");

        System.out.println("Do you want to load your plan or create a new plan?");
        System.out.println("\t l -> load plan");
        System.out.println("\t n -> create new plan");
        String load = input.nextLine();
        if (load.equals("l")) {
            loadPlan();
        } else if (load.equals("n")) {
            System.out.println("Please enter your name: ");
            String name = input.nextLine();
            workoutCalendar.setName(name);
        } else {
            System.out.println("invalid input");
            initPlan();
        }
    }


    //MODIFIES: this
    //EFFECTS: processes user inputs
    //continues running app until user quits.
    //prompts user to save before quitting.
    private void runApp() {
        boolean keepGoing = true;
        String command = null;
        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("quit")) {
                keepGoing = isKeepGoing();
            } else {
                processCommand(command);
            }
        }
        System.out.println("Logging Off!");
    }


    //EFFECTS: returns boolean for keepGoing to determine
    //whether or not the app should quit.
    private boolean isKeepGoing() {
        boolean keepGoing;
        String save = quittingMenu();
        if (save.equals("save")) {
            savePlan();
            keepGoing = false;
        } else if (save.equals("no")) {
            keepGoing = false;
        } else if (save.equals("m")) {
            keepGoing = true;
        } else {
            System.out.println("invalid input");
            keepGoing = true;
        }
        return keepGoing;
    }

    //EFFECTS: prompts user to save changes before logging off.
    // if user chooses not to save, quits app
    // if user selects in valid input returns to main menu
    private String quittingMenu() {
        System.out.println("Do you want to save your plan before logging off? ");
        System.out.println("Note: unsaved changes will be lost ");
        System.out.println("\t save -> save changes");
        System.out.println("\t no -> changes will not be saved");
        input.nextLine();
        System.out.println("\t m -> return to main menu");
        String save = input.nextLine();
        return save;
    }


    //MODIFIES: this
    //EFFECTS: processes user commands
    private void processCommand(String command) {
        switch (command) {
            case "new":
                createNewWorkout();
                break;
            case "race":
                createNewRace();
                break;
            case "workout":
                checkWorkout();
                break;
            case "complete":
                completeWorkout();
                break;
            case "view":
                viewPlan();
                break;
            default: System.out.println("Selection not valid...");
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
        System.out.println("\t quit -> Save and logout");
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
            String workoutCalendarString = workoutCalendar.workoutCalendarToString();
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
            String workoutCalendarString = workoutCalendar.workoutCalendarToString();
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
            String workoutCalendarString = workoutCalendar.workoutCalendarToString();
            System.out.println("\n " + workoutCalendar.getName() + "\n " + "\n " + workoutCalendarString);
        }

    }

    //EFFECTS: saves the users running plan to file
    private void savePlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(workoutCalendar);
            jsonWriter.close();
            System.out.println("Saved running plan to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the users running plan from file
    private void loadPlan() {
        try {
            workoutCalendar = jsonReader.read();
            System.out.println("Loaded running plan from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file:  " + JSON_STORE);
        }
        viewPlan();
    }


}

