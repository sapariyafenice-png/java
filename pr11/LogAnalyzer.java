
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
 
public class LogAnalyzer {
    public static void main(String[] args) throws Exception {
        Path folder = Files.createDirectories(Path.of("log-samples"));
        Files.writeString(folder.resolve("access.log"),
                "INFO startup\nWARN slow response\nINFO close\n");
        Files.writeString(folder.resolve("build.log"),
                "WARN deprecated\nINFO success\n");
        Files.writeString(folder.resolve("events.log"),
                "INFO login\nWARN retry\nWARN quota\nINFO logout\n");
        String keyword = "WARN";
        long totalLines = 0;
        long totalMatches = 0;
        for (String name : List.of(
                "access.log", "build.log", "events.log")) {
            Path file = folder.resolve(name);
            BasicFileAttributes attributes = Files.readAttributes(
                    file, BasicFileAttributes.class);
            long lines = 0;
            long matches = 0;
            try (BufferedReader reader = Files.newBufferedReader(
                    file, StandardCharsets.UTF_8)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines++;
                    if (line.contains(keyword)) matches++;
                }
            }
            System.out.printf("%s: %d bytes, lines=%d, WARN=%d%n",
                    name, attributes.size(), lines, matches);
            totalLines += lines;
            totalMatches += matches;
        }
        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing " + keyword + ": "
                + totalMatches);
        if (totalLines != 9 || totalMatches != 4) {
            throw new AssertionError("Unexpected log counts");
        }
    }
}
