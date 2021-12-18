package pl.vanta.adventofcode.year2021

import static java.lang.Integer.parseInt
import static java.lang.Long.parseLong
import static java.lang.Long.toBinaryString
import static pl.vanta.adventofcode.year2021.Day16.OperatorPacket.LENGTH_TYPE_ID_BITS
import static pl.vanta.adventofcode.year2021.Day16.Packet.LITERAL

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

        def packet = parsePackets(binary)

        println(packet)

        packet.getAllVersions()
    }

    static long solve2(String input) {

    }

    static Packet parsePackets(String input) {
        def version = parseInt(input[0..2], 2)
        def typeId = parseInt(input[3..5], 2)

        if (typeId == LITERAL) {
            parseLiteralPacket(version, typeId, input.substring(6))
        } else {
            parseOperatorPacket(version, typeId, input.substring(6))
        }
    }

    static LiteralPacket parseLiteralPacket(int version, int typeId, String body) {
        new LiteralPacket(version: version, typeId: typeId, value: parseLiteralPacketValue(body))
    }

    static OperatorPacket parseOperatorPacket(int version, int typeId, String body) {
        def subPackets = body[0] == LENGTH_TYPE_ID_BITS
                ? parseSubPacketsByBits(body.substring(16), parseInt(body[1..15], 2))
                : parseSubPacketsByNumber(body.substring(16), parseInt(body[1..11], 2))

        new OperatorPacket(version: version, typeId: typeId, subPackets: subPackets)
    }

    static List<Packet> parseSubPacketsByBits(String input, int numberOfBits) {
        def version = parseInt(input[0..2], 2)
        def typeId = parseInt(input[3..5], 2)

        if (typeId == LITERAL) {
            parseLiteralPacket(version, typeId, input.substring(6))
        } else {
            parseOperatorPacket(version, typeId, input.substring(6))
        }
    }

    static List<Packet> parseSubPacketsByNumber(String input, int numberOfPackets) {


    }

    static int parseLiteralPacketValue(String literalValue) {
        def lastChunkProceed = false
        def index = 0
        def bits = ''

        while (!lastChunkProceed) {
            def chunk = literalValue[index..index + 4]
            bits += chunk[1..4]

            index += 5
            lastChunkProceed = (chunk[0] == '0')
        }

        parseInt(bits, 2)
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
        static final LENGTH_TYPE_ID_BITS = '0'
        static final LENGTH_TYPE_ID_COUNT = '1'

        List<? extends Packet> subPackets = []

        int getAllVersions() {
            version + subPackets.inject(0, { a, b -> a + b.version })
        }
    }

}
