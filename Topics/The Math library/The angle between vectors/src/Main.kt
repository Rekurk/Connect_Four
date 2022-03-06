import kotlin.math.acos
import kotlin.math.floor
import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.PI
const val RAD = 180
fun main() {
    val u = readln().split(" ").map { it.toDouble() }
    val v = readln().split(" ").map { it.toDouble() }
    val cosUV = (u[0] * v[0] + u[1] * v[1]) / (sqrt(u[0].pow(2) + u[1].pow(2)) * sqrt(v[0].pow(2) + v[1].pow(2)))
    println(floor(acos(cosUV) * RAD / PI).toInt())
}