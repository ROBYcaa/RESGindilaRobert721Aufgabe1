import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.parsers.*;
import org.w3c.dom.*;

class NinjaEvent {
    int id;
    String name;
    String rank;
    String description;
    LocalDate date;
    double power;

    public NinjaEvent(int id, String name, String rank, String description, LocalDate date, double power) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.description = description;
        this.date = date;
        this.power = power;
    }
}

public class NinjaArchive {
    private static List<NinjaEvent> events = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String filename = "src/ninja_events.tsv"; // Beispieldatei
        readData(filename);

    }

    private static void readData(String filename) throws Exception {
        if (filename.endsWith(".tsv")) {
            readTSV(filename);
        }
    }

    private static void readTSV(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines.subList(1, lines.size())) {
            String[] parts = line.split("\t");
            events.add(new NinjaEvent(
                    Integer.parseInt(parts[0]),
                    parts[1], parts[2], parts[3],
                    LocalDate.parse(parts[4]),
                    Double.parseDouble(parts[5])
            ));
        }
    }





}
