package ui;

import java.io.FileNotFoundException;

public class Main {

    //EFFECTS: runs app, catches FileNotFoundException.
    public static void main(String[] args) {
        try {
            new RunningPlanApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
