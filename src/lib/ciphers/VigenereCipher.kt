package lib.ciphers

import lib.abstractions.Alphabet
import lib.abstractions.BaseCipher

/**
 * Implementación del cifrado de Vigenère.
 * Un cifrado polialfabético que utiliza una palabra clave repetida.
 */
class VigenereCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando el cifrado de Vigenère.
     * @param plainText Texto a cifrar.
     * @param keyParameter Palabra clave para el cifrado.
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando el cifrado de Vigenère.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Palabra clave para el descifrado.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}
