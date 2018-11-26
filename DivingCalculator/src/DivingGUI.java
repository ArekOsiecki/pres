/*by Arkadiusz Osiecki 26.1.18

This program is a simple diving calculator/logbook. It allows user to create and update profile and enter data about a planned dive.
* Program also contains features allowing to calculate amount of air needed for the dive,
 * alerts about maximum dive time and surface interval for absolute safe second dive
 * and nitrox composition if using rebreathing open-circuit device.
  * Program also creates the dive log that can be viewed.*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class DivingGUI extends JFrame implements ActionListener{ //This contains global references, buttons and text fields creation code

        JFrame guiFrame;
        JMenuBar guiMenuBar = new JMenuBar();
        JMenu diveMenu = new JMenu("Dive menu");
        JMenuItem createProfile = new JMenuItem("Create Profile");
        JMenuItem planDive = new JMenuItem("Plan dive");
        JMenuItem showDiveLog = new JMenuItem("Show dive log");
        JMenuItem planAnotherDive = new JMenuItem("Plan Second Dive");//declaration of menu, menu bar and items
        JTextArea logArea,RDPArea; //Text areas




    public DivingGUI() { //Code for GUI creation

        guiFrame = new JFrame("Dive Planner");
        guiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Setting exit method

        diveMenu.add(createProfile);//adding items to menus and registering listeners
        createProfile.addActionListener(this);

        diveMenu.add(planDive);
        planDive.addActionListener(this);


        diveMenu.add(planAnotherDive);
        planAnotherDive.addActionListener(this);

        diveMenu.add(showDiveLog);
        showDiveLog.addActionListener(this);


        guiMenuBar.add(diveMenu);
        guiFrame.setJMenuBar(guiMenuBar);

        guiFrame.setSize(400,640);//adding menu bar to frame, setting visibility and size
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);
        guiFrame.setFocusable(true);
    }


    public static void main(String[] args){

        DivingGUI divingGUI = new DivingGUI(); //GUI instantiation
        divingGUI.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {


        if(actionEvent.getSource()==createProfile){

            try {
                new ProfileFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            guiFrame.dispose();

        }

        if(actionEvent.getSource()==planDive){

            try {
                new DiveFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            guiFrame.dispose();

        }
        if (actionEvent.getSource() == showDiveLog) {

            try {
                new LogFrame();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            guiFrame.dispose();

        }
        if (actionEvent.getSource() == planAnotherDive) {

            new RdpFrame();
            guiFrame.dispose();

        }

    }
}
