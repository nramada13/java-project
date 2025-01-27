import java.util.GregorianCalendar;

public class myDate {
    // Data fields
    private int year;
    private int month; 
    private int day;

    // No-arg constructor for the current date
    public myDate() {

        GregorianCalendar calendar = new GregorianCalendar();
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }
    // Constructor with specified elapsed time since January 1, 1970
    public myDate(long elapsedTime) {
        setDate(elapsedTime);
    }
    // Constructor with specified year, month, and day
    public myDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    // Getter methods
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    // Method to set a new date based on elapsed time since January 1, 1970
    public void setDate(long elapsedTime) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);

    }
    // Main method for testing
    public static void main(String[] args) {

        // Test case 1: Create a MyDate object for the current date
        myDate currentDate = new myDate();
        System.out.println("Current date:");
        System.out.println("Year: " + currentDate.getYear());
        System.out.println("Month: " + currentDate.getMonth());
        System.out.println("Day: " + currentDate.getDay());

        // Test case 2: Create a MyDate object with a specified elapsed time

        myDate specificDate = new myDate(34355555133101L);
        System.out.println("\nDate for elapsed time 34355555133101L:");
        System.out.println("Year: " + specificDate.getYear());
        System.out.println("Month: " + specificDate.getMonth());
        System.out.println("Day: " + specificDate.getDay());

    }
}