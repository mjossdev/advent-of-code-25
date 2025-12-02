fun main() {
    fun String.toRanges(): List<LongRange> = split(',').map { pair ->
        val (start, end) = pair.split('-').map { it.toLong() }
        start..end
    }

    fun part1(input: List<String>): Long {
        val ranges = input.single().toRanges()
        return ranges.sumOf { r ->
            r.filter {
                val s = it.toString()
                val mid = s.length / 2
                s.length % 2 == 0 && s.regionMatches(0, s, mid, mid)
            }.sum()
        }
    }

    fun part2(input: List<String>): Long {
        val ranges = input.single().toRanges()
        return ranges.sumOf { r ->
            r.filter { n ->
                val s = n.toString()
                (1..(s.length / 2)).any {
                    s.length % it == 0 && s == s.take(it).repeat(s.length / it)
                }
            }.sum()
        }
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
