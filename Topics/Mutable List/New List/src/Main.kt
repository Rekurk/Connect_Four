fun solution(numbers: List<Int>, number: Int): MutableList<Int> {
    val numbers = numbers.toMutableList()
    numbers.add(number)
    return numbers
}