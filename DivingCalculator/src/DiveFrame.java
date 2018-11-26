import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class DiveFrame extends JFrame implements ActionListener {


    JFrame diveFrame;

    JMenuBar guiMenuBar = new JMenuBar();
    JMenu diveMenu = new JMenu("Dive Menu");
    JMenuItem createProfile = new JMenuItem("Create Profile");
    JMenuItem showDiveLog = new JMenuItem("Show dive log");
    JMenuItem planAnotherDive = new JMenuItem("Plan another dive");//declaration of menu, menu bar and items




    String divePlace, diveDate;
    int diveDepth;
    int diveLength;
    int deviceSize ;
    int deviceFilter ;
    String diverName = Diver.getName();
    int diverAge = Diver.getAge();
    int diverExp = Diver.getExperienceLevel();
    int diverSac = Diver.getSac();
    int pressureGroup = Diver.getPressureGroup();




    public static void main(String[] args) throws IOException {

            new DiveFrame();


    }

        public DiveFrame() throws IOException {

            diveFrame = new JFrame("Plan second dive");
            diveFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Setting exit method

            diveMenu.add(createProfile);
            createProfile.addActionListener(this);//adding items to menus and registering listeners

            diveMenu.add(planAnotherDive);
            planAnotherDive.addActionListener(this);

            diveMenu.add(showDiveLog);
            showDiveLog.addActionListener(this);

            guiMenuBar.add(diveMenu);
            //adding menus to bar



            GridLayout diveFrameLayout = new GridLayout(0, 1);
            diveFrame.setLayout(diveFrameLayout);
            diveFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JMenu diveGridMenu = new JMenu("Dive Menu");
            diveGridMenu.setVisible(true);

            JTextField divePlaceText = new JTextField(20);//Creating fields for user input and corresponding labels
            divePlaceText.setBorder(BorderFactory.createTitledBorder("Where you plan to dive?"));

            JTextField diveDateText = new JTextField(20);
            diveDateText.setBorder(BorderFactory.createTitledBorder("Planned date in dd-MM-yyyy format"));

            JTextField diveDepthText = new JTextField(20);
            diveDepthText.setBorder(BorderFactory.createTitledBorder("Enter maximum planned depth in meters"));

            JTextField diveLengthText = new JTextField(20);
            diveLengthText.setBorder(BorderFactory.createTitledBorder("Enter planned length in minutes"));

            String[] choices = {"Regulator and cylinders ", "Rebreather (semi - closed circut)"}; //Creating drop down list, setting it to be uneditable by user and retrieving input
            final JComboBox<String> diveDropDown = new JComboBox<>(choices);
            diveDropDown.setBorder(BorderFactory.createTitledBorder("Choose your device"));
            diveDropDown.setVisible(true);
            diveDropDown.setEditable(false);


            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
            GridLayout buttonsLayout = new GridLayout(0, 2, 25, 0);
            buttonsPanel.setLayout(buttonsLayout);

            JButton confirmButton = new JButton("Confirm");
            JButton cancelButton = new JButton("Cancel");
            diveFrame.setJMenuBar(guiMenuBar);//adding components to layout
            buttonsPanel.add(confirmButton);
            buttonsPanel.add(cancelButton);
            diveFrame.add(divePlaceText);
            diveFrame.add(diveDateText);
            diveFrame.add(diveDepthText);
            diveFrame.add(diveLengthText);
            diveFrame.add(diveDropDown);
            diveFrame.add(buttonsPanel);




            diveFrame.setSize(400, 640);//adding menu bar to frame, setting visibility and size
            diveFrame.setLocationRelativeTo(null);
            diveFrame.setVisible(true);
            diveFrame.setFocusable(true);


            File outFile = new File("objects.data");
            FileOutputStream  outFileStream = new FileOutputStream(outFile);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);


            confirmButton.addActionListener(e -> {
                        boolean valid = false;




                        try {
                            while(!valid) {


                                Diver diver = new Diver(diverName,diverAge,diverSac,diverExp,pressureGroup);


                                    DiveDriver.validateText(divePlaceText.getText());
                                    divePlace = divePlaceText.getText();

                                    DiveDriver.validateDiveDate(diveDateText.getText());
                                    diveDate = diveDateText.getText();

                                    DiveDriver.validateBreathingDevice(diveDropDown.getSelectedIndex(),diverExp);
                                    if(diveDropDown.getSelectedIndex()==1){

                                        DiveDriver.validateNumber(diveDepthText.getText());
                                        diveDepth = Integer.parseInt(diveDepthText.getText());

                                        DiveDriver.validateNumber(diveLengthText.getText());
                                        diveLength = Integer.parseInt(diveLengthText.getText());

                                        deviceFilter = 180;


                                        valid = true;


                                        Rebreather device = new Rebreather(2,8,180,true);


                                        JOptionPane.showMessageDialog(diveFrame, "Device " +device.toString()+" ", "Device", JOptionPane.INFORMATION_MESSAGE);
                                        Dive dive = new Dive(divePlace, diveDate, diveDepth, diveLength, diver,device);
                                        JOptionPane.showMessageDialog(diveFrame, "Dive Logged", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        JOptionPane.showMessageDialog(diveFrame,DiveDriver.rebreatherDive(diveDepth,diveLength,deviceFilter));
                                        outObjectStream.writeObject(dive);


                                    }else{

                                        DiveDriver.validateNumber(diveDepthText.getText());
                                        diveDepth = DiveDriver.validateDepth(diveDepthText.getText());
                                        DiveDriver.validateDepthAndExperience(diveDepth,diverExp);

                                        DiveDriver.validateNumber(diveLengthText.getText());
                                        diveLength = DiveDriver.validateDiveLength(diveDepth,diveLengthText.getText());

                                        valid = true;

                                        BreathingDevice device = new BreathingDevice(DiveDriver.tanksNeeded(deviceSize),DiveDriver.airVolume(diveDepth,diveLength,diverSac));

                                        JOptionPane.showMessageDialog(diveFrame, "Device " +device.toString()+" ", "Device", JOptionPane.INFORMATION_MESSAGE);
                                        Dive dive = new Dive(divePlace, diveDate, diveDepth, diveLength, diver,device);
                                        JOptionPane.showMessageDialog(diveFrame, "Dive Logged", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        outObjectStream.writeObject(dive);



                                    }

                            }
                        } catch (RuntimeException | IOException f) {
                            JOptionPane.showMessageDialog(diveFrame, f.fillInStackTrace(), "Incorrect input!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
            );

            cancelButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(diveFrame, "Closing dive planner", "Canceled", JOptionPane.WARNING_MESSAGE);
                diveFrame.setVisible(false);//disposing of current frame

            });//adding action listeners to buttons using lambda statements


        }


    public void actionPerformed(ActionEvent actionEvent) {


        if(actionEvent.getSource()==createProfile){

            try {
                new ProfileFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            diveFrame.dispose();

        }

        if (actionEvent.getSource() == showDiveLog) {

            try {
                new LogFrame();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            diveFrame.dispose();

        }
        if (actionEvent.getSource() == planAnotherDive) {

            new RdpFrame();
            diveFrame.dispose();

        }


    }

}

