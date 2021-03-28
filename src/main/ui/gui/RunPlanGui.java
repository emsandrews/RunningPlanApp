package ui.gui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

//Creates and initializes RunPlanGui
public class RunPlanGui extends JPanel {

    private JLabel yearLabel;
    private JTextField yearTf;
    private JLabel monthLabel;
    private JTextField monthTf;
    private JLabel dayLabel;
    private JTextField dayTf;
    private JLabel distanceLabel;
    private JTextField distanceTf;
    private JLabel raceLabel;
    private JTextField raceTf;

    private int year;
    private int month;
    private int day;
    private double distance;
    private WorkoutType type;
    private String race;
    private RaceWorkout raceWorkout;
    private TrainingWorkout trainingWorkout;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private WorkoutCalendar workoutCalendar;
    private static final String JSON_STORE = "./data/workoutCalendar.json";

    private Color darkPurple = new Color(113, 97, 239);
    private Color medPurple = new Color(184, 184, 255);

    private JButton addButton;
    private JPanel addWorkoutPanel;
    private JToggleButton raceToggle;
    private WorkoutTable workoutTable;


    //EFFECTS: constructs a RunPlanGui
    public RunPlanGui() {

        //initialize fields
        init();

        //load plan from Json
        loadPlan();

        this.setBackground(medPurple);

        //Set layout and add components.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(workoutTable);
        add(Box.createVerticalStrut(20));
        add(createAddWorkoutPanel());
        add(Box.createVerticalStrut(10));
        add(addButtonPanel());
        add(Box.createVerticalStrut(20));

    }

    //EFFECTS: initializes workout table and reader/writer fields
    public void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        workoutTable = new WorkoutTable();

    }

    //MODIFIES: this
    //EFFECTS: loads plan from Json File and adds it to workoutCalendar
    private void loadPlan() {
        //load workoutCalendar from JsonTable
        try {
            this.workoutCalendar = this.jsonReader.read();

        } catch (IOException e) {
            System.out.println("Unable to read from file:  " + JSON_STORE);
        }
    }


    //Creating and adding components to workoutPanel.


    //MODIFIES: this
    //EFFECTS: creates a JPanel and adds components.
    public JPanel createAddWorkoutPanel() {
        //Create JPanel
        addWorkoutPanel = new JPanel();
        addWorkoutPanel.setLayout(new BoxLayout(addWorkoutPanel, BoxLayout.LINE_AXIS));
        addWorkoutPanel.setBackground(medPurple);

        //Create and add components
        createFieldsAndLabels();
        addTextFieldsAndLabels();
        addComboBoxAndToggle();

        return addWorkoutPanel;
    }

    //MODIFIES: this
    //EFFECTS: creates JComboBox and JToggleButton and adds them to addWorkoutPanel component
    private void addComboBoxAndToggle() {
        JComboBox types = createComboBox();
        raceToggle = createToggleButton();
        addWorkoutPanel.add(types);
        addWorkoutPanel.add(Box.createHorizontalStrut(10));
        addWorkoutPanel.add(raceToggle);
        addWorkoutPanel.add(Box.createHorizontalStrut(10));
    }

    //MODIFIES: this
    //EFFECTS: adds text fields and labels to addWorkoutPanel component
    private void addTextFieldsAndLabels() {
        addWorkoutPanel.add(Box.createHorizontalStrut(10));
        addWorkoutPanel.add(yearLabel);
        addWorkoutPanel.add(yearTf);
        addWorkoutPanel.add(Box.createHorizontalStrut(10));
        addWorkoutPanel.add(monthLabel);
        addWorkoutPanel.add(monthTf);
        addWorkoutPanel.add(Box.createHorizontalStrut(10));
        addWorkoutPanel.add(dayLabel);
        addWorkoutPanel.add(dayTf);
        addWorkoutPanel.add(Box.createHorizontalStrut(10));
        addWorkoutPanel.add(distanceLabel);
        addWorkoutPanel.add(distanceTf);
        addWorkoutPanel.add(Box.createHorizontalStrut(10));
        addWorkoutPanel.add(raceLabel);
        addWorkoutPanel.add(raceTf);
        addWorkoutPanel.add(Box.createHorizontalStrut(10));
    }


    //Creating add button and panel


    //EFFECTS: Creates JPanel and JButton. Adds button to JPanel.
    public JPanel addButtonPanel() {
        final JPanel addButtonPanel = new JPanel();
        createAddButton();
        addButtonPanel.setBackground(medPurple);
        addButton.setBackground(darkPurple);
        addButton.setForeground(Color.white);
        addButton.setBorderPainted(false);
        addButton.setOpaque(true);

        createAddButton();
        addButtonPanel.add(addButton);

        setVisible(true);

        return addButtonPanel;
    }

    //EFFECTS: returns AddButton field.
    public JButton getAddButton() {
        return this.addButton;
    }

    //MODIFIES: this
    //EFFECTS: creates a JButton with an Action listener
    //action listener creates workout and calls updateTable methods.
    public void createAddButton() {
        JButton addButton = new JButton("Add Workout");
        addButton.setActionCommand("addWorkout");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (year != 0 && month != 0 && day != 0 && type != null && distance != 0.0) {
                    if (race != null) {
                        raceWorkout = new RaceWorkout(year, month, day, type, distance, "", race);
                        updateTableRace();

                    } else {
                        trainingWorkout = new TrainingWorkout(year, month, day, type, distance, "");
                        updateTableTraining();
                    }
                }
            }
        });

        this.addButton = addButton;
    }

    //Creating text fields and labels


    //MODIFIES: this
    //EFFECTS: instantiates JTextFields and JLabels for topMenuBar
    private void createFieldsAndLabels() {
        yearLabel = new JLabel("Year(XXXX)");
        yearTf = getJTextFieldYear();

        monthLabel = new JLabel("Month(1-12)");
        monthTf = getJTextFieldMonth();

        dayLabel = new JLabel("Day(1-31)");
        dayTf = getJTextFieldDay();

        distanceLabel = new JLabel("Distance(KM)");
        distanceTf = getJTextFieldDistance();

        raceLabel = new JLabel("Race Name");
        raceTf = getJTextFieldRace();
        raceTf.setEnabled(false);
    }

    //MODIFIES: this
    //EFFECTS: creates a JTextField with an action listener
    private JTextField getJTextFieldRace() {
        JTextField raceTf = new JTextField(4);
        raceTf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                race = raceTf.getText();
            }
        });

        return raceTf;
    }

//    //MODIFIES: this
//    //EFFECTS: creates a JTextField with an action listener
    private JTextField getJTextFieldDistance() {
        JTextField distanceTf = new JTextField(4);
        distanceTf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                distance = parseDouble(distanceTf.getText());
            }
        });

        return distanceTf;
    }

    //MODIFIES: this
    //EFFECTS: creates a JTextField with an action listener
    private JTextField getJTextFieldDay() {
        JTextField dayTf = new JTextField(4);
        dayTf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                day = parseInt(dayTf.getText());
            }
        });

        return dayTf;
    }

    //MODIFIES: this
    //EFFECTS: creates a JTextField with an action listener
    private JTextField getJTextFieldMonth() {
        JTextField monthTf = new JTextField(4);
        monthTf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                month = parseInt(monthTf.getText());
            }
        });

        return monthTf;
    }

    //MODIFIES: this
    //EFFECTS: creates a JTextField with an action listener
    private JTextField getJTextFieldYear() {
        JTextField yearTf = new JTextField(4);
        yearTf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                year = parseInt(yearTf.getText());
            }
        });
        return yearTf;
    }


    //Creating JComboBox and JToggle Button


    //EFFECTS: helper for JComboBox. Returns the workout type key associated with a given label.
    public int labelToKey(String label) {
        int key = 0;
        switch (label) {
            case "Speed": key += 1;
                break;
            case "Medium": key += 2;
                break;
            case "Long": key += 3;
                break;
            case "Hills": key += 4;
                break;
            case "Cross Train": key += 5;
                break;
            case "Rest": key += 6;
                break;
            case "Stretch": key += 7;
                break;
            case "Race": key += 8;

        } return key;
    }




    //MODIFIES: this
    //EFFECTS: creates a JCombo box with an action listener
    //action listener changes type field based on selection.
    public JComboBox createComboBox() {
        String[] workoutTypeStrings = { "Speed", "Medium", "Long", "Hills", "Cross Train", "Rest", "Stretch" };
        JComboBox cb = new JComboBox(workoutTypeStrings);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cb.getSelectedItem();
                type = WorkoutType.valueOf(labelToKey(selected));
                raceToggle.setSelected(false);
                raceTf.setText("");
                race = null;
            }
        });
        return cb;
    }


    //MODIFIES: this
    //EFFECTS: creates a JToggle button with an item listener
    //item listener enables race text field and sets type field as "race" if selected == true
    public JToggleButton createToggleButton() {
        JToggleButton raceToggle = new JToggleButton("Race");
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    raceTf.setEnabled(true);
                    type = WorkoutType.RACE;
                } else {
                    raceTf.setEnabled(false);
                }
            }
        };
        raceToggle.addItemListener(itemListener);
        return raceToggle;
    }


    //Updating workout calendar and saving plan



    //MODIFIES: this, WorkoutTable
    //EFFECTS: adds new race workout to workoutTable and workoutCalendar
    //removes workoutTable and replaces it with updated workoutTable
    public void updateTableRace() {
        workoutTable.writeTableData();
        workoutCalendar = workoutTable.getWorkoutCalendar();
        workoutCalendar.addRaceWorkout(raceWorkout);
        savePlan();
        loadPlan();
        this.remove(workoutTable);
        workoutTable.setWorkoutCalendar(this.workoutCalendar);
        this.add(workoutTable);

    }

    //MODIFIES: this, Workout Table
    //EFFECTS: adds new race workout to workoutTable and workoutCalendar
    //removes workoutTable and replaces it with updated workoutTable
    public void updateTableTraining() {
        workoutTable.writeTableData();
        workoutCalendar = workoutTable.getWorkoutCalendar();
        workoutCalendar.addTrainingWorkout(trainingWorkout);
        savePlan();
        loadPlan();
        this.remove(workoutTable);
        workoutTable.setWorkoutCalendar(this.workoutCalendar);
        this.add(workoutTable);

    }

    //MODIFIES: this, WorkoutTable
    //EFFECTS: saves running plan to Json File
    public void savePlan() {
        workoutTable.writeTableData();
        workoutCalendar = workoutTable.getWorkoutCalendar();

        try {
            jsonWriter.open();
            jsonWriter.write(workoutCalendar);
            jsonWriter.close();
            System.out.println("Saved running plan to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
