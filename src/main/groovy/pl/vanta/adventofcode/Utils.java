package pl.vanta.adventofcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import static java.lang.System.out;

public class Utils {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";
    private static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";

    public static String red(String s) {
        return WHITE_BACKGROUND_BRIGHT + RED + s + RESET;
    }
    public static String red(char c) {
        return WHITE_BACKGROUND_BRIGHT + RED + c + RESET;
    }

    public static String green(String s) {
        return GREEN_BACKGROUND_BRIGHT + RED + s + RESET;
    }
    public static String green(char c) {
        return GREEN_BACKGROUND_BRIGHT + RED + c + RESET;
    }

    public static void printRed(String s) {
        out.print(red(s));
    }

    public static <T> List<Pair<T,T>> generatePairs(List<T> input) {
        var list = new ArrayList<Pair<T,T>>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                list.add(Pair.of(input.get(i), input.get(j)));
            }
        }

        return list;
    }

    public static boolean inBounds(Location location, int x, int y) {
        return location.x() >= 0 && location.x() < x && location.y() >= 0 && location.y() < y;
    }

    public static void print(Collection<Location> locations, char c, int x, int y){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (locations.contains(new Location(i, j))) {
                    out.print(c);
                } else {
                    out.print(".");
                }
            }
            out.println();
        }
    }

    public static void print(Collection<Location> locations, char c, char[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (locations.contains(new Location(i, j))) {
                    out.print(c);
                } else {
                    out.print(array[i][j]);
                }
            }
            out.println();
        }
    }
}
