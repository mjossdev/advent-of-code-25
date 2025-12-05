import java.util.IdentityHashMap
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun String.toRange() = split('-')
        .map { it.toLong() }
        .let { (start, end) -> start..end }

    fun List<String>.toFreshRanges() = takeWhile { it.isNotBlank() }.map { it.toRange() }

    fun part1(input: List<String>): Int {
        val freshRanges = input.toFreshRanges()
        val availableIds = input.takeLastWhile { it.isNotBlank() }.map { it.toLong() }
        return availableIds.count { id ->
            freshRanges.any { id in it }
        }
    }

    fun part2(input: List<String>): Long {
        val ranges = ArrayDeque(input.toFreshRanges())
        val finalRanges = buildList {
            while (ranges.isNotEmpty()) {
                val range = ranges.removeFirst()
                var hadOverlaps = false
                var count = 0
                while (count < ranges.size) {
                    val other = ranges.removeFirst()
                    if (range.first in other || range.last in other || other.first in range || other.last in range) {
                        hadOverlaps = true
                        val merged = min(range.first, other.first)..max(range.last, other.last)
                        ranges.addLast(merged)
                    } else {
                        ranges.addLast(other)
                    }
                    ++count
                }
                if (!hadOverlaps) {
                    add(range)
                }
            }
        }
        return finalRanges.sumOf { it.last - it.first + 1 }
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 14L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
