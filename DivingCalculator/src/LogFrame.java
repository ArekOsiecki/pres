import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LogFrame  extends JFrame implements ActionListener {



    JFrame logFrame;

    JMenuBar guiMenuBar = new JMenuBar();
    JMenu diveMenu = new JMenu("Dive Menu");
    JMenuItem createProfile = new JMenuItem("Create Profile");
    JMenuItem planDive = new JMenuItem("Plan dive");
    JMenuItem planAnotherDive = new JMenuItem("Plan another dive");//declaration of menu, menu bar and items



    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new LogFrame();



    }

    public LogFrame() throws IOException, ClassNotFoundException {


        logFrame = new JFrame("Show Log");
        logFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Setting exit method

        diveMenu.add(createProfile);
        createProfile.addActionListener(this);//adding items to menus and registering listeners

        diveMenu.add(planDive);
        planDive.addActionListener(this);


        diveMenu.add(planAnotherDive);
        planAnotherDive.addActionListener(this);

        guiMenuBar.add(diveMenu);
        logFrame.setJMenuBar(guiMenuBar);

        logFrame.setSize(400,640);
        logFrame.setLocationRelativeTo(null);
        logFrame.setVisible(true);
        logFrame.setFocusable(true);//adding menu bar to frame, setting visibility and size

        BorderLayout diveFrameLayout = new BorderLayout(0, 1);
        logFrame.setLayout(diveFrameLayout);
        logFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenu diveGridMenu = new JMenu("Dive Menu");
        diveGridMenu.setVisible(true);

        JTextArea TextArea = new JTextArea(200,150);
        Font font = new Font("Monospaced",Font.PLAIN,12);
        TextArea.setFont(font);
        TextArea.setPreferredSize(new Dimension(200, 150));
        logFrame.add(TextArea,BorderLayout.CENTER);
        JButton confirmButton = new JButton("OK");

        logFrame.add(confirmButton,BorderLayout.PAGE_END);



        File inFile = new File("objects.data");

        FileInputStream inFileStream = new FileInputStream(inFile);
        ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);

        Dive dive = (Dive) inObjectStream.readObject();

        Diver diver = Dive.getDiver();








        String text;
        text = String.format("\n%10s%10s%10s%10s%10s%10s", "  Name  ", "  Place  ", "  Date  ","  Depth  ","  Length  ","  Device  ");

        TextArea.append(text);


        String name = Diver.getName();
        String place = dive.getPlace();
        String date = dive.getDate();
        String depth = Integer.toString(dive.getDepth());
        String length = Integer.toString(dive.getLength());
        String device;


        if(Rebreather.isClosedCircuit()){
            device = "Rebreather";
        }else{
            device = "Regulator";
        }

        text = String.format("\n%10s%10s%10s%10s%10s%10s"," "+name+" ", " "+place+" ",
                " "+date+" "," "+depth+" "," "+length+" "," "+device+" ");
        TextArea.append(text);






        confirmButton.addActionListener(e -> {//adding action listeners to buttons using lambda statements


            logFrame.setVisible(false);//disposing of current frame

        });

    }





    public void actionPerformed(ActionEvent actionEvent) {


        if (actionEvent.getSource() == createProfile) {

            try {
                new ProfileFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logFrame.dispose();


        }

        if (actionEvent.getSource() == planDive) {

            try {
                new DiveFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logFrame.dispose();
        }


        if (actionEvent.getSource() == planAnotherDive) {

            new RdpFrame();
            logFrame.dispose();

        }


    }
}
