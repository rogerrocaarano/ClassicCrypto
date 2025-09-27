package implementations

import abstractions.Alphabet
import abstractions.BaseCipher

/**
 * Implementación del cifrado de César.
 * Un cifrado de sustitución simple donde cada letra se desplaza un número fijo
 * de posiciones en el alfabeto. Es uno de los cifrados más antiguos y conocidos.
 */
class CesarCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando el cifrado de César.
     * @param plainText Texto a cifrar.
     * @param keyParameter Número de posiciones a desplazar (Int).
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando el cifrado de César.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Número de posiciones a desplazar (Int).
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}