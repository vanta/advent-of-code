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
        findPath(start, end, new Path(), paths)
        paths.each {
            println it
        }

        paths.size()
    }

    static long solve2(List<Connection> input) {
        def caves = buildCavesGraph(input)
        def start = caves.find { (it.name == 'start') }
        def end = caves.find { (it.name == 'end') }

        def paths = []
        findPath(start, end, new Path2(), paths)
        paths.each {
            println it
        }
        paths.size()
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

    static void findPath(Cave current, Cave end, Path parentPath, def alreadyFound) {
        def currentPath = parentPath.add(current)

        if (current == end) {
            alreadyFound << currentPath
            return
        }

        current.neighbours
                .findAll { currentPath.canVisit(it) }
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

        boolean isStart() {
            name == 'start'
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

    static class Path {
        List<Cave> path = []

        boolean canVisit(Cave c) {
            !c.isSmall() || !path.contains(c)
        }

        Path add(Cave c) {
            new Path(path: path + [c])
        }

        @Override
        String toString() {
            path.toString()
        }
    }

    static class Path2 extends Path {
        final boolean smallCaveVisitedTwice

        Path2(List<Cave> path = []) {
            this.path = path
            smallCaveVisitedTwice = !this.path
                    .findAll { it.isSmall() }
                    .groupBy { it.name }
                    .findAll { it.value.size() > 1 }
                    .isEmpty()
        }

        Path2 add(Cave c) {
            new Path2(path + [c])
        }

        @Override
        boolean canVisit(Cave c) {
            if (c.isStart()) {
                return false
            }

            if (!c.isSmall()) {
                return true
            }

            if (!path.contains(c)) {
                return true
            }

            return !smallCaveVisitedTwice
        }
    }
}
