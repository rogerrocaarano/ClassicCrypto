package lib.ciphers.xor

import lib.abstractions.Alphabet
import lib.abstractions.BaseCipher

/**
 * Implementación del cifrado XOR.
 * Un cifrado que aplica la operación XOR bit a bit entre el texto y una clave,
 * convirtiendo caracteres a sus valores numéricos correspondientes.
 */
class XORCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando operación XOR.
     * @param plainText Texto a cifrar.
     * @param keyParameter Clave para la operación XOR (String o ByteArray).
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando operación XOR.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Clave para la operación XOR (String o ByteArray).
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}