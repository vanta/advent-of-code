package pl.vanta.adventofcode.year2021

class Day8 {

    static List<Line> parse(String input) {
        input.split('\n')
                .collect {
                    it.split('\\|')
                }
                .collect {
                    new Line(inputs: it[0].split(' ') as List, outputs: it[1].split(' ') as List)
                }
    }

    static int solve(List<Line> input) {
        println "Input data: $input"
        input.sum {
            it.outputs.count {
                it.length() == 2 || it.length() == 3 || it.length() == 4 || it.length() == 7
            }
        }
    }

    static long solve2(List<Line> input) {
        0
    }

    static class Line {
        List<String> inputs = []
        List<String> outputs = []

        @Override
        String toString() {
            "Line: Inputs=$inputs, outputs=$inputs"
        }
    }

}
