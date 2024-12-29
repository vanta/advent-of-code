package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Character.getNumericValue;
import static java.lang.Integer.parseInt;
import static java.util.Collections.swap;
import static java.util.stream.IntStream.generate;
import static java.util.stream.IntStream.range;

public class Day9 implements ParserSolver<String, Long> {

    @Override
    public int getDayNumber() {
        return 9;
    }

    @Override
    public String parse(String lines) {
        return lines.replace("\\n", "").trim();
    }

    @Override
    public Long solve(String parsedInput) {
        var mapped = map(parsedInput);

        var rearranged = rearrange(mapped);

        return checksum(rearranged);
    }

    private static List<Integer> map(String parsedInput) {
        var result = new ArrayList<Integer>();

        for (int i = 0; i < parsedInput.length(); i++) {
            var c = parsedInput.charAt(i);
            var v = parseInt(String.valueOf(c));
            var val = i / 2;

            for (int j = 0; j < v; j++) {
                if (val * 2 == i) {
                    result.add(val);
                } else {
                    result.add(null);
                }
            }
        }

        return result;
    }

    private List<Integer> rearrange(List<Integer> mapped) {
        var result = new ArrayList<>(mapped);

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == null) {
                var lastNumberIndex = findLastNumberIndex(result);

                if (lastNumberIndex < i) {
                    break;
                }

                swap(result, i, lastNumberIndex);
            }
        }

        return result;
    }

    private int findLastNumberIndex(List<Integer> mapped) {
        for (int i = mapped.size() - 1; i >= 0; i--) {
            if (mapped.get(i) != null) {
                return i;
            }
        }

        return -1;
    }

    private long checksum(List<Integer> rearranged) {
        return range(0, rearranged.size())
                .map(i -> {
                    var val = rearranged.get(i);
                    return val == null ? 0 : i * val;
                })
                .mapToLong(i -> i)
                .boxed()
                .reduce(0L, Long::sum);
    }

    @Override
    public Long solve2(String parsedInput) {
        var mapped = map2(parsedInput);

        var rearranged = rearrange2(mapped);

        return checksum2(rearranged);
    }

    private List<File> map2(String parsedInput) {
        return range(0, parsedInput.length())
                .mapToObj(i -> new File(i % 2 == 0 ? i / 2 : -1, getNumericValue(parsedInput.charAt(i))))
                .toList();
    }

    private List<File> rearrange2(List<File> mapped) {
        var result = new ArrayList<>(mapped);

        for (int index = mapped.size() - 1; index >= 0; index--) {
            var file = result.get(index);

//            System.out.println("Checking: " + file);

            if (file.id != -1) {
                int indexEmpty = findFirstEmptySpace(result, file.length);

                if (indexEmpty >= 0 && indexEmpty < index) {
                    var empty = result.get(indexEmpty);

                    if (empty.length == file.length) {
                        result.set(index, empty);
                        result.set(indexEmpty, file);
                    } else {
                        result.set(index, new File(-1, file.length));
                        result.set(indexEmpty, file);
                        result.add(indexEmpty + 1, new File(-1, empty.length - file.length));
                        index++;
                    }

//                    System.out.println(toString(result));
                }
            }
        }

        return result;
    }

    private int findFirstEmptySpace(List<File> mapped, int length) {
        for (int i = 0; i < mapped.size(); i++) {
            if (mapped.get(i).id == -1 && mapped.get(i).length >= length) {
                return i;
            }
        }

        return -1;
    }

    private long checksum2(List<File> rearranged) {
        var result = rearranged.stream()
                .flatMapToInt(file -> generate(() -> file.id == -1 ? 0 : file.id).limit(file.length))
                .boxed()
                .toList();

        return checksum(result);
    }

    private String toString(List<File> list) {
        var sb = new StringBuilder();

        for (var file : list) {
            if (file.id == -1) {
                sb.append(".".repeat(file.length));
            } else {
                sb.append(String.valueOf(file.id).repeat(file.length));
            }
        }

        return sb.toString();
    }

    public record File(int id, int length) {
    }
}
