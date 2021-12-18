package pl.vanta.adventofcode.year2021


import static java.lang.Integer.parseInt
import static java.lang.Long.parseLong
import static java.lang.Long.toBinaryString

class Day16 {
    static String parse(String input) {
        input.trim()
    }

    static long solve(String input) {
        def binary = input
                .collect { toBinaryString(parseLong(it as String, 16)) }
                .collect { it.padLeft(4, '0') }
                .join()
        println "Input=$input -> binary=$binary"

        def packets = parsePackets(binary)

        println(packets)

        packets.sum { it.getAllVersions() }
    }

    static long solve2(String input) {

    }

    static Packet parsePackets(String input) {
        def version = parseInt(input[0..2], 2)
        def typeId = parseInt(input[3..5], 2)

        if (typeId == Packet.LITERAL) {
            parseLiteralPacket(version, typeId, input.substring(6))
        } else {
            parseOperatorPacket(version, typeId, input.substring(6))
        }
    }

    static Packet parseLiteralPacket(int version, int typeId, String body) {
        new LiteralPacket(version: version, typeId: typeId, value: parseLiteralPacketValue(body))
    }

    static Packet parseOperatorPacket(int version, int typeId, String body) {
        List<Packet> subPackets

        if (body[0] == '0') {
            int subPacketsBitsLength = parseInt(body[1..15], 2)
            subPackets = parseSubPacketsByBits(body.substring(16), subPacketsBitsLength)
        } else {
            int subPacketsCount = parseInt(body[1..11], 2)
            subPackets = parseSubPacketsByNumber(body.substring(16), subPacketsCount)
        }

        new OperatorPacket(version: version, typeId: typeId, subPackets: subPackets)
    }

    static List<Packet> parseSubPacketsByNumber(String input, int numberOfPackets) {


    }

    static List<Packet> parseSubPacketsByBits(String input, int numberOfBits) {
        def version = parseInt(input[0..2], 2)
        def typeId = parseInt(input[3..5], 2)

        if (typeId == Packet.LITERAL) {
            parseLiteralPacket(version, typeId, input.substring(6))
        } else {
            parseOperatorPacket(version, typeId, input.substring(6))
        }
    }

    static int parseLiteralPacketValue(String s) {
        -1
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
            version + subPackets.inject(0, { a, b -> a + b.version })
        }
    }

}
