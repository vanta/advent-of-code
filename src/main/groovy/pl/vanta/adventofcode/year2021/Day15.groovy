package pl.vanta.adventofcode.year2021


import static java.lang.Integer.MAX_VALUE

class Day15 {
    static int[][] parse(String input) {
        input.split('\n')
                .collect { it.split('').collect { it as int }.toArray() }
                .toArray()
    }

    static long solve(int[][] input) {
        Map<Tuple2, Vertex> mapping = buildMapping(input)
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(mapping.size(), { a, b -> a.distance <=> b.distance })
        queue.addAll(mapping.values())

        calculateDistances(queue, mapping)

        def end = mapping.get(new Tuple2(input.length - 1, input.length - 1))
        end.distance
    }

    private static void calculateDistances(PriorityQueue<Vertex> queue, Map<Tuple2, Vertex> mapping) {
        while (!queue.isEmpty()) {
            def vertex = queue.remove()

            for (def neighbour : getNeighbours(mapping, vertex)) {
                if (queue.contains(neighbour)) {
                    def dist = vertex.distance + neighbour.value
                    if (neighbour.distance > dist) {
                        neighbour.distance = dist
                        queue.remove(neighbour)
                        queue.add(neighbour)
                    }
                }
            }
        }
    }

    static List<Vertex> getNeighbours(Map<Tuple2, Vertex> mapping, Vertex vertex) {
        [
                mapping.get(new Tuple2(vertex.x - 1, vertex.y)),
                mapping.get(new Tuple2(vertex.x + 1, vertex.y)),
                mapping.get(new Tuple2(vertex.x, vertex.y - 1)),
                mapping.get(new Tuple2(vertex.x, vertex.y + 1)),
        ].findAll {
            it != null
        }
    }

    static Map<Tuple2, Vertex> buildMapping(int[][] input) {
        Map<Tuple2, Vertex> mapping = [:]

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                mapping.put(new Tuple2(i, j), new Vertex(x: i, y: j, value: input[i][j]))
            }
        }

        mapping.get(new Tuple2(0, 0)).distance = 0

        mapping
    }

    static long solve2(int[][] input) {
        int[][] newInput = buildNewInput(input)

        solve(newInput)
    }

    static int[][] buildNewInput(int[][] input) {
        def size = input.length
        def result = new int[size * 5][size * 5]

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = input[i][j]

                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        result[i + size * x][j + size * y] = aaa(input[i][j], x, y)
                    }
                }
            }
        }

        result
    }

    private static int aaa(int value, int x, int y) {
        def newValue = value + x + y
        newValue < 10 ? newValue : (newValue - 9)
    }

    static class Vertex {
        int x, y
        int value
        int distance = MAX_VALUE

        @Override
        String toString() {
            "$x,$y ($distance)"
        }
    }
}
