package pl.vanta.adventofcode.year2021

import static java.lang.Integer.parseInt
import static java.lang.Long.parseLong
import static java.lang.Long.toBinaryString
import static pl.vanta.adventofcode.year2021.Day16.LiteralPacket.LITERAL
import static pl.vanta.adventofcode.year2021.Day16.OperatorPacket.LENGTH_TYPE_ID_BITS

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

        def packet = parsePacket(binary)

        println(packet)

        packet.getAllVersions()
    }

    static long solve2(String input) {

    }

    static Packet parsePacket(String input) {
        return LiteralPacket.isValid(input)
                ? LiteralPacket.parse(input)
                : parseOperatorPacket(input)
    }

    static OperatorPacket parseOperatorPacket(String input) {
        def version = parseInt(input[0..2], 2)
        def typeId = parseInt(input[3..5], 2)
        def body = input[6]

        def subPackets = body[0] == LENGTH_TYPE_ID_BITS
                ? parseSubPacketsByBits(body.substring(16), parseInt(body[1..15], 2))
                : parseSubPacketsByNumber(body.substring(16), parseInt(body[1..11], 2))

        new OperatorPacket(version: version, typeId: typeId, subPackets: subPackets)
    }

    static List<Packet> parseSubPacketsByBits(String input, int numberOfBits) {
        def version = parseInt(input[0..2], 2)
        def typeId = parseInt(input[3..5], 2)

        if (typeId == LITERAL) {
            LiteralPacket.parse(input.substring(6))
        } else {
            parseOperatorPacket(version, typeId, input.substring(6))
        }
    }

    static List<Packet> parseSubPacketsByNumber(String input, int numberOfPackets) {


    }

    static abstract class Packet {
        int version
        int typeId

        int getAllVersions() {
            version
        }
    }

    static class LiteralPacket extends Packet {
        private static final LITERAL = 4
        int value
        int length

        static boolean isValid(String input) {
            parseInt(input[3..5], 2) == LITERAL
        }

        static LiteralPacket parse(String input) {
            def version = parseInt(input[0..2], 2)
            def typeId = parseInt(input[3..5], 2)

            def result = new LiteralPacket(version: version, typeId: typeId)

            result.parseLiteralPacketValue(input.substring(6))
        }

        LiteralPacket parseLiteralPacketValue(String literalValue) {
            def lastChunkProceed = false
            def index = 0
            def bits = ''

            while (!lastChunkProceed) {
                def chunk = literalValue[index..index + 4]
                bits += chunk[1..4]
                index += 5
                lastChunkProceed = (chunk[0] == '0')
            }

            value = parseInt(bits, 2)
            length = index + 6

            this
        }
    }

    static class OperatorPacket extends Packet {
        static final LENGTH_TYPE_ID_BITS = '0'

        List<? extends Packet> subPackets = []

        int getAllVersions() {
            version + subPackets.inject(0, { a, b -> a + b.getAllVersions() })
        }
    }

}
