package implementations

import abstractions.Alphabet
import abstractions.BaseCipher
import implementations.keyparameters.ColumnarTranspositionCipherKeyParameters

/**
 * Implementación del cifrado de transposición columnar.
 * Un cifrado de transposición que reordena las letras según un patrón columnar.
 */
class ColumnarTranspositionCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando transposición columnar.
     * @param plainText Texto a cifrar.
     * @param keyParameter Clave de transposición (orden de columnas).
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
       if (keyParameter !is ColumnarTranspositionCipherKeyParameters) {
            throw IllegalArgumentException("keyParameter debe ser de tipo ColumnarTranspositionCipherKeyParameters")
       }

        val preparedText = prepareText(plainText)
        val textLength = preparedText.length
        val rowSize = calculateMatrixRowsSize(textLength, keyParameter.columnSize)
        val matrix = Array(rowSize) { CharArray(keyParameter.columnSize) { keyParameter.fillChar } }
        fillMatrix(matrix, preparedText, keyParameter.fillChar)

        val transposedMatrix = transposeMatrix(matrix)
        return matrixToString(transposedMatrix)
    }

    /**
     * Descifra el texto usando transposición columnar.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Clave de transposición (orden de columnas).
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        if (keyParameter !is ColumnarTranspositionCipherKeyParameters) {
            throw IllegalArgumentException("keyParameter debe ser de tipo ColumnarTranspositionCipherKeyParameters")
        }

        if (!encryptedTextHasValidLength(encryptedText, keyParameter.columnSize)) {
            throw IllegalArgumentException("La longitud del texto cifrado no es válida para el tamaño de columna proporcionado.")
        }

        val rowSize = keyParameter.columnSize
        val colSize = encryptedText.length / rowSize
        val matrix = Array(rowSize) { CharArray(colSize) { keyParameter.fillChar } }
        fillMatrix(matrix, encryptedText, keyParameter.fillChar)

        val transposedMatrix = transposeMatrix(matrix)
        val decryptedText = matrixToString(transposedMatrix)
        return removeFillChars(decryptedText, keyParameter.fillChar)
    }

    private fun calculateMatrixRowsSize(textLength: Int, keyLength: Int): Int {
        return (textLength + keyLength - 1) / keyLength // Redondeo hacia arriba
    }

    private fun fillMatrix(matrix: Array<CharArray>, text: String, fillChar: Char) {
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

    private fun transposeMatrix(matrix: Array<CharArray>): Array<CharArray> {
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

    private fun matrixToString(matrix: Array<CharArray>): String {
        val stringBuilder = StringBuilder()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                stringBuilder.append(matrix[i][j])
            }
        }
        return stringBuilder.toString()
    }

    private fun removeFillChars(text: String, fillChar: Char): String {
        return text.replace(fillChar.toString(), "")
    }

    private fun encryptedTextHasValidLength(encryptedText: String, colSize: Int): Boolean {
        return encryptedText.length % colSize == 0
    }
}