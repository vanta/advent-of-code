package pl.vanta.adventofcode.year2025;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.stream;

public class Day10 extends BaseDay<List<Day10.Machine>, Integer> {
    @Override
    public int getDayNumber() {
        return 10;
    }

    @Override
    public List<Machine> parse(String input) {
        return stream(input.split("\n"))
                .map(Machine::parse)
                .toList();
    }

    @Override
    public Integer solve(List<Machine> parsedInput) {
        return parsedInput.stream()
                .map(this::findMinPresses)
                .reduce(0, Integer::sum);
    }

    private int findMinPresses(Machine m) {
        
        
        
        return 1;
    }

    @Override
    public Integer solve2(List<Machine> parsedInput) {

        return 11;
    }

    public record Machine(boolean[] lights, Set<Set<Integer>> buttons) {
        public static Machine parse(String input) {
            input = input.trim();

            int lightsEnd = input.indexOf(']');
            String lightsPart = input.substring(1, lightsEnd);
            boolean[] lights = new boolean[lightsPart.length()];

            for (int i = 0; i < lightsPart.length(); i++) {
                lights[i] = lightsPart.charAt(i) == '#';
            }

            Set<Set<Integer>> buttons = new HashSet<>();

            int pos = lightsEnd + 1;
            while (true) {
                int open = input.indexOf('(', pos);
                int close = input.indexOf(')', open);
                if (open < 0 || close < 0) break;

                String content = input.substring(open + 1, close).trim();

                Set<Integer> button = new HashSet<>();
                if (!content.isEmpty()) {
                    for (String s : content.split(",")) {
                        button.add(Integer.parseInt(s.trim()));
                    }
                }
                buttons.add(button);
                pos = close + 1;
            }


            return new Machine(lights, buttons);

        }
    }
}
