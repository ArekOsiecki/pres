import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RdpFrame  extends JFrame implements ActionListener {

    JFrame rdpFrame;

    JMenuBar guiMenuBar = new JMenuBar();
    JMenu diveMenu = new JMenu("Dive Menu");
    JMenuItem createProfile = new JMenuItem("Create Profile");
    JMenuItem planDive = new JMenuItem("Plan dive");
    JMenuItem showDiveLog = new JMenuItem("Show dive log");
    //declaration of menu, menu bar and items
    JTextArea logArea, RDPArea; //Text areas


    public static void main(String[] args) {

        new RdpFrame();

    }

    public RdpFrame() {


        rdpFrame = new JFrame("Plan second dive");
        rdpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Setting exit method

        diveMenu.add(createProfile);
        createProfile.addActionListener(this);//adding items to menus and registering listeners

        diveMenu.add(planDive);
        planDive.addActionListener(this);

        diveMenu.add(showDiveLog);
        showDiveLog.addActionListener(this);

        guiMenuBar.add(diveMenu);
      //adding menus to bar



        GridLayout diveFrameLayout = new GridLayout(0, 1);
        rdpFrame.setLayout(diveFrameLayout);
        rdpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenu diveGridMenu = new JMenu("Dive Menu");
        diveGridMenu.setVisible(true);


        JTextField surfaceIntervalText = new JTextField(20);//Creating fields for user input and corresponding labels
        surfaceIntervalText.setBorder(BorderFactory.createTitledBorder("Where you plan to dive?"));

        JTextField nextDepthText = new JTextField(20);
        nextDepthText.setBorder(BorderFactory.createTitledBorder("When you plan to dive?"));

        JTextField nextLengthText = new JTextField(20);
        nextLengthText.setBorder(BorderFactory.createTitledBorder("Enter maximum planned depth in meters"));



        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridLayout buttonsLayout = new GridLayout(0, 2, 25, 0);
        buttonsPanel.setLayout(buttonsLayout);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");
        rdpFrame.setJMenuBar(guiMenuBar);//adding components to layout
        buttonsPanel.add(confirmButton);
        buttonsPanel.add(cancelButton);
        rdpFrame.add(buttonsPanel);




        rdpFrame.setSize(400, 640); //adding menu bar to frame, setting visibility and size
        rdpFrame.setLocationRelativeTo(null);
        rdpFrame.setVisible(true);
        rdpFrame.setFocusable(true);


    }

    public void actionPerformed(ActionEvent actionEvent) {


        if (actionEvent.getSource() == createProfile) {

            try {
                new ProfileFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rdpFrame.dispose();


        }

        if (actionEvent.getSource() == planDive) {

            try {
                new DiveFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rdpFrame.dispose();



        }
        if (actionEvent.getSource() == showDiveLog) {

            try {
                new LogFrame();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            rdpFrame.dispose();


        }
    }
}
/*
*import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class RdpFrame  extends JFrame implements ActionListener {


    JFrame rdpFrame;

    JMenuBar guiMenuBar = new JMenuBar();
    JMenu diveMenu = new JMenu("Dive Menu");
    JMenuItem createProfile = new JMenuItem("Create Profile");
    JMenuItem planDive = new JMenuItem("Plan dive");
    JMenuItem showDiveLog = new JMenuItem("Show dive log");



    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new RdpFrame();

    }

    public RdpFrame() throws ClassNotFoundException, FileNotFoundException, IOException {


        rdpFrame = new JFrame("Plan second dive");
        rdpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Setting exit method

        diveMenu.add(createProfile);
        createProfile.addActionListener(this);//adding items to menus and registering listeners

        diveMenu.add(planDive);
        planDive.addActionListener(this);

        diveMenu.add(showDiveLog);
        showDiveLog.addActionListener(this);

        guiMenuBar.add(diveMenu);
      //adding menus to bar



        GridLayout diveFrameLayout = new GridLayout(0, 1);
        rdpFrame.setLayout(diveFrameLayout);
        rdpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenu diveGridMenu = new JMenu("Dive Menu");
        diveGridMenu.setVisible(true);

        JTextField surfaceIntervalText = new JTextField(20);//Creating fields for user input and corresponding labels
        surfaceIntervalText.setBorder(BorderFactory.createTitledBorder("Where you plan to dive?"));

        JTextField nextDepthText = new JTextField(20);
        nextDepthText.setBorder(BorderFactory.createTitledBorder("When you plan to dive?"));

        JTextField nextLengthText = new JTextField(20);
        nextLengthText.setBorder(BorderFactory.createTitledBorder("Enter maximum planned depth in meters"));





        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridLayout buttonsLayout = new GridLayout(0, 2, 25, 0);
        buttonsPanel.setLayout(buttonsLayout);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");
        rdpFrame.setJMenuBar(guiMenuBar);
        buttonsPanel.add(confirmButton);
        buttonsPanel.add(cancelButton);
        rdpFrame.add(buttonsPanel);
        //adding components to layout


        //adding menu bar to frame, setting visibility and size
        rdpFrame.setSize(400, 640);
        rdpFrame.setLocationRelativeTo(null);
        rdpFrame.setVisible(true);
        rdpFrame.setFocusable(true);

        File outFile = new File("objects.data");
        FileOutputStream  outFileStream = new FileOutputStream(outFile);
        ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);






        confirmButton.addActionListener(e -> {
                    boolean valid = false;




                    try {
                        while(!valid) {


                            File inFile = new File("objects.data");

                            FileInputStream inFileStream = new FileInputStream(inFile);
                            ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);


                            Dive dive = (Dive)inObjectStream.readObject();
                            Diver diver = dive.getDiver();
                            BreathingDevice bd =dive.getBreathingDevice();


                            if(bd.getSize()>8){
                                JOptionPane.showMessageDialog(rdpFrame,"You can dive again after completing post-dive and pre-dive procedures on surface");
                            }else {


                                class GroupPressure
                                {

                                    private HashMap<Integer,Integer[]> pressureGroup;


                                    {

                                        pressureGroup.put(0, new Integer[]{10, 9, 8, 7, 6, 5, 4, 3});
                                        pressureGroup.put(1, new Integer[]{20, 17, 15, 13, 11, 10, 9, 8, 6, 5, 5});
                                        pressureGroup.put(2, new Integer[]{25, 23, 19, 17, 15, 13, 12, 10, 8, 7, 6});
                                        pressureGroup.put(3, new Integer[]{30, 26, 22, 19, 16, 15, 13, 11, 9, 8, 8});
                                        pressureGroup.put(4, new Integer[]{34, 29, 24, 21, 18, 16, 15, 13, 10, 7, 7});
                                        pressureGroup.put(5, new Integer[]{37, 32, 27, 23, 20, 18, 16, 14, 11, 9, 8});
                                        pressureGroup.put(6, new Integer[]{41, 35, 29, 25, 22, 20, 18, 15, 12, 10});
                                        pressureGroup.put(7, new Integer[]{45, 38, 32, 27, 24, 21, 19, 17, 13, 12});
                                        pressureGroup.put(8, new Integer[]{50, 42, 35, 29, 26, 23, 21, 18, 14, 12});
                                        pressureGroup.put(9, new Integer[]{54, 45, 37, 32, 28, 25, 22, 19, 15, 13});
                                        pressureGroup.put(10, new Integer[]{59, 49, 40, 34, 30, 26, 24, 21, 16});
                                        pressureGroup.put(11, new Integer[]{64, 53, 43, 37, 32, 28, 25, 22, 17});
                                        pressureGroup.put(12, new Integer[]{70, 57, 47, 39, 34, 30, 27, 23, 19});
                                        pressureGroup.put(13, new Integer[]{75, 62, 50, 42, 36, 32, 29, 25});
                                        pressureGroup.put(14, new Integer[]{82, 66, 53, 45, 39, 34, 30, 26});
                                        pressureGroup.put(15, new Integer[]{88, 71, 57, 48, 41, 36, 32, 28});
                                        pressureGroup.put(16, new Integer[]{95, 76, 61, 50, 43, 38, 34});
                                        pressureGroup.put(17, new Integer[]{104, 82, 64, 53, 46, 40, 36});
                                        pressureGroup.put(18, new Integer[]{112, 88, 68, 56, 48, 42});
                                        pressureGroup.put(19, new Integer[]{122, 94, 73, 60, 51, 44});
                                        pressureGroup.put(20, new Integer[]{133, 101, 77, 63, 53});
                                        pressureGroup.put(21, new Integer[]{145, 108, 82, 67, 55});
                                        pressureGroup.put(22, new Integer[]{160, 116, 87, 70});
                                        pressureGroup.put(23, new Integer[]{178, 125, 92});
                                        pressureGroup.put(24, new Integer[]{199, 134});

                                        Object[] keys = pressureGroup.keySet().toArray();


                                        int lastDepth = dive.getDepth();
                                        int lastLength = dive.getLength();
                                        int pressureG = diver.getPressureGroup();
                                        int diverExp = diver.getExperienceLevel();

                                        DiveDriver.validateNumber(surfaceIntervalText.getText());
                                        DiveDriver.validateNumber(nextDepthText.getText());
                                        DiveDriver.validateNumber(nextLengthText.getText());

                                        int nextDepth = DiveDriver.validateDepth(nextDepthText.getText());
                                        DiveDriver.validateDepthAndExperience(nextDepth, diverExp);
                                        int nextLength = DiveDriver.validateDiveLength(nextDepth, nextLengthText.getText());
                                        int surfaceInterval = Integer.parseInt(surfaceIntervalText.getText());

                                        pressureGroup.get(keys[lastDepth]);

                                        String myKey = keys[lastDepth].toString();


                                        pressureG+=Integer.parseInt(myKey);

                                        diver.setPressureGroup(pressureG);

                                        System.out.println(diver.toString());


                                    }

                                }



                               /* public Integer indexOf(int length){



                                        }
                                    }
                                }

                                public Object getElementByIndex(HashMap GroupPressure int depth){
                                    return GroupPressure.get( (GroupPressure.keySet().toArray())[ depth ] );
                                }



//JOptionPane.showMessageDialog(null,DiveDriver.nextSafeDive(lastDepth,lastLength));


                            }
                                    }
                                    } catch (RuntimeException | IOException | ClassNotFoundException f) {
                                    JOptionPane.showMessageDialog(rdpFrame, f.fillInStackTrace(), "Incorrect input!", JOptionPane.WARNING_MESSAGE);
                                    }
                                    }
                                    );

                                    cancelButton.addActionListener(e -> {
                                    JOptionPane.showMessageDialog(rdpFrame, "Closing dive planner", "Canceled", JOptionPane.WARNING_MESSAGE);
                                    rdpFrame.setVisible(false);//disposing of current frame

                                    });//adding action listeners to buttons using lambda statements


                                    }















public void actionPerformed(ActionEvent actionEvent) {


        if (actionEvent.getSource() == createProfile) {

        try {
        new ProfileFrame();
        } catch (IOException e) {
        e.printStackTrace();
        }
        rdpFrame.dispose();


        }

        if (actionEvent.getSource() == planDive) {

        try {
        new DiveFrame();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        }
        rdpFrame.dispose();



        }
        if (actionEvent.getSource() == showDiveLog) {

        try {
        new LogFrame();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        }
        rdpFrame.dispose();


        }
        }
        }
        */