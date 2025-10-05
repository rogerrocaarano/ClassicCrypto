package lib.ciphers.keyparameters

/**
 * Parámetro de clave para el cifrado César
 * @property shift Número de posiciones a desplazar
 */
data class CesarKey(val shift: Int) {
    init {
        require(shift in -100..100) { "El desplazamiento debe estar entre -100 y 100" }
    }

    companion object {
        /**
         * Crea una clave César a partir de cualquier tipo
         */
        fun fromParameter(parameter: Any?): CesarKey {
            return when (parameter) {
                is Int -> CesarKey(parameter)
                is CesarKey -> parameter
                is String -> CesarKey(parameter.toIntOrNull() ?: 0)
                else -> throw IllegalArgumentException("Tipo de parámetro no válido para César: ${parameter?.javaClass?.simpleName}")
            }
        }
    }
    
    override fun toString(): String {
        return "CesarKey(shift=$shift)"
    }
}