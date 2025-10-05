package lib.ciphers

import lib.abstractions.Alphabet
import lib.abstractions.BaseCipher

/**
 * Implementación del cifrado de clave automática (Autokey).
 * Una variante del cifrado Vigenère donde la clave se extiende añadiendo
 * el propio texto plano después de la clave inicial.
 */
class AutoKeyCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando el cifrado Autokey.
     * @param plainText Texto a cifrar.
     * @param keyParameter Clave inicial que se extenderá con el texto plano.
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando el cifrado Autokey.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Clave inicial que se extenderá con el texto plano.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}
