package classiccrypto.utils

/**
 * Llena una matriz de caracteres con el texto dado, siguiendo el orden de columnas especificado.
 * Si el texto es más corto que la matriz, se rellena con el carácter indicado.
 * @param matrix Matriz a llenar.
 * @param text Texto a insertar en la matriz.
 * @param fillChar Carácter de relleno si el texto no cubre toda la matriz.
 * @param colsOrder Orden de columnas opcional para el llenado.
 */
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

/**
 * Transpone una matriz de caracteres (intercambia filas por columnas).
 * @param matrix Matriz original.
 * @return Matriz transpuesta.
 */
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

/**
 * Convierte una matriz de caracteres en un String, siguiendo el orden de columnas especificado.
 * @param matrix Matriz a convertir.
 * @param colsOrder Orden de columnas opcional para la conversión.
 * @return Cadena resultante de la matriz.
 */
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

/**
 * Convierte un String en una matriz de caracteres, rellenando con el carácter indicado si es necesario.
 * @param text Texto a convertir.
 * @param rows Número de filas de la matriz.
 * @param cols Número de columnas de la matriz.
 * @param fillChar Carácter de relleno si el texto no cubre toda la matriz.
 * @param colsOrder Orden de columnas opcional para el llenado.
 * @return Matriz resultante.
 */
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

/**
 * Calcula el número de filas requeridas para acomodar el texto en una matriz dada la longitud de la clave.
 * @param textLength Longitud del texto.
 * @param keyLength Longitud de la clave.
 * @return Número de filas requeridas.
 */
fun calculateRequiredRows(textLength: Int, keyLength: Int): Int {
    return (textLength + keyLength - 1) / keyLength
}