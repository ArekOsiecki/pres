import java.io.Serializable;

public class Diver implements Serializable {

        private static String name;
        private static int age;
        private static int sac; //SAC(Surface air consumption) is a unit of measure used to determine amount of air needed for particular dive in bars per minute
        private static int experienceLevel; //Different experience levels allow for different depths
        private static int pressureGroup;



    public static String getName() {
        return name;
    }

    public static int getAge() {
        return age;
    }

    public static int getSac() {
        return sac;
    }

    public static int getExperienceLevel() {
        return experienceLevel;
    }

    public static int getPressureGroup(){return pressureGroup;}



    public void setName(String name) {
        Diver.name = name;
    }

    public void setAge(int age) {
        Diver.age = age;
    }

    public void setSac(int sac) {
        Diver.sac = sac;
    }

    public void setExperienceLevel(int experienceLevel) {
        Diver.experienceLevel = experienceLevel;
    }

    public static void setPressureGroup(int pressureGroup) {
        Diver.pressureGroup = pressureGroup;
    }





    public Diver(String name, int age, int sac, int experienceLevel,int pressureGroup){
        Diver.name = name;
        Diver.age = age;
        Diver.sac = sac;
        Diver.experienceLevel = experienceLevel;
        Diver.pressureGroup = pressureGroup;


    }
    public Diver(){
        new Diver("No name entered",0,0,0,0);
    }

    @Override
    public String toString() {
        String diverToString = "\nDiver name: "+getName()+"\nDiver age: "+getAge()+"\nDiver SAC: "
                +getSac()+"\nDiver experience level: "+getExperienceLevel();
        return diverToString;
    }
}
