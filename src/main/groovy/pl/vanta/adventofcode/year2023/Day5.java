package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

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
        return getMinimalLocation(parsedInput.seeds.stream(), parsedInput.maps());
    }

    @Override
    public Long solve2(Almanac parsedInput) {
        var stream = Stream.iterate(0, i -> i + 2)
                .limit(parsedInput.seeds.size() / 2)
                .map(index -> LongStream.range(parsedInput.seeds.get(index), parsedInput.seeds.get(index) + parsedInput.seeds.get(index + 1)))
                .flatMap(LongStream::boxed);

        return getMinimalLocation(stream, parsedInput.maps());
    }

    private static Long getMinimalLocation(Stream<Long> stream, Map<String, Mappings> maps) {
        return stream
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
    }

    private record Mapping(long dest, long source, long length) {
    }
}
