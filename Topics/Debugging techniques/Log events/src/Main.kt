fun String?.capitalize(): String? {
    println("Before: $this")
    println(
        "After: ${when {
            isNullOrBlank() -> this
            length == 1 -> uppercase()
            else -> this[0].uppercase() + substring(1)}
        }"
    )
    return when {
        isNullOrBlank() -> this
        length == 1 -> uppercase()
        else -> this[0].uppercase() + substring(1)
    }
}