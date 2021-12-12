package pl.vanta.adventofcode.year2021

class Day12 {
    static List<Connection> parse(String input) {
        input.split('\n')
                .collect { it.split('-') }
                .collect { new Connection(from: new Cave(name: it[0]), to: new Cave(name: it[1])) }
    }

    static int solve(List<Connection> input) {
        def caves = buildCavesGraph(input)
        def start = caves.find { (it.name == 'start') }
        def end = caves.find { (it.name == 'end') }

        def paths = []
        findPath(start, end, [] as Stack, paths)
        println(paths)
        paths.size()
    }

    static long solve2(List<Connection> input) {

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

    static void findPath(Cave current, Cave end, List<Cave> parentPath, def alreadyFound) {
        def currentPath = parentPath + [current]

        if (current == end) {
            alreadyFound << currentPath
            return
        }

        current.neighbours
                .findAll { !it.isSmall() || !currentPath.contains(it) }
                .each {
                    findPath(it, end, currentPath, alreadyFound)
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

        @Override
        String toString() {
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
