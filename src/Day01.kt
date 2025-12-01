import kotlin.math.abs

fun main() {
    fun String.toRotation(): Int {
        val distance = substring(1).toInt()
        return when (this[0]) {
            'L' -> -distance
            'R' -> distance
            else -> error("Should not happen")
        }
    }

    fun part1(input: List<String>): Int {
        var pos = 50
        var password = 0
        input.map { it.toRotation() }.forEach {
            pos = (pos + it) % 100
            if (pos == 0) {
                password++
            }
        }
        return password
    }

    fun part2(input: List<String>): Int {
        var pos = 50
        var password = 0
        input.map { it.toRotation() }.forEach {
            val prev = pos
            password += abs(it) / 100
            pos = (pos + it % 100)
            if (prev != 0 && pos !in 1..99) {
                password++
            }
            pos = (pos + 100) % 100
        }
        return password
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
