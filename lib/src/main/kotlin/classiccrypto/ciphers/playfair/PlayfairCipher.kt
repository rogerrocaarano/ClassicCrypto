package classiccrypto.ciphers.playfair

import classiccrypto.abstractions.Alphabet
import classiccrypto.abstractions.BaseCipher

/**
 * Implementación del cifrado Playfair.
 * Un cifrado de sustitución que opera sobre pares de letras (dígrafos)
 * usando una matriz de 5x5 basada en una palabra clave.
 */
class PlayfairCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando el cifrado Playfair.
     * @param plainText Texto a cifrar.
     * @param keyParameter Palabra clave para generar la matriz Playfair.
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando el cifrado Playfair.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Palabra clave para generar la matriz Playfair.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}