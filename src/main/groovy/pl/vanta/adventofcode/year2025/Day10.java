package pl.vanta.adventofcode.year2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;
import static java.util.stream.IntStream.range;

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
        int limit = 1 << m.buttons.size();
        var buttonIndexes = range(1, limit)
                .boxed()
                .sorted(comparingInt(Integer::bitCount).thenComparingInt(Integer::intValue))
                .map(Day10::oneBitIndexes)
                .toList();

        return buttonIndexes.stream()
                .filter(buttonIdx -> lightsMatch(m, buttonIdx))
                .findFirst()
                .map(Set::size)
                .orElseThrow();
    }

    private static boolean lightsMatch(Machine m, Set<Integer> buttons) {
        var array = new boolean[m.lights.length];
        for (int buttonIdx : buttons) {
            var btns = m.buttons.get(buttonIdx);
            for (int button : btns) {
                array[button] = !array[button];
            }
        }
        return Arrays.equals(m.lights, array);
    }

    public static Set<Integer> oneBitIndexes(int n) {
        Set<Integer> indexes = new HashSet<>();

        while (n != 0) {
            int lsb = n & -n; // isolate lowest set bit
            int index = Integer.numberOfTrailingZeros(lsb);
            indexes.add(index);
            n &= (n - 1); // clear lowest set bit
        }
        return indexes;
    }

    @Override
    public Integer solve2(List<Machine> parsedInput) {


        return 11;
    }

    public record Machine(boolean[] lights, List<Set<Integer>> buttons) {
        public static Machine parse(String input) {
            input = input.trim();

            int lightsEnd = input.indexOf(']');
            String lightsPart = input.substring(1, lightsEnd);
            boolean[] lights = new boolean[lightsPart.length()];

            for (int i = 0; i < lightsPart.length(); i++) {
                lights[i] = lightsPart.charAt(i) == '#';
            }

            List<Set<Integer>> buttons = new ArrayList<>();

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
