package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Day5 implements ParserSolver<Day5.Almanac, Integer> {

    @Override
    public int getDayNumber() {
        return 5;
    }

    @Override
    public Almanac parse(String lines) {
        var scanner = new Scanner(lines);

        var seeds = Arrays.stream(scanner.nextLine().substring(7).split(" "))
                .map(Integer::parseInt)
                .toList();

        var list = new HashMap<String, Mappings>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();

            if (line.contains("map")) {
                list.put(line.replaceAll(" map:", ""), readMapping(line, scanner));
            }
        }

        return new Almanac(seeds, list);
    }

    private Mappings readMapping(String name, Scanner scanner) {
        String line;

        var list = new ArrayList<Mapping>();
        while (scanner.hasNextLine() && !(line = scanner.nextLine()).isEmpty()) {
            var split = line.split(" ");

            list.add(new Mapping(parseInt(split[0]), parseInt(split[1]), parseInt(split[2])));
        }

        return new Mappings(name, list);
    }

    @Override
    public Integer solve(Almanac parsedInput) {
        return parsedInput.seeds.stream()
                .map(seed -> parsedInput.maps().get("seed-to-soil").getMapping(seed))
                .map(soil -> parsedInput.maps().get("soil-to-fertilizer").getMapping(soil))
                .map(fertilizer -> parsedInput.maps().get("fertilizer-to-water").getMapping(fertilizer))
                .map(water -> parsedInput.maps().get("water-to-light").getMapping(water))
                .map(light -> parsedInput.maps().get("light-to-temperature").getMapping(light))
                .map(temperature -> parsedInput.maps().get("temperature-to-humidity").getMapping(temperature))
                .map(humidity -> parsedInput.maps().get("humidity-to-location").getMapping(humidity))
                .min(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public Integer solve2(Almanac parsedInput) {
        return 0;
    }

    public record Almanac(List<Integer> seeds, Map<String, Mappings> maps) {
    }

    private record Mappings(String name, List<Mapping> mappings) {
        int getMapping(int number) {
            return mappings.stream()
                    .filter(e -> e.source < number && number < e.source + e.length)
                    .findFirst()
                    .map(e -> number - e.source + e.dest)
                    .orElse(number);
        }
    }

    private record Mapping(int dest, int source, int length) {
    }
}
