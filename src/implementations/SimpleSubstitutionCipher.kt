package implementations

import abstractions.Alphabet
import abstractions.BaseCipher

/**
 * Implementación del cifrado de sustitución simple.
 * Cada letra del alfabeto se sustituye por otra letra de forma consistente.
 */
class SimpleSubstitutionCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando sustitución simple.
     * @param plainText Texto a cifrar.
     * @param keyParameter Alfabeto de sustitución o mapa de sustituciones.
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando sustitución simple.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Alfabeto de sustitución o mapa de sustituciones.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}
