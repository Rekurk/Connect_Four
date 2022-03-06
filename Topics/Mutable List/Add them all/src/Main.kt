fun solution(first: List<Int>, second: List<Int>): MutableList<Int> {
    val first = first.toMutableList()
    first.addAll(second)
    return first
}