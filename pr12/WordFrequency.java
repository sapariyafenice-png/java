
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
 
public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Practice builds skill; skill grows when "
                + "practice meets patience. Practice brings confidence.";
        HashMap<String, Integer> frequencies = new HashMap<>();
        for (String word : paragraph.toLowerCase(Locale.ROOT)
                .split("[^a-z]+")) {
            if (!word.isEmpty()) frequencies.merge(word, 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> words =
                new ArrayList<>(frequencies.entrySet());
        Comparator<Map.Entry<String, Integer>> order = Comparator
                .<Map.Entry<String, Integer>>comparingInt(
                        Map.Entry::getValue).reversed()
                .thenComparing(Map.Entry::getKey);
        words.sort(order);
        System.out.println("Words sorted by count, then alphabetically:");
        for (Map.Entry<String, Integer> entry : words) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
