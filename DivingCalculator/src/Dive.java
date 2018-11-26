import java.io.Serializable;

public class Dive implements Serializable {

    private String place;
    private String date;
    private int depth;
    private int length;
    private static Diver diver;
    private static BreathingDevice breathingDevice;

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public int getDepth() {
        return depth;
    }

    public int getLength() {
        return length;
    }

    public static Diver getDiver() {
        return diver;
    }

    public static BreathingDevice getBreathingDevice() {
        return breathingDevice;
    }


    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDiver(Diver diver) {
        Dive.diver = diver;
    }

    public void setBreathingDevice(BreathingDevice breathingDevice){
        breathingDevice = new BreathingDevice();
    }


    public Dive(String place, String date, int depth, int length,Diver diver,BreathingDevice breathingDevice){
        this.place = place;
        this.date = date;
        this.depth = depth;
        this.length = length;
        Dive.diver = diver;
        Dive.breathingDevice = breathingDevice;


    }
    public Dive(){
        new Dive("No place entered","No date entered",0,0,getDiver(),getBreathingDevice());

    }

    public String toString(){

        String diveToString = "Place: "+getPlace()+"\nDate: "+getDate()+"\nDepth: "+getDepth()+"\nLength: "+getLength()+"\nDiver: "+getDiver()+"Device used: "+getBreathingDevice();
        return diveToString;

    }

}
