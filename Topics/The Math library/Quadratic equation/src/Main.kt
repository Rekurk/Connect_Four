import kotlin.math.pow
import kotlin.math.sqrt
const val FOUR = 4
fun main() {
    val a = readln().toDouble()
    val b = readln().toDouble()
    val c = readln().toDouble()
    val d = b.pow(2) - FOUR * a * c
    if ((-b - sqrt(d)) / (2.0 * a) > (-b + sqrt(d)) / (2.0 * a)) {
        println("${ (-b + sqrt(d)) / (2.0 * a) } ${ (-b - sqrt(d)) / (2.0 * a) }")
    } else println("${ (-b - sqrt(d)) / (2.0 * a) } ${ (-b + sqrt(d)) / (2.0 * a) }")
}