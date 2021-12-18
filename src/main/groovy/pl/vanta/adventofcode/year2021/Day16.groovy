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

        def packet = parsePacket(binary)

        println(packet)

        packet.getVersionsSum()
    }

    static long solve2(String input) {
        def binary = input
                .collect { toBinaryString(parseLong(it as String, 16)) }
                .collect { it.padLeft(4, '0') }
                .join()
        println "Input=$input -> binary=$binary"

        def packet = parsePacket(binary)

        println(packet)

        packet.getValue()
    }

    static Packet parsePacket(String input) {
        return LiteralPacket.isValid(input)
                ? LiteralPacket.parse(input)
                : OperatorPacket.parse(input)
    }

    static abstract class Packet {
        static final MIN_PACKET_LENGTH = 11

        int version
        int typeId
        long value

        int getVersionsSum() {
            version
        }

        abstract int getLength()
    }

    static class LiteralPacket extends Packet {
        private static final LITERAL = 4
        int length

        static boolean isValid(String input) {
            parseInt(input[3..5], 2) == LITERAL
        }

        static LiteralPacket parse(String input) {
            def version = parseInt(input[0..2], 2)
            def typeId = parseInt(input[3..5], 2)

            new LiteralPacket(version: version, typeId: typeId).parseValue(input.substring(6))
        }

        private LiteralPacket parseValue(String literalValue) {
            def lastChunkProceed = false
            def index = 0
            def bits = ''

            while (!lastChunkProceed) {
                def chunk = literalValue[index..index + 4]
                bits += chunk[1..4]
                index += 5
                lastChunkProceed = (chunk[0] == '0')
            }

            value = parseLong(bits, 2)
            length = index + 6

            this
        }
    }

    static abstract class OperatorPacket extends Packet {
        static final LENGTH_TYPE_ID_BITS = '0'
        int length = 6

        List<? extends Packet> subPackets = []

        int getVersionsSum() {
            version + subPackets.inject(0, { a, b -> a + b.getVersionsSum() })
        }

        int getLength() {
            length + subPackets.inject(0, { a, b -> a + b.getLength() })
        }

        long getValue() {
            value + subPackets.inject(0, { a, b -> a + b.getValue() })
        }

        static OperatorPacket parse(String input) {
            def version = parseInt(input[0..2], 2)
            def typeId = parseInt(input[3..5], 2)

            producePacket(typeId, version).parseValue(input.substring(6))
        }

        private static OperatorPacket producePacket(int typeId, int version) {
            switch (typeId) {
                case 0: return new SumPacket(version: version, typeId: typeId)
                case 1: return new ProductPacket(version: version, typeId: typeId)
                case 2: return new MinPacket(version: version, typeId: typeId)
                case 3: return new MaxPacket(version: version, typeId: typeId)
                case 5: return new GreaterThanPacket(version: version, typeId: typeId)
                case 6: return new LessThanPacket(version: version, typeId: typeId)
                case 7: return new EqualToPacket(version: version, typeId: typeId)
            }
        }

        OperatorPacket parseValue(String body) {
            def subPackets = body[0] == LENGTH_TYPE_ID_BITS
                    ? parseSubPacketsByBits(body.substring(16), parseInt(body[1..15], 2))
                    : parseSubPacketsByNumber(body.substring(12), parseInt(body[1..11], 2))

            this.subPackets = subPackets

            this
        }

        List<Packet> parseSubPacketsByBits(String input, int numberOfBits) {
            this.length += 16

            def body = input
            def bitsLeft = numberOfBits
            def packets = []

            while (bitsLeft >= MIN_PACKET_LENGTH) {
                def packet = LiteralPacket.isValid(body)
                        ? LiteralPacket.parse(body)
                        : parse(body)

                packets << packet
                body = body.substring(packet.length)
                bitsLeft -= packet.length
            }

            packets
        }

        List<Packet> parseSubPacketsByNumber(String input, int numberOfPackets) {
            this.length += 12

            def body = input
            def packetsLeft = numberOfPackets
            def packets = []

            while (packetsLeft > 0) {
                def packet = LiteralPacket.isValid(body)
                        ? LiteralPacket.parse(body)
                        : parse(body)

                packets << packet
                body = body.substring(packet.length)
                packetsLeft--
            }

            packets
        }
    }

    static class SumPacket extends OperatorPacket {
        @Override
        long getValue() {
            subPackets.inject(0L, { a, b -> a + b.value })
        }
    }

    static class ProductPacket extends OperatorPacket {
        @Override
        long getValue() {
            subPackets.inject(1L, { a, b -> a * b.value })
        }
    }

    static class MinPacket extends OperatorPacket {
        @Override
        long getValue() {
            subPackets.collect { it.getValue() }.min()
        }
    }

    static class MaxPacket extends OperatorPacket {
        @Override
        long getValue() {
            subPackets.collect { it.getValue() }.max()
        }
    }

    static class EqualToPacket extends OperatorPacket {
        @Override
        long getValue() {
            subPackets[0].getValue() == subPackets[1].getValue() ? 1L : 0L
        }
    }

    static class GreaterThanPacket extends OperatorPacket {
        @Override
        long getValue() {
            subPackets[0].getValue() > subPackets[1].getValue() ? 1L : 0L
        }
    }

    static class LessThanPacket extends OperatorPacket {
        @Override
        long getValue() {
            subPackets[0].getValue() < subPackets[1].getValue() ? 1L : 0L
        }
    }
}
