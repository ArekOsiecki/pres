import java.io.Serializable;

public class BreathingDevice implements Serializable {

    private static int tanks;
    private static int size; //Volume of tanks in liters


    public static int getTanks() {
        return tanks;
    }

    public static int getSize() {
        return size;
    }


    public void setTanks(int tanks) {
        BreathingDevice.tanks = tanks;
    }

    public void setSize(int size) {
        BreathingDevice.size = size;
    }

    public BreathingDevice(int tanks, int size){
        setTanks(tanks);
        setSize(size);


    }

    public BreathingDevice(){
        new BreathingDevice(0,0);
    }

    public String toString(){
        String bdToString = "Number of tanks :" +getTanks()+"\nAir needed : "+getSize()+" litres";
        return bdToString;

    }




}
