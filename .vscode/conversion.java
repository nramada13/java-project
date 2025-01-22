public class conversion {
    public static void main(String[] args) {
        //Table header
        System.out.printf("%-10s%-10s%-10s%-10s%n", "Feet", "Meters", "Meters", "Feet");
        //Loop through values for the table
        for (int i = 1; i <= 10; i++){
            double feet = i;
            double meters = 20.0 + (i - 1) * 5.0;
            //Convert feet to meters and meters to feet
            double metersFromFeet = footToMeter(feet);
            double feetFromMeters = meterToFoot(meters);
            //Print table rows
            System.out.printf("%-10.1f%-10.3f%-10.1f%-10.3f%n", feet, metersFromFeet, meters, feetFromMeters);
        }
    }
    //Coversion
    
    public static double footToMeter(double foot) {
        return 0.305 * foot;
    }
    //Conversion
    public static double meterToFoot(double meter) {
        return 3.279 * meter;
    }
}
