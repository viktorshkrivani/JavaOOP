package proj1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class PersonHeartRate {
    private String firstName;
    private String lastName;
    private DateOfBirth dateOfBirth;

    public PersonHeartRate(String firstName, String lastName,DateOfBirth dateOfBirth){

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

    }

    public  String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName =firstName;
    }

    public  String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private int calculateAge(){

        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(dateOfBirth.getYear(), dateOfBirth.getMonth(), dateOfBirth.getDay());
        long ageInYears = ChronoUnit.YEARS.between(birthDate, today);
        return (int) ageInYears;

    }

    public int calulateMaxHeartRate() {
        int age = calculateAge();
        int maxBPM = 220 -age;
        return maxBPM;
    }

    public String calculateTargetHeartRateRange(){
        int maxHeartRate = calulateMaxHeartRate();
        int lowerRange = (int) (maxHeartRate * 0.5);
        int upperRange = (int) (maxHeartRate * 0.85);
        return lowerRange + " - " + upperRange;
    }

    public void printData(){

        System.out.println(lastName + ", " + firstName);
        System.out.println("Max heart rate: " + calulateMaxHeartRate());
        System.out.println("Target heart rate: " + calculateTargetHeartRateRange());

    }
}
