package classiccrypto.ciphers.permutation

import classiccrypto.abstractions.Alphabet
import classiccrypto.abstractions.BaseCipher
import classiccrypto.utils.calculateRequiredRows
import classiccrypto.utils.matrixToString
import classiccrypto.utils.stringToMatrix

/**
 * Implementación del cifrado de permutación.
 * Un cifrado de transposición que reordena las posiciones de las letras
 * según una clave de permutación específica.
 */
class PermutationCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando permutación.
     * @param plainText Texto a cifrar.
     * @param keyParameter Array o lista que define la permutación de posiciones.
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        require(keyParameter is PermutationCipherKeyParameter)
        val cleanText = prepareText(plainText)
        val perm = keyParameter.permutation()
        val rows = calculateRequiredRows(cleanText.length, perm.size)
        val matrix = stringToMatrix(cleanText, rows, perm.size, keyParameter.fillChar)

        return matrixToString(matrix, getColumnOrder(perm))
    }

    /**
     * Descifra el texto usando permutación.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Array o lista que define la permutación de posiciones.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        require(keyParameter is PermutationCipherKeyParameter)
        val perm = keyParameter.permutation()
        val cols = perm.size
        require(encryptedText.length % cols == 0)
        val rows = encryptedText.length / cols
        val matrix = stringToMatrix(encryptedText, rows, cols, keyParameter.fillChar, getColumnOrder(perm))
        val txt = matrixToString(matrix)
        return txt.replace(keyParameter.fillChar.toString(), "")
    }

    private fun getColumnOrder(perm: List<Int>): List<Int> {
        return perm.withIndex().sortedBy { it.value }.map { it.index }
    }
}