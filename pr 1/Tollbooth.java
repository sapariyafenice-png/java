import java.util.Scanner;

public class Tollbooth {

    record Vehicle(String number, String type) {}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalToll = 0;
        int bike = 0;
        int car = 0;
        int truck = 0;

        while (true) {
            System.out.print("Enter vehicle number (or done): ");
            String number = sc.nextLine();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.nextLine().toLowerCase();

            Vehicle v = new Vehicle(number, type);

            int toll = switch (v.type()) {
                case "bike" -> {
                    bike++;
                    yield 20;
                }
                case "car" -> {
                    car++;
                    yield 50;
                }
                case "truck" -> {
                    truck++;
                    yield 150;
                }
                default -> {
                    System.out.println("Invalid vehicle type");
                    yield 0;
                }
            };

            totalToll += toll;
        }

        String mostFrequent;

        if (car >= bike && car >= truck) {
            mostFrequent = "car";
        } else if (truck >= bike && truck >= car) {
            mostFrequent = "truck";
        } else {
            mostFrequent = "bike";
        }

        System.out.println("Total toll: " + totalToll);
        System.out.println("Most frequent: " + mostFrequent);

        sc.close();
    }
}