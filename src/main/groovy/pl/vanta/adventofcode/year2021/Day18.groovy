package pl.vanta.adventofcode.year2021

import groovy.json.JsonSlurper

class Day18 {
    private static int LEFT = 0
    private static int RIGHT = 1

    static List<String> parse(String input) {
        input.split('\n')
    }

    static long solve(List<String> input) {
        Node result = addAndReduceAll(input)

        result.getMagnitude()
    }

    static Node addAndReduceAll(List<String> input) {
        input
                .collect { parseNumber(it) }
                .inject { a, b -> addAndReduce(a, b) }
    }

    static long solve2(List<String> input) {
        println("input: ${input.size()}")

        def pairs = generatePairs(input)

        println("Pairs: ${pairs.size()}")

        def result = pairs
                .collect { [parseNumber(it[0]), parseNumber(it[1])] }
                .collect { addAndReduce(it[0], it[1]) }
                .collectEntries() { Node it -> [it.toString(), it.getMagnitude()] }
                .sort { it.value }

        result.values().last()
    }

    static Node parseNumber(String input) {
        def list = new JsonSlurper().parseText(input) as List

        Node.fromList(list)
    }

    static Node addAndReduce(Node n1, Node n2) {
        def result = Node.add(n1, n2)

        def reduced = result.reduce()

        result
    }

    static boolean explode(List list) {
        explode(list, 0, null) != null
    }

    static List explode(List list, int level, List parent) {
        if (level == 4) {
            return list
        }

        def l = list[LEFT]
        def r = list[RIGHT]

        if (l instanceof List) {
            def exploded = explode(l as List, level + 1, list)

            if (exploded) {
                list[LEFT] = 0

                increase(list, exploded[LEFT], LEFT)
                increase(list, exploded[RIGHT], RIGHT)
            }

            return exploded
        }

        if (r instanceof List) {
            def exploded = explode(r as List, level + 1, parent)

            if (exploded) {
                list[RIGHT] = 0

                increase(list, exploded[LEFT], LEFT)
                increase(list, exploded[RIGHT], RIGHT)
            }

            return exploded
        }

        return null
    }

    static List splitNumber(int number) {
        def a = (number / 2) as int
        def b = number - a

        [b as int, a as int]
    }

    static List<List<String>> generatePairs(List<String> strings) {
        def result = []
        for (String s1 : strings) {
            for (String s2 : strings) {
                if(s1 != s2) {
                    result << [s1, s2]
                    result << [s2, s1]
                }
            }
        }
        result
    }

    static class Node {
        protected Node parent
        Node left
        Node right

        Node(left, right) {
            this.left = left
            this.right = right
        }

        Node(left, right, parent) {
            this.left = left
            this.right = right
            this.parent = parent
        }

        void attachParentsToChildren() {
            this.left.parent = this
            this.right.parent = this
        }

        static Node fromList(List list) {
            def left = list[LEFT] instanceof Integer ? new NodeValue(list[LEFT] as int) : fromList(list[LEFT])
            def right = list[RIGHT] instanceof Integer ? new NodeValue(list[RIGHT] as int) : fromList(list[RIGHT])

            def node = new Node(left, right)
            node.attachParentsToChildren()
            node
        }

        static add(Node n1, Node n2) {
            def node = new Node(n1, n2)

            n1.parent = node
            n2.parent = node

            node
        }

        boolean reduce() {
            def reduced = true
            while (reduced) {
                reduced = explode() || split()
            }
            reduced
        }

        int getMagnitude() {
            3 * left.getMagnitude() + 2 * right.getMagnitude()
        }

        boolean explode(int level = 0) {
            if (level < 3) {
                return left.explode(level + 1) || right.explode(level + 1)
            }

            checkExplode()
        }

        boolean checkExplode() {
            return checkExplodeLeft() || checkExplodeRight()
        }

        private boolean checkExplodeLeft() {
            if (left.canExplode()) {
                addExplodedToLeft(left.left.value, left)
                addExplodedToRight(left.right.value, left)
                left = new NodeValue(0, this)
                return true
            } else {
                return left.checkExplode()
            }
        }

        private boolean checkExplodeRight() {
            if (right.canExplode()) {
                addExplodedToLeft(right.left.value, right)
                addExplodedToRight(right.right.value, right)
                right = new NodeValue(0, this)

                return true
            } else {
                return right.checkExplode()
            }
        }

        def addExplodedToLeft(int value, Node exploded) {
            def node = exploded.findLeftNeighbour()
            if (node) {
                node.addRight(value)
            }
        }

        def addExplodedToRight(int value, Node exploded) {
            def node = exploded.findRightNeighbour()
            if (node) {
                node.addLeft(value)
            }
        }

        def addLeft(int value) {
            if (this instanceof NodeValue) {
                this.add(value)
            } else {
                left.addLeft(value)
            }
        }

        def addRight(int value) {
            if (this instanceof NodeValue) {
                this.add(value)
            } else {
                right.addRight(value)
            }
        }

        Node findLeftNeighbour() {
            if (parent && parent.left != this) {
                return parent.left
            }

            return parent?.findLeftNeighbour()
        }

        Node findRightNeighbour() {
            if (parent && parent.right != this) {
                return parent.right
            }

            return parent?.findRightNeighbour()
        }

        boolean canExplode() {
            left instanceof NodeValue && right instanceof NodeValue
        }

        boolean split() {
            splitLeft() || splitRight()
        }

        boolean splitLeft() {
            if (left instanceof NodeValue) {
                def values = left.getSplitValues()
                if (!values.isEmpty()) {
                    left = new Node(new NodeValue(values[LEFT]), new NodeValue(values[RIGHT]), this)
                }

                return !values.isEmpty()
            }

            return left.split()
        }

        boolean splitRight() {
            if (right instanceof NodeValue) {
                def values = right.getSplitValues()
                if (!values.isEmpty()) {
                    right = new Node(new NodeValue(values[LEFT]), new NodeValue(values[RIGHT]), this)
                }

                return !values.isEmpty()
            }

            return right.split()
        }

        def toList() {
            [left.toList(), right.toList()]
        }

        @Override
        String toString() {
            "[${left.toString()},${right.toString()}]"
        }
    }

    static class NodeValue extends Node {
        int value

        NodeValue(int value) {
            super(null, null)

            this.value = value
        }

        NodeValue(int value, Node parent) {
            super(null, null, parent)

            this.value = value
        }

        @Override
        String toString() {
            String.valueOf(value)
        }

        List getSplitValues() {
            if (value >= 10) {
                def a = (value / 2) as int
                def b = value - a

                return [a, b]
            }
            return []
        }

        @Override
        boolean split() {
            false
        }

        boolean checkExplode() {
            false
        }

        boolean explode(int i) {
            false
        }

        @Override
        def toList() {
            value
        }

        boolean add(int value) {
            this.value += value
            true
        }

        @Override
        int getMagnitude() {
            value
        }
    }
}
