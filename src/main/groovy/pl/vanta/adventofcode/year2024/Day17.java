package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;
import static java.util.stream.Collectors.joining;

public class Day17 implements ParserSolver<Day17.Input, String> {

    @Override
    public int getDayNumber() {
        return 17;
    }

    @Override
    public Day17.Input parse(String lines) {
        var tokenizer = new StringTokenizer(lines, "\n");

        var a = parseInt(tokenizer.nextToken().substring(12));
        var b = parseInt(tokenizer.nextToken().substring(12));
        var c = parseInt(tokenizer.nextToken().substring(12));
        var p = tokenizer.nextToken().substring(9).split(",");

        return new Day17.Input(a, b, c, Arrays.stream(p).mapToInt(Integer::parseInt).toArray());
    }

    @Override
    public String solve(Day17.Input input) {
        var computer = new Computer(input.a, input.b, input.c, input.program);

        var out = computer.run();

        return Arrays.stream(out)
                .mapToObj(String::valueOf)
                .collect(joining(","));
    }

    @Override
    public String solve2(Day17.Input parsedInput) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if(i % 1_000_000 == 0) {
                System.out.println(i);
            }

            var computer = new Computer(i, parsedInput.b, parsedInput.c, parsedInput.program);

            var out = computer.run();

            if(Arrays.equals(out, parsedInput.program)) {
                return String.valueOf(i);
            }
        }

        return "";
    }

    public record Input(int a, int b, int c, int[] program) {
    }

    class Computer {
        int a;
        int b;
        int c;
        int[] program;

        private int ip = 0;
        private final List<Integer> out = new ArrayList<>();

        Computer(int a, int b, int c, int[] program) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.program = program;
        }

        private void adv(int param) {
            a = a / (int) pow(2, combo(param));

            ip += 2;
        }

        private void bxl(int param) {
            b = b ^ param;

            ip += 2;
        }

        private void bst(int param) {
            b = combo(param) % 8;

            ip += 2;
        }

        private void jnz(int param) {
            if (a != 0) {
                ip = param;
            } else {
                ip += 2;
            }
        }

        private void bxc(int param) {
            b = b ^ c;

            ip += 2;
        }

        private void out(int param) {
            out.add(combo(param) % 8);

            ip += 2;
        }

        private void bdv(int param) {
            b = a / (int) pow(2, combo(param));

            ip += 2;
        }

        private void cdv(int param) {
            c = a / (int) pow(2, combo(param));

            ip += 2;
        }

        private int combo(int param) {
            return switch (param) {
                case 0, 1, 2, 3 -> param;
                case 4 -> a;
                case 5 -> b;
                case 6 -> c;
                default -> throw new IllegalArgumentException("Unknown param: " + param);
            };
        }

        int[] run() {
            while (ip < program.length) {
                var opcode = program[ip];
                var param = program[ip + 1];

                switch (opcode) {
                    case 0 -> adv(param);
                    case 1 -> bxl(param);
                    case 2 -> bst(param);
                    case 3 -> jnz(param);
                    case 4 -> bxc(param);
                    case 5 -> out(param);
                    case 6 -> bdv(param);
                    case 7 -> cdv(param);
                    default -> throw new IllegalArgumentException("Unknown opcode: " + opcode);
                }
            }

//            System.out.println("a=" + a + ", b=" + b + ", c=" + c + ", out=" + out);

            return out.stream().mapToInt(i -> i).toArray();
        }
    }
}
