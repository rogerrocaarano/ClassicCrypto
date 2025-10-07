package classiccrypto.ciphers.vigenere

data class VigenereKeyParameters(val key: String) {
    companion object {
        fun fromParameter(param: Any?): VigenereKeyParameters {
            return when (param) {
                is String -> {
                    require(param.isNotEmpty()) { "La clave no puede estar vacía." }
                    VigenereKeyParameters(param)
                }
                else -> throw IllegalArgumentException("La clave debe ser una cadena de texto.")
            }
        }
    }
}
