package pl.vanta.adventofcode.year2021

class Day12 {
    static List<Connection> parse(String input) {
        input.split('\n')
                .collect { it.split('-') }
                .collect { new Connection(from: new Cave(name: it[0]), to: new Cave(name: it[1])) }
    }

    static int solve(List<Connection> input) {
        def caves = buildCavesGraph(input)
        def start = caves.find { it.isStart() }
        def end = caves.find { it.isEnd() }

        def paths = findPaths(start, end)
        println(paths)
        paths.size()
    }

    static long solve2(int[][] input) {

        0
    }

    static Set<Cave> buildCavesGraph(List<Connection> connections) {
        def caves = [] as Set<Cave>
        connections.each { connection ->
            if (!caves.contains(connection.from)) {
                caves << connection.from
            }
            if (!caves.contains(connection.to)) {
                caves << connection.to
            }

            def from = caves.find { it.name == connection.from.name }
            def to = caves.find { it.name == connection.to.name }

            from.neighbours << to
            to.neighbours << from
        }
        caves
    }

    static Set<List<Cave>> findPaths(Cave start, Cave end) {
        def paths = [] as Set<List<Cave>>

        boolean found = false
        while (found) {
            def list = []
            found = findPath(start, end, list)
            if (found) {
                paths << list
            }
        }

        paths
    }

    static boolean findPath(Cave current, Cave end, List<Cave> path) {
        path << current

        if (path.last() == end) {
            return true
        }

        def toVisit = current.neighbours
                .findAll { !it.isSmall() || !path.contains(it) }

        if (toVisit.isEmpty()) {
            return false
        } else {
            boolean foundEnd = false
            while (!foundEnd && toVisit.iterator().hasNext()) {
                def n = toVisit.iterator().next()

                foundEnd = findPath(n, end, path)
            }
            return foundEnd
        }
    }

    static class Connection {
        Cave from
        Cave to

        @Override
        String toString() {
            "$from-$to"
        }
    }

    static class Cave {
        String name
        Set<Cave> neighbours = [] as Set<Cave>

        boolean isSmall() {
            name.toLowerCase() == name
        }

        boolean isStart() {
            name == 'start'
        }

        boolean isEnd() {
            name == 'end'
        }

        @Override
        String toString() {
//            "$name->${neighbours.name}"
            "$name"
        }

        @Override
        boolean equals(final Object obj) {
            name == ((Cave) obj).name
        }

        @Override
        int hashCode() {
            name.hashCode()
        }
    }
}
