package pl.vanta.adventofcode.year2021

import groovy.json.JsonSlurper

class Day18 {
    private static int LEFT = 0
    private static int RIGHT = 1

    static List parse(String input) {
        input.split('\n').collect { parseNumber(it) }
    }

    static long solve(List input) {
        def result = add(input)
        magnitude(result)
    }

    static long solve2(List input) {
        -1
    }

    static String add(List input) {
        def result = input.inject { a, b -> addAndReduce(a as List, b as List) }

        result.toString().replace(' ', '')
    }

    static long magnitude(String input) {
        def number = parseNumber(input)

        getMagnitude(number)
    }

    static List parseNumber(String input) {
        new JsonSlurper().parseText(input) as List
    }

    static List addAndReduce(List l1, List l2) {
        reduce([l1, l2])
    }

    static List reduce(List l) {
//        def canBeReduced = true
//
//        while (canBeReduced) {
//            canBeReduced = explode(l) || split(l)
//        }

        l
    }

    static boolean explode(List list) {
        def l = list[LEFT]
        def r = list[RIGHT]

        if (l instanceof Integer && r instanceof Integer) {
            //can explode

        }


        return explode(l as List) || explode(r as List)
    }

    static boolean split(List list) {
        def l = list[LEFT]
        def r = list[RIGHT]

        if (l instanceof Integer && l >= 10) {
            list[LEFT] = splitNumber(l)
            return true
        }

        if (l instanceof List && split(l as List)) {
            return true
        }

        if (r instanceof Integer && r >= 10) {
            list[RIGHT] = splitNumber(r)
            return true
        }

        if (r instanceof List && split(r as List)) {
            return true
        }

        return false
    }

    static int getMagnitude(List list) {
        def l = list[LEFT]
        def r = list[RIGHT]
        def left = l instanceof List ? getMagnitude(l) : l as int
        def right = r instanceof List ? getMagnitude(r) : r as int

        3 * left + 2 * right
    }

    static List splitNumber(int number) {
        def a = (number / 2) as int
        def b = number - a

        [b as int, a as int]
    }
}
