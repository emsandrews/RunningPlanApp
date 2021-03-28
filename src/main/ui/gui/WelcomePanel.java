package ui.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

//creates a JPanel with an image and load button
public class WelcomePanel extends JPanel {
    private MainGui mainGui;
    private Color darkPurple = new Color(113, 97, 239);
    private Color lightPurple = new Color(237, 242, 251);
    private JButton loadButton;

    //EFFECTS: constructs a WelcomePanel
    public WelcomePanel(MainGui mainGui) {
        this.mainGui = mainGui;

        //Set Layout and background colour
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(lightPurple);
        this.setOpaque(true);

        //Add image and load button
        createImagePanel();
        this.add(createLoadButton());
    }


    //EFFECTS: loads image from file creates JPanel, adds to welcome panel.
    private void createImagePanel() {
        try {
            //Load and format image
            BufferedImage myPicture = ImageIO.read(new File("src/main/images/Run-Plan-App-Logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setPreferredSize(new Dimension(400,300));

            //Create JPanel with grid layout
            JPanel pictureContainer = new JPanel(new GridLayout(1, 1));
            pictureContainer.setPreferredSize(new Dimension(400, 400));
            pictureContainer.add(picLabel);
            pictureContainer.setBackground(lightPurple);

            //Add picture to JPanel
            this.add(pictureContainer);

            //Catch exception if image cannot be loaded
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    //MODIFIES: this, MainGui
    //EFFECTS: creates a JButton with an action listener. Adds the JButton to a JPanel, returns the JPanel.
    //action listener calls create running plan method in MainGui
    public JPanel createLoadButton() {

        //Create and format JButton
        loadButton = new JButton("Load");
        loadButton.setBackground(darkPurple);
        loadButton.setForeground(Color.white);
        loadButton.setOpaque(true);
        loadButton.setFont(new Font("Arial", Font.PLAIN, 22));
        loadButton.setBorderPainted(false);
        //Add action listener to JButton
        loadButton.setActionCommand("load");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGui.createRunningPlan();

            }
        });
        //Create and format JPanel
        JPanel buttonContainer = new JPanel(new GridLayout(1, 1));
        buttonContainer.setPreferredSize(new Dimension(400, 50));

        //Add button to JPanel
        buttonContainer.add(loadButton);

        return buttonContainer;

    }

}


