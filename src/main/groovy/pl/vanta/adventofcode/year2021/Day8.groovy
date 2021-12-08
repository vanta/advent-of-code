package pl.vanta.adventofcode.year2021

class Day8 {

    static List<Line> parse(String input) {
        input.split('\n')
                .collect {
                    it.split('\\|')
                }
                .collect {
                    new Line(inputs: it[0].trim().split(' ') as List, outputs: it[1].trim().split(' ') as List)
                }
    }

    static int solve(List<Line> input) {
        input.sum {
            it.outputs.count {
                it.length() == 2 || it.length() == 3 || it.length() == 4 || it.length() == 7
            }
        }
    }

    static long solve2(List<Line> input) {
        input.sum {
            def val = it.getValue()
            println "$it value is=$val"
            val
        }
    }

    static class Line {
        List<String> inputs = []
        List<String> outputs = []
        Map<Set<Character>, String> mapping = [:]

        @Override
        String toString() {
            "Line: in=$inputs, out=$outputs"
        }

        int getValue() {
            decodeInput()

            outputs
                    .collect { getStringValue(it) }
                    .join() as int
        }

        String getStringValue(String number) {
            def chars = number.chars as Set
            mapping.get(chars)
        }

        void decodeInput() {
            inputs.sort { it.length() }
            def inputsChars = inputs.collect { it.chars as Set<Character> }

            def x1 = inputsChars[0]
            def x7 = inputsChars[1]
            def x4 = inputsChars[2]
            def x8 = inputsChars[9]
            def l5 = [inputsChars[3], inputsChars[4], inputsChars[5]] as Set
            def l6 = [inputsChars[6], inputsChars[7], inputsChars[8]] as Set

            mapping.put(x1, '1')
            mapping.put(x7, '7')
            mapping.put(x4, '4')
            mapping.put(x8, '8')

            def x3 = l5.find { it.containsAll(x7) }
            l5.remove(x3)
            mapping.put(x3, '3')

            def x6 = l6.find { !it.containsAll(x3) }
            l6.remove(x6)
            mapping.put(x6, '6')

            def x9 = l6.find { it.containsAll(x3) }
            l6.remove(x9)
            mapping.put(x9, '9')
            mapping.put(l6[0], '0')

            def diff = x4 - x1
            def x5 = l5.find { it.containsAll(diff) }
            l5.remove(x5)
            mapping.put(x5, '5')
            mapping.put(l5[0], '6')
        }
    }

}
