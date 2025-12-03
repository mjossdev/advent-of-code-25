fun main() {
    tailrec fun List<Int>.maxJoltage(remaining: Int, sum: Long = 0L): Long {
        if (remaining == 0) return sum
        val digit = dropLast(remaining - 1).max()
        val index = indexOf(digit)
        return drop(index + 1).maxJoltage(remaining - 1, sum * 10L + digit)
    }

    fun solve(input: List<String>, digits: Int): Long {
        val batteries = input.map {
            it.map { c -> c.digitToInt() }
        }
        return batteries.sumOf {
            it.maxJoltage(digits)
        }
    }

    fun part1(input: List<String>): Long = solve(input, 2)

    fun part2(input: List<String>): Long = solve(input, 12)

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357L)
    check(part2(testInput) == 3121910778619L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
