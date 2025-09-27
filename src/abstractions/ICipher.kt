package abstractions

/**
 * Interfaz para cifrados clásicos.
 * Define las operaciones básicas de cifrado y descifrado.
 */
interface ICipher {
    /**
     * Cifra el texto plano usando el parámetro de clave opcional.
     * @param plainText Texto a cifrar.
     * @param keyParameter Parámetro de clave (opcional).
     * @return Texto cifrado.
     */
    fun encrypt(plainText: String, keyParameter: Any? = null): String

    /**
     * Descifra el texto cifrado usando el parámetro de clave opcional.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Parámetro de clave (opcional).
     * @return Texto descifrado.
     */
    fun decrypt(encryptedText: String, keyParameter: Any? = null): String
}
