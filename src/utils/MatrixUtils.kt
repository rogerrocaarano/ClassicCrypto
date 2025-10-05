package utils

fun fillMatrix(matrix: Array<CharArray>, text: String, fillChar: Char, colsOrder: List<Int>? = null) {
    var index = 0
    for (i in matrix.indices) {
        val columnIndices = colsOrder ?: matrix[i].indices.toList()
        for (j in columnIndices) {
            if (index < text.length) {
                matrix[i][j] = text[index]
                index++
            } else {
                matrix[i][j] = fillChar
            }
        }
    }
}

fun transposeMatrix(matrix: Array<CharArray>): Array<CharArray> {
    val rowSize = matrix.size
    val columnSize = matrix[0].size
    val transposed = Array(columnSize) { CharArray(rowSize) }
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            transposed[j][i] = matrix[i][j]
        }
    }

    return transposed
}

fun matrixToString(matrix: Array<CharArray>, colsOrder: List<Int>? = null): String {
    val stringBuilder = StringBuilder()
    for (row in matrix.indices) {
        val columnIndices = colsOrder ?: matrix[row].indices.toList()
        for (colIndex in columnIndices) {
            stringBuilder.append(matrix[row][colIndex])
        }
    }
    return stringBuilder.toString()
}

fun stringToMatrix(
    text: String,
    rows: Int,
    cols: Int,
    fillChar: Char,
    colsOrder: List<Int>? = null
): Array<CharArray> {
    val matrix = Array(rows) { CharArray(cols) { fillChar } }
    fillMatrix(matrix, text, fillChar, colsOrder)
    return matrix
}

fun calculateRequiredRows(textLength: Int, keyLength: Int): Int {
    return (textLength + keyLength - 1) / keyLength
}