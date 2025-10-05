package implementations

import abstractions.Alphabet
import abstractions.BaseCipher
import implementations.alphabets.*
import implementations.keyparameters.CesarKey

class CesarCipher(
    alphabet: Alphabet,
    ignoreCase: Boolean = true,
    ignoreEspecialChars: Boolean = true
) : BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    override fun encrypt(plainText: String, keyParameter: Any?): String {
        val key = CesarKey.fromParameter(keyParameter)
        val processedText = prepareText(plainText)
        val workingAlphabet = getWorkingAlphabet()
        
        return buildString {
            for (char in processedText) {
                val index = workingAlphabet.indexOf(char)
                
                if (index != -1) {
                    var newIndex = (index + key.shift) % workingAlphabet.length
                    if (newIndex < 0) newIndex += workingAlphabet.length
                    append(workingAlphabet[newIndex])
                } else if (!ignoreEspecialChars) {
                    append(char)
                }
            }
        }
    }

    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        val key = CesarKey.fromParameter(keyParameter)
        return encrypt(encryptedText, CesarKey(-key.shift))
    }

    /**
     * Obtiene el alfabeto de trabajo basado en el Alphabet proporcionado
     */
    private fun getWorkingAlphabet(): String {
        // Usa directamente los caracteres del Alphabet proporcionado
        // Para César necesitamos el orden correcto, así que usamos detección
        return when {
            alphabet.validateText("Ñ") && alphabet.validateText("Á") -> 
                LATIN_CHARS_UPPER + LATIN_CHARS_SPECIAL_UPPER
            alphabet.validateText("Ñ") -> 
                LATIN_CHARS_UPPER + "Ñ"
            else -> 
                LATIN_CHARS_UPPER
        }
    }
}