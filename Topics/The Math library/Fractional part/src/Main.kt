fun main() {
    val x = readln().split(".").toMutableList()
    if (x.size == 1) x += "0"
    println(x[1][0])
}