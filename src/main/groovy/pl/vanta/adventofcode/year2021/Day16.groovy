package pl.vanta.adventofcode.year2021


import static java.lang.Integer.parseInt
import static java.lang.Long.parseLong
import static java.lang.Long.toBinaryString

class Day16 {
    static String parse(String input) {
        input.trim()
    }

    static long solve(String input) {
        def binary = input.chars.collect { toBinaryString(parseLong(it as String, 16)) }.join()
        print "Input=$input -> binary=$binary"

        def packet = parsePacket(binary)

        packet.getAllVersions()
    }

    static long solve2(String input) {

    }

    static Packet parsePacket(String input) {
        def version = parseInt(input[0..2], 2)
        def typeId = parseInt(input[3..5], 2)

        if (typeId == Packet.LITERAL) {
            parseLiteralPacket(version, typeId, input)
        } else {
            parseOperatorPacket(version, typeId, input)
        }
    }

    static Packet parseLiteralPacket(int version, int typeId, String s) {
        new LiteralPacket(version: version, typeId: typeId)
    }

    static Packet parseOperatorPacket(int version, int typeId, String s) {
        new OperatorPacket(version: version, typeId: typeId)
    }

    static abstract class Packet {
        static final LITERAL = 4

        int version
        int typeId

        int getAllVersions() {
            version
        }
    }

    static class LiteralPacket extends Packet {
        int value
    }

    static class OperatorPacket extends Packet {
        List<? extends Packet> subPackets = []

        int getAllVersions() {
            version + subPackets.inject(0, {a, b -> a + b.version})
        }
    }

}
