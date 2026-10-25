
public class BoundedStats {
    static <N extends Number> double average(N[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("No values to average");
        }
        double sum = 0;
        for (N value : values) sum += value.doubleValue();
        return sum / values.length;
    }
 
    public static void main(String[] args) {
        Integer[] visits = {13, 19, 28};
        Double[] readings = {2.4, 5.6, 8.8};
        Long[] distances = {120L, 180L, 240L, 300L};
        System.out.printf("Integer average: %.2f%n", average(visits));
        System.out.printf("Double average: %.2f%n", average(readings));
        System.out.printf("Long average: %.2f%n", average(distances));
        try {
            average(new Integer[0]);
        } catch (IllegalArgumentException error) {
            System.out.println("Empty case: " + error.getMessage());
        }
        System.out.println("String is not a subtype of Number.");
        // average(new String[]{"13"}); // Fails the Number bound.
    }
}