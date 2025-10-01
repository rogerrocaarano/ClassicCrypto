package utils

fun fillMatrix(matrix: Array<CharArray>, text: String, fillChar: Char) {
    var index = 0
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
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

fun matrixToString(matrix: Array<CharArray>): String {
    val stringBuilder = StringBuilder()
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            stringBuilder.append(matrix[i][j])
        }
    }
    return stringBuilder.toString()
}

fun stringToMatrix(
    text: String,
    rows: Int,
    cols: Int,
    fillChar: Char
): Array<CharArray> {
    val matrix = Array(rows) { CharArray(cols) { fillChar } }
    fillMatrix(matrix, text, fillChar)
    return matrix
}

fun calculateRequiredRows(textLength: Int, keyLength: Int): Int {
    return (textLength + keyLength - 1) / keyLength
}