fun solution(products: List<String>, product: String) {
    for (i in 0..products.lastIndex) {
        if (products[i] == product) print("$i ")
    }
}