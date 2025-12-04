fun main() {
    fun List<String>.toGrid() = map { it.toList() }
    fun List<List<Char>>.isAccessibleRoll(point: Point) = this[point] == '@' && point.alLNeighbors().count { this[it] == '@' } < 4
    fun List<List<Char>>.countRolls() = sumOf {
        it.countValue('@')
    }

    fun part1(input: List<String>): Int {
        val grid = input.toGrid()
        return grid.points().count { grid.isAccessibleRoll(it) }
    }

    fun part2(input: List<String>): Int {
        val initial = input.toGrid()
        var previous: List<List<Char>>? = null
        var current = initial
        while (previous != current) {
            previous = current
            current = current.mapIndexed { rowIndex, row ->
                row.mapIndexed { colIndex, char ->
                    val p = Point(rowIndex, colIndex)
                    if (current.isAccessibleRoll(p)) {
                        '.'
                    } else {
                        char
                    }
                }
            }
        }
        return initial.countRolls() - current.countRolls()
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
