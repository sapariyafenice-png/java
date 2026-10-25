
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
 
public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> logins = List.of("zoya", "dev", "kiran", "dev",
                "zoya", "mehul", "isha", "kiran");
        HashSet<String> unique = new HashSet<>(logins);
        LinkedHashSet<String> firstSeen = new LinkedHashSet<>(logins);
        List<String> ordered = new ArrayList<>(firstSeen);
        int limit = Math.min(3, ordered.size());
        System.out.println("Login sequence: " + logins);
        System.out.println("Unique users: " + unique.size());
        System.out.println("First three distinct: "
                + ordered.subList(0, limit));
    }
}
