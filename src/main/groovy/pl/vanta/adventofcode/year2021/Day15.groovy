package pl.vanta.adventofcode.year2021


import static java.lang.Integer.MAX_VALUE

class Day15 {
    static int[][] parse(String input) {
        input.split('\n')
                .collect { it.split('').collect { it as int }.toArray() }
                .toArray()
    }

    static long solve(int[][] input) {
        List<Vertex> visited = []
        Map<Tuple2, Vertex> mapping = buildMapping(input)
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(mapping.size(), { a, b -> a.distance <=> b.distance })
        queue.addAll(mapping.values())

        def start = mapping.get(new Tuple2(0, 0))
        def end = mapping.get(new Tuple2(input.length - 1, input.length - 1))

        while (!queue.isEmpty()) {

            def vertex = queue.remove()

            visited << vertex

            for (def d : getNeighbours(mapping, vertex)) {
                if (queue.contains(d)) {
                    def dist = vertex.distance + input[d.x][d.y]
                    if (d.distance > dist) {
                        d.distance = dist
                        queue.remove(d)
                        queue.add(d)
                    }
                }
            }
        }

        end.distance
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

    private static Vertex buildVertex(int i, int j) {
        if (i == 0 && j == 0) {
            new Vertex(x: i, y: j, distance: 0)
        } else {
            new Vertex(x: i, y: j)
        }
    }

    static Map<Tuple2, Vertex> buildMapping(int[][] input) {
        Map<Tuple2, Vertex> mapping = [:]

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                mapping.put(new Tuple2(i, j), buildVertex(i, j))
            }
        }

        mapping
    }

    static long solve2(int[][] input) {

    }

    static class Vertex {
        int x, y
        int distance = MAX_VALUE

        @Override
        String toString() {
            "$x,$y ($distance)"
        }
    }
}
