package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import pl.vanta.adventofcode.ParserSolver;
import pl.vanta.adventofcode.Utils;

import static java.lang.Integer.parseInt;

public class Day13 implements ParserSolver<List<Day13.Machine>, Integer> {

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
    public Integer solve(List<Machine> parsedInput) {
        return parsedInput.stream()
                .map(Machine::solve)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solve2(List<Machine> parsedInput) {
        return 0;
    }

    record Machine(int ax, int ay, int bx, int by, int px, int py) {
        int solve() {
            double d = ax * by - ay * bx;

            double da = px * by - py * bx;
            double db = ax * py - ay * px;

            double a = da / d;
            double b = db / d;

            if (a != (int) a || b != (int) b) {
                return 0;
            }

            return (int) (3 * a + b);
        }
    }
}
