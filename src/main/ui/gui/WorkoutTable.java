package ui.gui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;


//Creates JTable with workouts in running plan.
public class WorkoutTable extends JPanel {
    private WorkoutCalendar workoutCalendar;
    private static final String JSON_STORE = "./data/workoutCalendar.json";
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private int row = 0;
    private Color coolBlue = new Color(168, 218, 220);
    private Color lightPurple = new Color(237, 242, 251);
    private Boolean status;
    private String comment;
    private LocalDate date;
    private int year;
    private int month;
    private int day;
    private String raceName;
    private double distance;
    private WorkoutType type;
    private TrainingWorkout trainingWorkout;
    private RaceWorkout raceWorkout;
    final JTable table;


    //EFFECTS: constructs table with workouts in running plan.
    public WorkoutTable() {
        super(new GridLayout(1, 0));
     //   this.status = status;
        try {
            this.workoutCalendar = this.jsonReader.read();

        } catch (IOException e) {
            System.out.println("Unable to read from file:  " + JSON_STORE);
        }
        this.row = workoutCalendar.numWorkouts();


        table = new JTable(new MyTableModel());
        table.setFillsViewportHeight(true);
        int height = table.getRowHeight();
        table.setRowHeight(height + 10);
        table.setOpaque(true);
        table.setFillsViewportHeight(true);
        table.setBackground(lightPurple);



        //Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane and add the table to it.
        add(scrollPane);

    }

    //EFFECTS: returns status
    public Boolean getStatus() {
        return status;
    }

    //EFFECTS: returns comment
    public String getComment() {
        return comment;
    }

    //MODIFIES: this
    //EFFECTS: sets workoutCalendar field
    public void setWorkoutCalendar(WorkoutCalendar workoutCalendar) {
        this.workoutCalendar = workoutCalendar;
    }

    //EFFECTS: returns workoutCalendar field
    public WorkoutCalendar getWorkoutCalendar() {
        return workoutCalendar;
    }


    //MODIFIES: this
    //EFFECTS: Class constructs abstract table model.
    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Date",
                                        "Distance",
                                        "Type",
                                        "Completed",
                                        "Comment",
                                        "Race Name" };


        //EFFECTS: returns column names
        public String getColumnName(int col) {
            return columnNames[col];
        }


        @Override
        //EFFECTS: returns row count
        public int getRowCount() {
            return workoutCalendar.numWorkouts();
        }

        @Override
        //EFFECTS: returns column count
        public int getColumnCount() {
            return 6;
        }

        @Override
        //MODIFIES: this
        //EFFECTS: returns value of the object at a given index.
        //Adds date frome workoutCalendar to table model.
        public Object getValueAt(int row, int col) {
            Workout workout = workoutCalendar.getWorkout(row);
            Object value = new Object();
            switch (col) {
                case 0:
                    value = workout.getDate();
                    break;
                case 1:
                    value = workout.getDistance();
                    break;
                case 2:
                    value = workout.getWorkoutType();
                    break;
                case 3:
                    value = workout.getStatus();
                    break;
                case 4:
                    value = workout.getComment();
                    break;
                case 5:
                    value = getRaceName(workout);
            } return value;
        }

        //EFFECTS: checks if workout is an instance of race workout
        // if it is returns raceName field
        // otherwise returns an empty string.
        private Object getRaceName(Workout workout) {
            Object value;
            if (workout instanceof RaceWorkout) {
                value = ((RaceWorkout) workout).getRaceName();
            } else {
                value = "";
            }
            return value;
        }

        //EFFECTS: returns column class at given column number
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }


        //EFFECTS: returns true if a cell is editable, false otherwise.
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col == 0 || col == 1 || col == 2 ||  col == 5) {
                return false;
            } else {
                return true;
            }
        }

        //MODIFIES: this, Workout
        //EFFECTS: edits value of field at a given index.
        public void setValueAt(Object value, int row, int col) {
            Workout workout = workoutCalendar.getWorkout(row);
            switch (col) {
                case 3:
                    workout.setStatus((Boolean) value);
                    break;
                case 4:
                    workout.setWorkoutComment((String) value.toString());
            }
            fireTableCellUpdated(row, col);
        }


    }

    //MODIFIES: this
    //EFFECTS: reads data from table and updates workoutCalendar with data.
    public void writeTableData() {
        String name = workoutCalendar.getName();
        WorkoutCalendar workoutCalendarTable = new WorkoutCalendar(name);
        for (int r = 0; r < table.getRowCount(); r++) {
            date = (LocalDate) table.getValueAt(r, 0);
            year = date.getYear();
            month = date.getMonthValue();
            day = date.getDayOfMonth();

            distance = (Double) table.getValueAt(r, 1);

            type = (WorkoutType) table.getValueAt(r, 2);

            status = (Boolean) table.getValueAt(r, 3);

            comment = (String) table.getValueAt(r, 4);

            raceName = (String) table.getValueAt(r, 5);

            if (raceName == "") {
                addTW(workoutCalendarTable, distance, type, status, comment, date);
            } else {
                addRW(workoutCalendarTable, raceName, distance, type, status, comment, date);
            }

        }
        workoutCalendar = workoutCalendarTable;
    }

    //MODIFIES: this
    //EFFECTS: helper for writeTableData method. creates race workout and adds it to workoutCalendar
    private void addRW(WorkoutCalendar wc, String rn, Double d, WorkoutType t, Boolean s, String c, LocalDate dt) {
        year = dt.getYear();
        month = dt.getMonthValue();
        day = dt.getDayOfMonth();
        raceWorkout = new RaceWorkout(year, month, day, t, d, c, rn);
        raceWorkout.setStatus(s);
        wc.addRaceWorkout(raceWorkout);
    }

    //MODIFIES: this
    //EFFECTS: helper for writeTableData method. creates training workout and adds it to workoutCalendar
    private void addTW(WorkoutCalendar wc, Double d, WorkoutType t, Boolean s, String c, LocalDate dt) {
        year = dt.getYear();
        month = dt.getMonthValue();
        day = dt.getDayOfMonth();
        trainingWorkout = new TrainingWorkout(year, month, day, t, d, c);
        trainingWorkout.setStatus(s);
        wc.addTrainingWorkout(trainingWorkout);
    }




}
