package classiccrypto.ciphers.permutation

import classiccrypto.abstractions.Alphabet
import classiccrypto.alphabets.LATIN_CHARS_UPPER
import classiccrypto.alphabets.WHITE_SPACE
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PermutationCipherTest {
    companion object {
        @JvmStatic
        fun provideTestData(): Stream<Array<Any>> = Stream.of(
            arrayOf("ABC", "Lorem ipsum", '='),
            arrayOf("KEY", "HELLO WORLD", '*'),
            arrayOf("QWERTY", "TEST STRING", '#')
        )

        @JvmStatic
        fun provideEncryptionDecryptionTestData(): Stream<Array<Any>> = Stream.of(
            arrayOf("BCA", "ABCDEFGHIJ", "=", "CABFDEIGH=J="),
            arrayOf("CAB", "ABCDEFGHIJ", "=", "BCAEFDHIG==J"),
            arrayOf("BDDA", "ABCDEFGHIJ", "=", "DABCHEFG=IJ=")
        )
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun encryptOutputLengthShouldBeMoreOrEqualsInputText(keyPhrase: String, plainText: String, fillChar: Char) {
        val alphabet = Alphabet(LATIN_CHARS_UPPER + WHITE_SPACE)
        val keyAlphabet = Alphabet(LATIN_CHARS_UPPER)
        val cipher = PermutationCipher(alphabet)
        val key = PermutationCipherKeyParameter(keyPhrase, keyAlphabet, fillChar)

        val encryptedText = cipher.encrypt(plainText, key)
        assertTrue(encryptedText.length >= plainText.length)
    }

    @ParameterizedTest
    @MethodSource("provideEncryptionDecryptionTestData")
    fun encryptShouldReturnExpectedResult(keyPhrase: String, plainText: String, fillChar: String, expectedEncryptedText: String) {
        val alphabet = Alphabet(LATIN_CHARS_UPPER + WHITE_SPACE)
        val keyAlphabet = Alphabet(LATIN_CHARS_UPPER)
        val cipher = PermutationCipher(alphabet)
        val key = PermutationCipherKeyParameter(keyPhrase, keyAlphabet, fillChar[0])
        val encryptedText = cipher.encrypt(plainText, key)
        assertEquals(expectedEncryptedText, encryptedText)
    }

    @ParameterizedTest
    @MethodSource("provideEncryptionDecryptionTestData")
    fun decryptShouldReturnOriginalText(keyPhrase: String, originalText: String, fillChar: String, encryptedText: String) {
        val alphabet = Alphabet(LATIN_CHARS_UPPER + WHITE_SPACE)
        val keyAlphabet = Alphabet(LATIN_CHARS_UPPER)
        val cipher = PermutationCipher(alphabet)
        val key = PermutationCipherKeyParameter(keyPhrase, keyAlphabet, fillChar[0])
        val decryptedText = cipher.decrypt(encryptedText, key)
        assertEquals(originalText, decryptedText)
    }
}