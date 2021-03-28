package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Creates a mainGui
public class MainGui extends JFrame {
    private RunPlanGui runPlanGui;
    private WelcomePanel welcomePanel;
    private Color medPurple = new Color(184, 184, 255);
    private Color darkPurple = new Color(113, 97, 239);

    //EFFECTS: constructs a MainGui
    public MainGui() {
        super("Running Plan App");

        //set exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900, 500));

        welcomePanel = new WelcomePanel(this);
        add(welcomePanel, BorderLayout.CENTER);

        setVisible(true);
        pack();
    }

    //EFFECTS: creates a panel and adds save Button to the panel.
    private JPanel createBottomPanel() {
        //Create panel and set layout
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(900, 50));
        bottomPanel.setBackground(medPurple);
        bottomPanel.setOpaque(true);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(Box.createHorizontalGlue());
        //Add save button
        bottomPanel.add(createSaveButton());
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        bottomPanel.setVisible(true);

        return bottomPanel;
    }



    //MODIFIES: this
    //EFFECTS: creates a JButton with action listener
    private JButton createSaveButton() {
        //Create save button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(darkPurple);
        saveButton.setForeground(Color.white);
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(false);
        //Add action listener
        saveButton.setActionCommand("save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runPlanGui.savePlan();
            }
        });
        return saveButton;
    }



    //MODIFIES: this
    //EFFECTS: removes welcome panel initialize and add runPlanGUI to MainGui
    public void createRunningPlan() {
        //create run plan Gui
        runPlanGui = new RunPlanGui();
        runPlanGui.getAddButton().setBackground(darkPurple);

        //remove welcome panel
        remove(welcomePanel);

        //add run plan gui and bottom panel to layout
        add(runPlanGui, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
        pack();
    }


    //EFFECTS: Runs main Gui
    public static void main(String[] args) {
        new MainGui();

    }
}
