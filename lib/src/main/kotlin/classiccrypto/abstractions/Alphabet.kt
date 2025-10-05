package classiccrypto.abstractions

/**
 * Representa un alfabeto y operaciones relacionadas.
 * @property alphabet Cadena que representa el alfabeto (privado).
 */
class Alphabet(private val alphabet: String) {
    /**
     * Valida que el texto solo contenga caracteres del alfabeto.
     * @param text Texto a validar.
     * @return true si el texto es válido, false en caso contrario.
     */
    fun validateText(text: String): Boolean {
        return text.all { alphabet.contains(it) }
    }

    /**
     * Realiza un análisis de frecuencia de los caracteres en el texto.
     * @param text Texto a analizar.
     * @return Mapa de caracteres a su frecuencia.
     */
    fun frequencyAnalysis(text: String): Map<Char, Int> {
        return text.filter { alphabet.contains(it) }
            .groupingBy { it }
            .eachCount()
    }

    /**
     * Filtra el texto manteniendo solo los caracteres del alfabeto.
     * @param text Texto a filtrar.
     * @return Texto filtrado.
     */
    fun filterText(text: String): String {
        return text.filter { alphabet.contains(it) }
    }

    /**
     * Obtiene la longitud del alfabeto.
     * @return Longitud del alfabeto.
     */
    fun length(): Int = alphabet.length

    /**
     * Obtiene el índice de un carácter en el alfabeto.
     * @param char Carácter a buscar.
     * @return Índice del carácter, o -1 si no se encuentra.
     */
    fun indexOf(char: Char): Int = alphabet.indexOf(char)

    override fun toString(): String = alphabet
}
