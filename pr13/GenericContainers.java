
import java.util.NoSuchElementException;
 
class Box<T> {
    private T value;
    void set(T value) { this.value = value; }
    T get() { return value; }
}
 
class Pair<A, B> {
    private final A first;
    private final B second;
    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
    A getFirst() { return first; }
    B getSecond() { return second; }
}
 
public class GenericContainers {
    static <T> T first(T[] values) {
        if (values.length == 0) {
            throw new NoSuchElementException("Array is empty");
        }
        return values[0];
    }
 
    public static void main(String[] args) {
        Box<String> label = new Box<>();
        label.set("Robotics");
        Box<Integer> count = new Box<>();
        count.set(18);
        String text = label.get();
        int number = count.get();
        Pair<String, Integer> club = new Pair<>(text, number);
        Pair<Integer, Double> measurement = new Pair<>(6, 2.75);
        System.out.println("Boxes: " + text + ", " + number);
        System.out.println("Pair: " + club.getFirst()
                + " / " + club.getSecond());
        System.out.println("Other pair: " + measurement.getFirst()
                + " / " + measurement.getSecond());
        String city = first(new String[]{"Surat", "Vadodara"});
        Integer code = first(new Integer[]{62, 87});
        System.out.println("First elements: " + city + ", " + code);
        try {
            first(new String[0]);
        } catch (NoSuchElementException error) {
            System.out.println("Empty case: " + error.getMessage());
        }
        // count.set("eighteen"); // Rejected by the compiler.
    }
}
