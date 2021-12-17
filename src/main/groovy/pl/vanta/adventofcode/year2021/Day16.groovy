package pl.vanta.adventofcode.year2021

class Day16 {
    static String parse(String input) {
        input.trim()
    }

    static long solve(String input) {
        def binary = input.chars.collect {Long.toBinaryString(Long.parseLong(it as String, 16))}.join()
        print "Input=$input -> binary=$binary"


    }

    static long solve2(String input) {

    }

    static abstract class Packet {
        int version
        int typeId

    }

    static class LiteralPacker extends Packet {
        int value
    }

    static class OperatorPacket extends Packet {
        int lengthTypeId

        List<? extends Packet> subPackets = []
    }
}
