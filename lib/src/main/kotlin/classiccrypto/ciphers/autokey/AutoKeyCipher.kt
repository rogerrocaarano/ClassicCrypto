package classiccrypto.ciphers.autokey

import classiccrypto.abstractions.Alphabet
import classiccrypto.abstractions.BaseCipher
import classiccrypto.alphabets.LATIN_CHARS_SPECIAL_UPPER
import classiccrypto.alphabets.LATIN_CHARS_UPPER

/**
 * Implementación del cifrado Autokey.
 * Similar al Vigenère, pero la clave se extiende con el texto claro.
 */
class AutokeyCipher(
    alphabet: Alphabet,
    ignoreCase: Boolean = true,
    ignoreEspecialChars: Boolean = true
) : BaseCipher(alphabet, ignoreCase, ignoreEspecialChars) {

    override fun encrypt(plainText: String, keyParameter: Any?): String {
        val key = AutokeyKeyParameters.fromParameter(keyParameter)
        val processedText = prepareText(plainText)
        val workingAlphabet = getWorkingAlphabet()
        val keyText = prepareText(key.key)

        val extendedKey = StringBuilder(keyText)
        val cipherText = StringBuilder()

        for (i in processedText.indices) {
            val keyChar = extendedKey[i]
            val shift = workingAlphabet.indexOf(keyChar)
            val charIndex = workingAlphabet.indexOf(processedText[i])

            if (charIndex != -1) {
                val newIndex = (charIndex + shift) % workingAlphabet.length
                val newChar = workingAlphabet[newIndex]
                cipherText.append(newChar)
                extendedKey.append(processedText[i]) // añade el texto claro
            } else if (!ignoreEspecialChars) {
                cipherText.append(processedText[i])
            }
        }

        return cipherText.toString()
    }

    override fun decrypt(encryptedText: String, keyParameter: Any?): String {
        val key = AutokeyKeyParameters.fromParameter(keyParameter)
        val processedText = prepareText(encryptedText)
        val workingAlphabet = getWorkingAlphabet()
        val keyText = prepareText(key.key)

        val extendedKey = StringBuilder(keyText)
        val plainText = StringBuilder()

        for (i in processedText.indices) {
            val keyChar = extendedKey[i]
            val shift = workingAlphabet.indexOf(keyChar)
            val charIndex = workingAlphabet.indexOf(processedText[i])

            if (charIndex != -1) {
                var newIndex = (charIndex - shift) % workingAlphabet.length
                if (newIndex < 0) newIndex += workingAlphabet.length
                val newChar = workingAlphabet[newIndex]
                plainText.append(newChar)
                extendedKey.append(newChar) // añade el texto descifrado
            } else if (!ignoreEspecialChars) {
                plainText.append(processedText[i])
            }
        }

        return plainText.toString()
    }

    /**
     * Obtiene el alfabeto de trabajo basado en el Alphabet proporcionado.
     */
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
