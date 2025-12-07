package pl.vanta.adventofcode.year2025;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import pl.vanta.adventofcode.Utils;

public class Day7 extends BaseDay<List<String>, Integer> {
    @Override
    public int getDayNumber() {
        return 7;
    }

    @Override
    public List<String> parse(String input) {
        return Arrays.stream(input.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        int start = parsedInput.getFirst().indexOf('S');
        int splits = 0;
        var beams = new HashSet<Integer>();
        beams.add(start);
        
        for(String line : parsedInput.subList(1, parsedInput.size())) {
            var indexes = Utils.indexesOf(line, '^');
            for(int index : indexes) {
                if(beams.contains(index)) {
                    splits++;
                    beams.remove(index);
                    beams.add(index - 1);
                    beams.add(index + 1);
                }
            }
        }
        
        return splits;
    }

    @Override
    public Integer solve2(List<String> parsedInput) {

        return 0;
    }

}
