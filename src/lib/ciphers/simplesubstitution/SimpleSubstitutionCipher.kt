package lib.ciphers.simplesubstitution

import lib.abstractions.Alphabet
import lib.abstractions.BaseCipher
import lib.alphabets.LATIN_CHARS_UPPER
import kotlin.text.iterator

/**
 * Implementación del cifrado de sustitución simple.
 * Reemplaza cada carácter del texto plano por un carácter correspondiente según un mapa de sustitución.
 */
class SimpleSubstitutionCipher(
    alphabet: Alphabet,
    ignoreCase: Boolean = true,
    ignoreEspecialChars: Boolean = true
) : BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    /**
     * Cifra el texto usando sustitución simple.
     * @param plainText Texto a cifrar.
     * @param keyParameter Mapa de sustitución o clave de sustitución.
     * @return Texto cifrado.
     */
    override fun encrypt(plainText: String, keyParameter: Any?): String {
        val key = SimpleSubstitutionKey.fromParameter(keyParameter, getWorkingAlphabet())
        val processedText = prepareText(plainText)

        return buildString {
            for (char in processedText) {
                when {
                    // Si el carácter está en el mapa de sustitución, sustituirlo
                    key.substitutionMap.containsKey(char) -> {
                        append(key.substitutionMap[char])
                    }
                    // Si no está en el mapa pero debemos mantener caracteres especiales
                    !ignoreEspecialChars -> {
                        append(char)
                    }
                    // Si ignorar caracteres especiales y no está en el mapa, omitirlo
                    else -> {
                        // No agregar el carácter
                    }
                }
            }
        }
    }

    /**
     * Descifra el texto usando sustitución simple.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Mapa de sustitución o clave de sustitución.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        val key = SimpleSubstitutionKey.fromParameter(keyParameter, getWorkingAlphabet())
        val processedText = prepareText(encryptedText)

        return buildString {
            for (char in processedText) {
                when {
                    // Si el carácter está en el mapa inverso, sustituirlo de vuelta
                    key.reverseMap.containsKey(char) -> {
                        append(key.reverseMap[char])
                    }
                    // Si no está en el mapa pero debemos mantener caracteres especiales
                    !ignoreEspecialChars -> {
                        append(char)
                    }
                    // Si ignorar caracteres especiales y no está en el mapa, omitirlo
                    else -> {
                        // No agregar el carácter
                    }
                }
            }
        }
    }

    /**
     * Obtiene el alfabeto de trabajo para crear claves
     */
    private fun getWorkingAlphabet(): String {
        // Para Simple Substitution, usamos el alfabeto básico
        // Podrías personalizar esto según tus necesidades
        return LATIN_CHARS_UPPER
    }

    /**
     * Genera un mapa de sustitución aleatorio
     */
    fun generateRandomKey(): SimpleSubstitutionKey {
        val alphabet = getWorkingAlphabet()
        val shuffled = alphabet.toList().shuffled().joinToString("")
        return SimpleSubstitutionKey.fromAlphabetString(alphabet, shuffled)
    }

    /**
     * Obtiene información sobre la clave actual
     */
    fun getKeyInfo(keyParameter: Any?): String {
        val key = SimpleSubstitutionKey.fromParameter(keyParameter, getWorkingAlphabet())
        return """
            |=== INFORMACIÓN DE CLAVE SUSTITUCIÓN ===
            |Alfabeto original: ${key.substitutionMap.keys.joinToString("").take(15)}...
            |Alfabeto sustitución: ${key.substitutionMap.values.joinToString("").take(15)}...
            |Tamaño del mapa: ${key.substitutionMap.size}
        """.trimMargin()
    }
}