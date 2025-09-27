package implementations

import abstractions.Alphabet
import abstractions.BaseCipher

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
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando permutación.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Array o lista que define la permutación de posiciones.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}
