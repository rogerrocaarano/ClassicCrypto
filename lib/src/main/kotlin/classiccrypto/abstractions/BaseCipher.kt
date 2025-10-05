package classiccrypto.abstractions

/**
 * Clase abstracta base para cifrados clásicos.
 * @property alphabet Instancia de Alphabet utilizada (protegida y solo lectura).
 * @property ignoreCase Indica si se ignora la distinción de mayusculas y minúsculas (protegida y solo lectura).
 * @property ignoreEspecialChars Indica si se ignoran caracteres especiales (protegida y solo lectura).
 */
abstract class BaseCipher protected constructor(
    protected val alphabet: Alphabet,
    protected val ignoreCase: Boolean,
    protected val ignoreEspecialChars: Boolean
) : ICipher {
    /**
     * Prepara el texto según las opciones de la instancia.
     * @param text Texto a preparar.
     * @return Texto preparado.
     */
    protected fun prepareText(text: String): String {
        var result = text
        if (ignoreCase) {
            result = result.uppercase()
        }
        if (ignoreEspecialChars) {
            result = alphabet.filterText(result)
        }
        return result
    }
}
