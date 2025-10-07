package classiccrypto.ciphers.autokey

/**
 * Representa los parámetros de la clave para el cifrado Autokey.
 */
data class AutokeyKeyParameters(val key: String) {
    companion object {
        fun fromParameter(param: Any?): AutokeyKeyParameters {
            return when (param) {
                is String -> {
                    require(param.isNotEmpty()) { "La clave no puede estar vacía." }
                    AutokeyKeyParameters(param)
                }
                else -> throw IllegalArgumentException("La clave debe ser una cadena de texto.")
            }
        }
    }
}

