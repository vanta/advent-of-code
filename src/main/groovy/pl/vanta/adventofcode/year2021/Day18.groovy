package pl.vanta.adventofcode.year2021

import groovy.json.JsonSlurper

class Day18 {
    private static int LEFT = 0
    private static int RIGHT = 1

    static List parse(String input) {
        input.split('\n').collect { parseNumber(it) }
    }

    static long solve(List input) {
        def result = add(input)

        result.getMagnitude()
    }

    static long solve2(List input) {
        -1
    }

    static Node add(List<Node> input) {
        input.inject { a, b -> addAndReduce(a, b) }
    }

    static Node parseNumber(String input) {
        def list = new JsonSlurper().parseText(input) as List

        Node.fromList(list)
    }

    static Node addAndReduce(Node n1, Node n2) {
        def result = Node.add(n1, n2)

        result.reduce()

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
            new Node(n1, n2)
        }

        def reduce() {

        }

        int getMagnitude() {
            3 * left.getMagnitude() + 2 * right.getMagnitude()
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

        @Override
        def toList() {
            value
        }

        @Override
        int getMagnitude() {
            value
        }
    }
}
