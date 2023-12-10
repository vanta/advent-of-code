package pl.vanta.adventofcode;

import static java.lang.System.out;

public class Utils {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";

    public static String red(String s) {
        return WHITE_BACKGROUND_BRIGHT + RED + s + RESET;
    }
    public static String red(char c) {
        return WHITE_BACKGROUND_BRIGHT + RED + c + RESET;
    }

    public static void printRed(String s) {
        out.print(red(s));
    }
}
