package proj1;

import java.util.ArrayList;
import java.util.Scanner;

public class HeartRateDemo {
    public static void main(String[] args) {
        ArrayList<PersonHeartRate> heartRateList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean continueAdding = true;
        while (continueAdding) {
            System.out.println("First name: ");
            String firstName = scanner.nextLine();

            System.out.println("Last name: ");
            String lastName = scanner.nextLine();

            System.out.println("Birth month: ");
            int birthMonth = scanner.nextInt();

            System.out.println("Birth date: ");
            int birthDay = scanner.nextInt();

            System.out.println("Birth year: ");
            int birthYear = scanner.nextInt();

            DateOfBirth dateOfBirth = new DateOfBirth(birthMonth, birthDay, birthYear);

            PersonHeartRate person = new PersonHeartRate(firstName, lastName, dateOfBirth);

            heartRateList.add(person);
            scanner.nextLine();

            System.out.println("Add another person's information? (Y/N): ");
            String choice = scanner.nextLine();

            continueAdding = choice.equalsIgnoreCase("Y");
        }

        System.out.println("Data provided: ");
        for (PersonHeartRate person : heartRateList) {
            person.printData();
            System.out.println();
        }
    }
}
