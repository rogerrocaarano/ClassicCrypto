package classiccrypto.ciphers.hill

import classiccrypto.abstractions.Alphabet
import classiccrypto.abstractions.BaseCipher

/**
 * Implementación del cifrado Hill.
 * Un cifrado de sustitución que opera sobre grupos de letras usando álgebra lineal
 * con una matriz clave para la transformación.
 */
class HillCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando el cifrado Hill.
     * @param plainText Texto a cifrar.
     * @param keyParameter Matriz clave para la transformación linear.
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando el cifrado Hill.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Matriz clave para la transformación linear.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}