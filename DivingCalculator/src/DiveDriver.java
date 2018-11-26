import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiveDriver{


    public static void main(String[]args){



    }

    public static String validateText(String text) throws RuntimeException{ //method to validate any text input


        String textToValidate = text;
        for (char c : textToValidate.toCharArray()) {

            if (!Character.isLetter(c)){
                throw new RuntimeException("You must use only letters to enter name while you used "+textToValidate+"");

            }

        }
        return textToValidate;
    }
    public static boolean validateNumber(String number) throws RuntimeException {//method to validate any number input


        boolean valid = false;
        String numberToValidate = number;

        while (!valid) {
            for (char c : numberToValidate.toCharArray()) {

                if (!Character.isDigit(c)) {
                    throw new RuntimeException("You must use numbers for input while you used " + numberToValidate + "");

                } else {

                    valid = true;
                }
            }
        }

        return valid;
    }
    public static boolean validateAgeRange(String diverAgeText) throws RuntimeException{//method to validate age against impossible input

        boolean valid;
        int ageRangeToValidate = Integer.parseInt(diverAgeText);
        if(ageRangeToValidate < 10 || ageRangeToValidate > 85) {

            throw new RuntimeException("Age is " + ageRangeToValidate +" and should be more than 6 and less than 85");
        }else{
            valid = true;
        }

        return valid;
    }
    public static boolean validateSacRange(String diverSacText) throws RuntimeException{//method to validate Surface Air Consumption

        boolean valid;
        int sacRangeToValidate = Integer.parseInt(diverSacText);

        if(sacRangeToValidate < 10 || sacRangeToValidate > 25) {

            throw new RuntimeException("SAC is " + sacRangeToValidate+ " and should be more than 10 and less than 25");
        }else{
            valid = true;
        }

        return valid;
    }
    public static boolean validateExperience(int level, int age) throws RuntimeException{//method to validate experience level against age(legal requirements of certificates)
        boolean valid;

        if(level == 3 && age<18){
            throw new RuntimeException("You need to be at least 18 years old to become a Divemaster!");
        }else if(level == 2 && age<12){
            throw new RuntimeException("You need to be at least 12 years old to hold Advanced Certificate!");
        }else if(level == 1 && age<10){
            throw new RuntimeException("You need to be at least 10 years old to become a Open Water Diver!");
        }else if(level == 0 && age>15){
            throw new RuntimeException("You should upgrade your certificate!");
        }else{
            valid = true;
        }
        return valid;

    }

        public static String validateDiveDate(String date) {//method to validate date


            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

            try {
            date= format.format(new Date());
            Date dateValidated       = format.parse ( date );

            } catch (ParseException e) {
                System.out.println("Date " + date + " is not valid according to " +
                        (format).toPattern() + " pattern.");
            }

            return date;
        }


    public static int validateDepth(String depth) throws RuntimeException {//method to validate depth. depth is converted from range to value for future manipulation


        int depthToValidate = Integer.parseInt(depth);
        int validatedDepth;


        if (depthToValidate > 40) {

            validatedDepth = 42;

        } else if ( depthToValidate <= 40 && depthToValidate > 35 ) {

            validatedDepth = 40;

        } else if (depthToValidate <= 35 && depthToValidate > 30 ) {

            validatedDepth = 35;

        } else if (depthToValidate <= 30 && depthToValidate > 25 ) {

            validatedDepth = 30;

        } else if (depthToValidate <= 25 && depthToValidate > 22)  {

            validatedDepth = 25;

        } else if (depthToValidate <= 22 && depthToValidate > 20) {

            validatedDepth = 22;

        } else if (depthToValidate <= 20 && depthToValidate > 18) {

            validatedDepth = 20;

        } else if (depthToValidate <= 18 && depthToValidate > 16) {

            validatedDepth = 18;

        } else if (depthToValidate <= 16 && depthToValidate > 14) {

            validatedDepth = 16;

        } else if (depthToValidate <= 14 && depthToValidate > 12) {

            validatedDepth = 14;

        } else if (depthToValidate <= 12 && depthToValidate > 10) {

            validatedDepth = 12;

        } else {

            validatedDepth = 10;
        }

            return validatedDepth;
        }


    public static boolean validateDepthAndExperience(int depth, int exp)  throws RuntimeException{//method to validate chosen depth against experience level

        boolean valid;

        if (depth > 13 && exp<= 1) {
            throw new RuntimeException("You need to hold Advanced Certificate to dive below 13 meters deep!");
        } else if (depth > 30 && exp <= 2) {
            throw new RuntimeException("You need to be a Divemaster to dive below 30 meters deep!");
        }else if (depth == 42){
            throw new RuntimeException("You should never dive that deep!");
        }else{
            valid = true;
        }

        return valid;


    }
    public static int validateDiveLength(int depth,String lengthString) throws RuntimeException {//method to validate length of dive against it's time

        int length = Integer.parseInt(lengthString);
        int validatedLength;


        if((length >= 7 && depth == 40)||(length >= 14 && depth == 35)||(length >= 20 && depth == 30)
                ||(length >= 29 && depth == 25)||(length >= 37 && depth == 22)||(length >= 45 && depth == 20)
                ||(length >= 56 && depth == 21)||(length >= 71 && depth == 16)||(length >= 98 && depth == 14)
                ||(length >= 147 && depth == 12)||(length >= 219 && depth == 10)){


            throw new RuntimeException("That dive is in no decompression limit! Do not plan recreational dives in this range!");
        }else{

            validatedLength = length;

        }

        return validatedLength;

    }
    public  static boolean validateBreathingDevice(int device, int exp) throws RuntimeException{//method to validate breathing device against experience

        boolean valid;

        if((device == 1) && (exp < 2)){
            throw new RuntimeException("You need to hold at least Advanced Certificate in order to use Rebreather!");

        }else{
            valid = true;
        }
        return valid;

    }

    public static String rebreatherDive (int depth, int length, int filter) throws RuntimeException{//method to calculate nitrox composition for chosen depth when using rebreather device


        double nitroxCalc = ((((depth)/10)+1)*1.4)*10;
        int nitrox = (int)nitroxCalc;
        String nitroxTimeDepth;

        if (filter<length){

            throw new RuntimeException("You have entered "+length+" , while your filter have capacity of "+filter+" minutes!");

        }else {
            nitroxTimeDepth = "For your planned maximum depth your breathing mixture should contain " + nitrox + "% nitrox" +
                    "\nYou can dive up to 3 hours" +
                    "\nYou can dive again after completing post-dive and pre-dive procedures on surface";
        }
        return nitroxTimeDepth;
    }

    public  static int airVolume (int depth,int length, int sac) {//method to calculate air litres needed for planned dive. Air used multiplied by depth and length of dive


        double depthC = (double)depth;
        double lengthC = (double)length;
        double sacC = (double)sac;
        double airUsed;
        double depthMultiplier;
        double airNeeded;
        int volume;

        airUsed = sacC*lengthC;

        depthMultiplier = (depthC/10)+1;

        airNeeded = airUsed*depthMultiplier;
        volume = (int)airNeeded;

        return volume;




    }

    public static int tanksNeeded (int size){//method to calculate tanks needed. Air needed divided by tank volume


        double sizeC = (double)size;
        double tank =14;
        double bars = sizeC/tank;
        double tanks = bars/230;

        double roundedTanks = Math.round(tanks)+1;



        return (int)roundedTanks;


    }





}


