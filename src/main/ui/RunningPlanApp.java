package ui;

import model.Workout;
import java.util.Scanner;

public class RunningPlanApp {

    //EFFECTS: runs the Running Plan App
    public RunningPlanApp() {
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user inputs
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

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
    private void processCommand() {
        System.out.println("Welcome to your running plan, waht would you like to do?");
        if (command.equals("new")) {
            createNewWorkout();
        } else if (command.equals("workout")) {
            checkWorkout();
        } else if (command.equals("progress")) {
            checkProgress();
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
        System.out.println("workout -> view a workout");
        System.out.println("progress -> view your progress");
        System.out.println("view -> view your running plan");
        System.out.println("quit");
    }

    //MODIFIES: this
    //EFFECTS: allows user to add a new workout to their plan for a specific day
    private void createNewWorkout() {

    }


    //EFFECTS: allows user to check their workout on a specific day
    private void checkWorkout() {

    }


    //EFFECTS: allows user to check their progress in their plan so far
    private void checkProgress() {

    }


    //EFFECTS: allows user to check their progress in their plan so far
    private void viewPlan() {

    }


}

