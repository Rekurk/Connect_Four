package connectfour
import kotlin.NumberFormatException

const val FWN = "oooo"
const val SWN = "****"
//ok it soooooooooo bad, but I'm so lazy to rebuild it
class Game {
    var firstPlayer: String
    var secondPlayer: String
    private var end = 1
    var chest : MutableList<Int>
    private var firstWin = 0
    private var secondWin = 0
    var numbergame = 1
    var nowGame = 1
    val z: MutableList<MutableList<String>>
    init {
            println("Connect Four\n" +
                    "First player's name:")
            firstPlayer = readln()
            println("Second player's name:")
            secondPlayer = readln()
            while (true) {
            println("Set the board dimensions (Rows x Columns)\n" +
                    "Press Enter for default (6 x 7)")
            val boardDimension = readln().replace("\\s".toRegex(), "")
            if (boardDimension == ""){
                z = MutableList(6) { MutableList(7) { " " }}
                break
            }
            val d = boardDimension.split("")
            when {
                !boardDimension.matches("[1-9][0-9]?[xX][1-9][0-9]?".toRegex()) -> println("Invalid input")
                d[1].toInt() !in 5..9 -> println("Board rows should be from 5 to 9")
                d[d.lastIndex - 1].toInt() !in 5..9 -> println("Board columns should be from 5 to 9")
                else -> {
                    z = MutableList(d[1].toInt()) { MutableList(d[d.lastIndex - 1].toInt()) { " " }}
                    break
                }
            }
        }
        chest = MutableList(z[0].size) { z.lastIndex }
        while (true) {
        println("Do you want to play single or multiple games?\n" +
                "For a single game, input 1 or press Enter\n" +
                "Input a number of games:")
            try {
                val buf = readln()
                when {
                    buf == "" -> break
                    buf.toInt() <= 0 -> println("Invalid input")
                    else -> {
                        numbergame = buf.toInt()
                        break
                    }
                }
            }
            catch (e: NumberFormatException) {
                println("Invalid input")
            }
        }
    }
    fun output () {
        println("$firstPlayer VS $secondPlayer")
        println("${ z.size } X ${ z[0].size } board")
        when(numbergame) {
            1 -> println("Single game")
            else -> println("Total $numbergame games")
        }
    }
    fun gameThis () {
        println("Score")
        println("$firstPlayer: $firstWin $secondPlayer: $secondWin")
    }
    fun printBoard() {
        for (i in 1..z[0].size) print(" $i")
        println()
        for (j in 0 until z.size) {
            for (i in 0 until z[0].size) print("║${ z[j][i] }")
            println("║")
        }
        print("╚")
        for (i in 1 until z[0].size) print("═╩")
        print("═╝")
        println()
    }
    fun playing(name: String): Boolean {
        println("$name's turn:")
        while (true) {
            val column1 = readln()
            if (column1 == "end") {
                end = 0
                break
            }
            try {
                column1.toInt()
            } catch (e: NumberFormatException) {
                println("Incorrect column number")
                playing(name)
                break
            }
            val column = column1.toInt()
            when {
                column > 0 && column <= z[0].size && 0 <= chest[column - 1] -> {
                    if (name == firstPlayer) z[chest[column - 1]][column - 1] = "o"
                    else z[chest[column - 1]][column - 1] = "*"
                    chest[column - 1]--
                    break
                }
                column !in 1..z[0].size -> {
                    println("The column number is out of range (1 - ${ z[0].size })")
                    playing(name)
                    break
                }
                chest[column - 1] < 0 -> {
                    println("Column $column is full")
                    playing(name)
                    break
                }
            }
        }
        if (end == 0) return false
        return true
    }
    fun checkingWin(): Boolean {
        var zz = 0
        var other2DArray = MutableList(81) { MutableList(0) {""} }
        for (i in 0..z[0].lastIndex) {
            if (z[0][i] != " ") zz++
            for (j in 0..z.lastIndex) {
                other2DArray[i + j].add(z[j][i])
                when {
                    other2DArray[i + j].joinToString("") == FWN -> {
                        println("Player $firstPlayer won")
                        firstWin += 2
                        return false
                    }
                    other2DArray[i + j].joinToString("") == SWN -> {
                        println("Player $secondPlayer won")
                        secondWin += 2
                        return false
                    }
                }
            }
        }
        if (zz == z[0].size) {
            println("It is a draw")
            firstWin++
            secondWin++
            return false
        }
        other2DArray = MutableList(81) { MutableList(0) {""} }
        for (i in 0..z[0].lastIndex) {
            var x = ""
            for (j in 0..z.lastIndex) {
                x += z[j][i]
                other2DArray[i + j].add(z[j][z[0].lastIndex - i])
                when {
                    other2DArray[i + j].joinToString("") == FWN -> {
                        println("Player $firstPlayer won")
                        firstWin += 2
                        return false
                    }
                    other2DArray[i + j].joinToString("") == SWN -> {
                        println("Player $secondPlayer won")
                        secondWin += 2
                        return false
                    }
                    z[j].joinToString("").contains(FWN) -> {
                        println("Player $firstPlayer won")
                        firstWin += 2
                        return false
                    }
                    z[j].joinToString("").contains(SWN) -> {
                        println("Player $secondPlayer won")
                        secondWin += 2
                        return false
                    }
                    x.contains(FWN) -> {
                        println("Player $firstPlayer won")
                        firstWin += 2
                        return false
                    }
                    x.contains(SWN) -> {
                        println("Player $secondPlayer won")
                        secondWin += 2
                        return false
                    }
                }
            }
        }
        return true
    }
}

fun main() {
    val x = Game()
    x.output()
    while (x.numbergame != 0) {
        if (x.numbergame > 1 || x.nowGame > 1) println("Game #${x.nowGame}")
        x.printBoard()
        while (true) {
            if (!x.playing(if (x.nowGame % 2 == 1) x.firstPlayer else x.secondPlayer)) break
            x.printBoard()
            if (!x.checkingWin()) break
            if (!x.playing(if (x.nowGame % 2 == 1) x.secondPlayer else x.firstPlayer)) break
            x.printBoard()
            if (!x.checkingWin()) break
        }
        x.numbergame--
        x.nowGame++
        for (i in x.z) {
            i.replaceAll { " " }
        }
        x.chest = MutableList(x.z[0].size) { x.z.lastIndex }
        if (x.numbergame != 0) {
            x.gameThis()
        }
    }
    if (x.nowGame > 2) {
        x.gameThis()
    }
    println("Game over!")
}