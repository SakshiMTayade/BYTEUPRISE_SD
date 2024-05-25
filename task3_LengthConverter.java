package byteUprise;
import java.util.Scanner;

public class task3_LengthConverter {
        // Conversion constants
        private static final double METERS_TO_FEET = 3.28084;
        private static final double METERS_TO_KILOMETERS = 0.001;
        private static final double FEET_TO_METERS = 0.3048;
        private static final double KILOMETERS_TO_METERS = 1000;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Get length value from the user
            System.out.print("Enter length value: ");
            double length = scanner.nextDouble();

            // Get unit of length from the user
            System.out.print("Enter unit of length (meters, feet, kilometers): ");
            String unit = scanner.next().toLowerCase();

            // Convert and display results
            switch (unit) {
                case "meters":
                    convertFromMeters(length);
                    break;
                case "feet":
                    convertFromFeet(length);
                    break;
                case "kilometers":
                    convertFromKilometers(length);
                    break;
                default:
                    System.out.println("Invalid unit of length entered.");
                    break;
            }

            scanner.close();
        }

        private static void convertFromMeters(double length) {
            double feet = length * METERS_TO_FEET;
            double kilometers = length * METERS_TO_KILOMETERS;
            System.out.printf("%.2f meters is equal to %.2f feet and %.2f kilometers.%n", length, feet, kilometers);
        }

        private static void convertFromFeet(double length) {
            double meters = length * FEET_TO_METERS;
            double kilometers = meters * METERS_TO_KILOMETERS;
            System.out.printf("%.2f feet is equal to %.2f meters and %.2f kilometers.%n", length, meters, kilometers);
        }

        private static void convertFromKilometers(double length) {
            double meters = length * KILOMETERS_TO_METERS;
            double feet = meters * METERS_TO_FEET;
            System.out.printf("%.2f kilometers is equal to %.2f meters and %.2f feet.%n", length, meters, feet);
        }

}
