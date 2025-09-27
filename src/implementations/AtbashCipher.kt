package implementations

import abstractions.Alphabet
import abstractions.BaseCipher

/**
 * Implementación del cifrado Atbash.
 * Un cifrado de sustitución simple donde cada letra se reemplaza por su correspondiente
 * en el alfabeto invertido (A->Z, B->Y, etc.).
 */
class AtbashCipher(alphabet: Alphabet, ignoreCase: Boolean = true, ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando el cifrado Atbash.
     * @param plainText Texto a cifrar.
     * @param keyParameter No se utiliza en Atbash (el alfabeto invertido es la clave).
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }

    /**
     * Descifra el texto usando el cifrado Atbash.
     * @param encryptedText Texto cifrado.
     * @param keyParameter No se utiliza en Atbash (el alfabeto invertido es la clave).
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        TODO("Not yet implemented")
    }
}
