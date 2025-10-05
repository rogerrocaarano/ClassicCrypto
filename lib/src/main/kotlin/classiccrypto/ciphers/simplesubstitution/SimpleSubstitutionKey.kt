package classiccrypto.ciphers.simplesubstitution

/**
 * Parámetro de clave para el cifrado de sustitución simple
 * @property substitutionMap Mapa de sustitución (carácter original -> carácter sustituido)
 * @property reverseMap Mapa inverso para descifrado (calculado automáticamente)
 */
class SimpleSubstitutionKey(val substitutionMap: Map<Char, Char>) {
    val reverseMap: Map<Char, Char> by lazy {
        substitutionMap.entries.associate { (key, value) -> value to key }
    }

    init {
        require(substitutionMap.isNotEmpty()) { "El mapa de sustitución no puede estar vacío" }
        require(substitutionMap.keys.toSet().size == substitutionMap.values.toSet().size) {
            "El mapa de sustitución debe ser una biyección (caracteres únicos)"
        }
    }

    companion object {
        /**
         * Crea una clave a partir de un string de sustitución
         * Ejemplo: "ZYXWVUTSR  QPONMLKJIHGFEDCBA" para cifrado Atbash
         */
        fun fromAlphabetString(original: String, substitution: String): SimpleSubstitutionKey {
            require(original.length == substitution.length) {
                "Los alfabetos deben tener la misma longitud"
            }
            require(original.toSet().size == original.length && substitution.toSet().size == substitution.length) {
                "Los alfabetos deben contener caracteres únicos"
            }

            val map = original.associateWith { originalChar ->
                val index = original.indexOf(originalChar)
                substitution[index]
            }
            return SimpleSubstitutionKey(map)
        }

        /**
         * Crea una clave a partir de un desplazamiento (como César)
         */
        fun fromShift(alphabet: String, shift: Int): SimpleSubstitutionKey {
            val substitution = buildString {
                for (i in alphabet.indices) {
                    val newIndex = (i + shift).mod(alphabet.length)
                    append(alphabet[newIndex])
                }
            }
            return fromAlphabetString(alphabet, substitution)
        }

        /**
         * Crea una clave a partir de cualquier parámetro
         */
        fun fromParameter(parameter: Any?, alphabet: String = ""): SimpleSubstitutionKey {
            return when (parameter) {
                is SimpleSubstitutionKey -> parameter
                is Map<*, *> -> {
                    val charMap = parameter.entries.associate {
                        (it.key as Char) to (it.value as Char)
                    }
                    SimpleSubstitutionKey(charMap)
                }
                is String -> {
                    when {
                        // Si es un string con formato "original:substitution"
                        parameter.contains(":") -> {
                            val parts = parameter.split(":")
                            require(parts.size == 2) { "Formato: 'alfabetoOriginal:alfabetoSustitucion'" }
                            fromAlphabetString(parts[0], parts[1])
                        }
                        // Si es solo un número (desplazamiento)
                        parameter.toIntOrNull() != null -> {
                            require(alphabet.isNotEmpty()) { "Se necesita un alfabeto para usar desplazamiento" }
                            fromShift(alphabet, parameter.toInt())
                        }
                        // Si es un alfabeto de sustitución directo
                        else -> {
                            require(alphabet.isNotEmpty()) { "Se necesita un alfabeto original" }
                            fromAlphabetString(alphabet, parameter)
                        }
                    }
                }
                is Int -> {
                    require(alphabet.isNotEmpty()) { "Se necesita un alfabeto para usar desplazamiento" }
                    fromShift(alphabet, parameter)
                }
                else -> throw IllegalArgumentException("Tipo de parámetro no válido: ${parameter?.javaClass?.simpleName}")
            }
        }
    }

    override fun toString(): String {
        val original = substitutionMap.keys.joinToString("")
        val substitution = substitutionMap.values.joinToString("")
        return "SimpleSubstitutionKey(original='${original.take(10)}...', substitution='${substitution.take(10)}...')"
    }
}