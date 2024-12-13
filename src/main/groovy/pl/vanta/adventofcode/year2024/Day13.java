package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import pl.vanta.adventofcode.ParserSolver;
import pl.vanta.adventofcode.Utils;

import static java.lang.Integer.parseInt;

public class Day13 implements ParserSolver<List<Day13.Machine>, Long> {

    private static final long FIX = 10000000000000L;

    @Override
    public int getDayNumber() {
        return 13;
    }

    @Override
    public List<Machine> parse(String lines) {
        var result = new ArrayList<Machine>();
        var tokenizer = new StringTokenizer(lines, "\n");

        while (tokenizer.hasMoreTokens()) {
            var linea = tokenizer.nextToken();
            var lineb = tokenizer.nextToken();
            var linep = tokenizer.nextToken();

            var ax = linea.substring(12, 14);
            var ay = linea.substring(18, 20);
            var bx = lineb.substring(12, 14);
            var by = lineb.substring(18, 20);

            var p = Utils.find("X=(\\d+), Y=(\\d+)", linep);

            result.add(new Machine(parseInt(ax), parseInt(ay),
                    parseInt(bx), parseInt(by),
                    parseInt(p.get(0)), parseInt(p.get(1))));
        }

        return result;
    }

    @Override
    public Long solve(List<Machine> parsedInput) {
        return parsedInput.stream()
                .map(machine -> machine.solve(0))
                .reduce(0L, Long::sum);
    }

    @Override
    public Long solve2(List<Machine> parsedInput) {
        return parsedInput.stream()
                .map(machine -> machine.solve(FIX))
                .reduce(0L, Long::sum);
    }

    record Machine(int ax, int ay, int bx, int by, long px, long py) {
        long solve(long fix) {
            int d = ax * by - ay * bx;

            long npx = px + fix;
            long npy = py + fix;

            long da = npx * by - npy * bx;
            long db = ax * npy - ay * npx;

            double a = (double) da / d;
            double b = (double) db / d;

            if (a != (long) a || b != (long) b) {
                return 0;
            }

            return (long) (3 * a + b);
        }
    }
}
