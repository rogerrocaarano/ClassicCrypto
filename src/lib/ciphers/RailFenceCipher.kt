package lib.ciphers

import lib.abstractions.Alphabet
import lib.abstractions.BaseCipher

/**
 * Implementación del cifrado Rail Fence (Valla de Ferrocarril).
 * Un cifrado de transposición que escribe el texto en zigzag sobre múltiples "rieles"
 * y luego lee las letras por filas.
 */
class RailFenceCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando el cifrado Rail Fence.
     * @param plainText Texto a cifrar.
     * @param keyParameter Número de rieles (niveles) del zigzag.
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando el cifrado Rail Fence.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Número de rieles (niveles) del zigzag.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}
