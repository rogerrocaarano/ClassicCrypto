package lib.ciphers.columnartransposition

import lib.abstractions.Alphabet
import lib.abstractions.BaseCipher
import lib.utils.calculateRequiredRows
import lib.utils.matrixToString
import lib.utils.stringToMatrix
import lib.utils.transposeMatrix

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
        require(keyParameter is ColumnarTranspositionCipherKeyParameters)
        val prepared = prepareText(plainText)
        val rows = calculateRequiredRows(prepared.length, keyParameter.columnSize)
        val matrix = stringToMatrix(prepared, rows, keyParameter.columnSize, keyParameter.fillChar)
        return matrixToString(transposeMatrix(matrix))
    }

    /**
     * Descifra el texto usando transposición columnar.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Clave de transposición (orden de columnas).
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        require(keyParameter is ColumnarTranspositionCipherKeyParameters)
        val rows = keyParameter.columnSize
        require(encryptedText.length % rows == 0)
        val cols = encryptedText.length / rows
        val matrix = stringToMatrix(encryptedText, rows, cols, keyParameter.fillChar)
        val txt = matrixToString(transposeMatrix(matrix))
        return txt.replace(keyParameter.fillChar.toString(), "")
    }
}