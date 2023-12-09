package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.*;
import java.util.function.LongUnaryOperator;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;
import static java.util.stream.LongStream.iterate;

public class Day5 implements ParserSolver<Day5.Almanac, Long> {

    @Override
    public int getDayNumber() {
        return 5;
    }

    @Override
    public Almanac parse(String lines) {
        var scanner = new Scanner(lines);

        var seeds = Arrays.stream(scanner.nextLine().substring(7).split(" "))
                .map(Long::parseLong)
                .toList();

        var list = new HashMap<String, Mappings>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();

            if (line.contains("map")) {
                list.put(line.replaceAll(" map:", ""), readMapping(scanner));
            }
        }

        return new Almanac(seeds, list);
    }

    private Mappings readMapping(Scanner scanner) {
        String line;

        var list = new ArrayList<Mapping>();
        while (scanner.hasNextLine() && !(line = scanner.nextLine()).isEmpty()) {
            var split = line.split(" ");

            list.add(new Mapping(parseLong(split[0]), parseLong(split[1]), parseLong(split[2])));
        }

        return new Mappings(list);
    }

    @Override
    public Long solve(Almanac parsedInput) {
        var maps = parsedInput.maps();
        return parsedInput.seeds.stream()
                .map(seed -> maps.get("seed-to-soil").getMapping(seed))
                .map(soil -> maps.get("soil-to-fertilizer").getMapping(soil))
                .map(fertilizer -> maps.get("fertilizer-to-water").getMapping(fertilizer))
                .map(water -> maps.get("water-to-light").getMapping(water))
                .map(light -> maps.get("light-to-temperature").getMapping(light))
                .map(temperature -> maps.get("temperature-to-humidity").getMapping(temperature))
                .map(humidity -> maps.get("humidity-to-location").getMapping(humidity))
                .min(Long::compareTo)
                .orElse(0L);
    }

    @Override
    public Long solve2(Almanac parsedInput) {
        var ranges = Stream.iterate(0, i -> i + 2)
                .limit(parsedInput.seeds.size() / 2)
                .map(index -> new Range(parsedInput.seeds.get(index), parsedInput.seeds.get(index + 1)))
                .toList();

        var maps = parsedInput.maps();

        return iterate(1, i -> i + 1)
                .filter(l -> contains(locationToSeed(maps).applyAsLong(l), ranges))
                .findFirst()
                .orElseThrow();
    }

    private LongUnaryOperator locationToSeed(Map<String, Mappings> maps) {
        return number -> Optional.of(number)
                .map(location -> maps.get("humidity-to-location").getMappingReversed(location))
                .map(humidity -> maps.get("temperature-to-humidity").getMappingReversed(humidity))
                .map(temperature -> maps.get("light-to-temperature").getMappingReversed(temperature))
                .map(light -> maps.get("water-to-light").getMappingReversed(light))
                .map(water -> maps.get("fertilizer-to-water").getMappingReversed(water))
                .map(fertilizer -> maps.get("soil-to-fertilizer").getMappingReversed(fertilizer))
                .map(soil -> maps.get("seed-to-soil").getMappingReversed(soil))
                .orElseThrow();
    }

    private static boolean contains(long number, List<Range> ranges) {
        return ranges.stream()
                .anyMatch(range -> range.start() <= number && number < (range.start() + range.length()));
    }

    public record Almanac(List<Long> seeds, Map<String, Mappings> maps) {
    }

    private record Mappings(List<Mapping> mappings) {
        long getMapping(long number) {
            return mappings.stream()
                    .filter(e -> e.source <= number && number < e.source + e.length)
                    .findFirst()
                    .map(e -> number - e.source + e.dest)
                    .orElse(number);
        }

        long getMappingReversed(long number) {
            return mappings.stream()
                    .filter(e -> e.dest <= number && number < e.dest + e.length)
                    .findFirst()
                    .map(e -> number - e.dest + e.source)
                    .orElse(number);
        }
    }

    private record Mapping(long dest, long source, long length) {
    }

    private record Range(long start, long length) {
    }
}
