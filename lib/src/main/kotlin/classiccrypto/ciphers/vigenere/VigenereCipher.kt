package classiccrypto.ciphers.vigenere

import classiccrypto.abstractions.Alphabet
import classiccrypto.abstractions.BaseCipher
import classiccrypto.alphabets.LATIN_CHARS_SPECIAL_UPPER
import classiccrypto.alphabets.LATIN_CHARS_UPPER

/**
 * Implementación del cifrado de Vigenère.
 * Un cifrado polialfabético que utiliza una palabra clave repetida.
 */
class VigenereCipher(alphabet: Alphabet,
                     ignoreCase: Boolean = true,
                     ignoreEspecialChars: Boolean = true) :
    BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    override fun encrypt(plainText: String, keyParameter: Any?): String {
        val key = VigenereKeyParameters.fromParameter(keyParameter)

        val processedText = prepareText(plainText)
        val workingAlphabet = getWorkingAlphabet()
        val keyText = prepareText(key.key)

        var keyIndex = 0

        return buildString {
            for (char in processedText) {
                val charIndex = workingAlphabet.indexOf(char)
                if (charIndex != -1) {
                    val shift = workingAlphabet.indexOf(keyText[keyIndex % keyText.length])
                    val newIndex = (charIndex + shift) % workingAlphabet.length
                    append(workingAlphabet[newIndex])
                    keyIndex++
                } else if (!ignoreEspecialChars) {
                    append(char)
                }
            }
        }

    }

    /**
     * Descifra el texto usando el cifrado de Vigenère.
     * @param encryptedText Texto cifrado.
     * @param keyParameter Palabra clave para el descifrado.
     * @return Texto descifrado.
     */
    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        val key = VigenereKeyParameters.fromParameter(keyParameter)
        val processedText = prepareText(encryptedText)
        val workingAlphabet = getWorkingAlphabet()
        val keyText = prepareText(key.key)

        var keyIndex = 0

        return buildString {
            for (char in processedText) {
                val charIndex = workingAlphabet.indexOf(char)

                if (charIndex != -1) {
                    val shift = workingAlphabet.indexOf(keyText[keyIndex % keyText.length])
                    var newIndex = (charIndex - shift) % workingAlphabet.length
                    if (newIndex < 0) newIndex += workingAlphabet.length
                    append(workingAlphabet[newIndex])
                    keyIndex++
                } else if (!ignoreEspecialChars) {
                    append(char)
                }
            }
        }
    }
        private fun getWorkingAlphabet(): String {
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
