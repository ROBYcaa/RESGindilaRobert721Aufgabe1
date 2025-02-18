import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class NinjaEvent {
    int id;
    String characterName;
    String rank;
    String description;
    LocalDate date;
    double powerPoints;

    public NinjaEvent(int id, String characterName, String rank, String description, LocalDate date, double powerPoints) {
        this.id = id;
        this.characterName = characterName;
        this.rank = rank;
        this.description = description;
        this.date = date;
        this.powerPoints = powerPoints;
    }
}

public class NinjaArchive {
    public static void main(String[] args) {
        List<NinjaEvent> events = readEventsFromFile("src/ninja_events.tsv");
        displayHighPowerNinjas(events, 5000);

    }

    private static List<NinjaEvent> readEventsFromFile(String fileName) {
        List<NinjaEvent> events = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header line
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\t");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String characterName = parts[1].trim();
                    String rank = parts[2].trim();
                    String description = parts[3].trim();
                    LocalDate date = LocalDate.parse(parts[4].trim());
                    double powerPoints = Double.parseDouble(parts[5].trim());
                    events.add(new NinjaEvent(id, characterName, rank, description, date, powerPoints));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    private static void displayHighPowerNinjas(List<NinjaEvent> events, double threshold) {
        Set<String> uniqueNinjas = new LinkedHashSet<>();
        for (NinjaEvent event : events) {
            if (event.powerPoints > threshold) {
                uniqueNinjas.add(event.characterName);
            }
        }
        uniqueNinjas.forEach(System.out::println);
    }

}
