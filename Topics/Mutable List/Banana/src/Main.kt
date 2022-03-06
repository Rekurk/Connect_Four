fun solution(stringss: MutableList<String>, str: String): MutableList<String> {
    for (i in 0..stringss.lastIndex) if (stringss[i] == str) stringss[i] = "Banana"
    return stringss
}