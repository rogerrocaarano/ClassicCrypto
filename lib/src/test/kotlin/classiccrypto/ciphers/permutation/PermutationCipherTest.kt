package classiccrypto.ciphers.permutation

import classiccrypto.abstractions.Alphabet
import classiccrypto.alphabets.LATIN_CHARS_UPPER
import classiccrypto.alphabets.WHITE_SPACE
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PermutationCipherTest {

    private val alphabet = Alphabet(LATIN_CHARS_UPPER + WHITE_SPACE)
    private val keyAlphabet = Alphabet(LATIN_CHARS_UPPER)
    private val cipher = PermutationCipher(alphabet)

    // --- Data Providers ---
    fun encryptionCases(): Stream<Arguments> = Stream.of(
        Arguments.of("BCA", "ABCDEFGHIJ", '=', "CABFDEIGH=J="),
        Arguments.of("CAB", "ABCDEFGHIJ", '=', "BCAEFDHIG==J"),
        Arguments.of("BDDA", "ABCDEFGHIJ", '=', "DABCHEFG=IJ=")
    )

    fun basicCases(): Stream<Arguments> = Stream.of(
        Arguments.of("ABC", "Lorem ipsum", '='),
        Arguments.of("KEY", "HELLO WORLD", '*'),
        Arguments.of("QWERTY", "TEST STRING", '#')
    )

    // --- Tests ---
    @ParameterizedTest(name = "Encrypted output length ≥ input length with key={0}")
    @MethodSource("basicCases")
    fun `encrypt output should be at least as long as input`(keyPhrase: String, plainText: String, fillChar: Char) {
        val key = PermutationCipherKeyParameter(keyPhrase, keyAlphabet, fillChar)
        val result = cipher.encrypt(plainText, key)
        assertTrue(result.length >= plainText.length)
    }

    @ParameterizedTest(name = "Encryption with key={0} should produce expected output")
    @MethodSource("encryptionCases")
    fun `encrypt should match expected output`(
        keyPhrase: String,
        plainText: String,
        fillChar: Char,
        expected: String
    ) {
        val key = PermutationCipherKeyParameter(keyPhrase, keyAlphabet, fillChar)
        assertEquals(expected, cipher.encrypt(plainText, key))
    }

    @ParameterizedTest(name = "Decryption with key={0} should restore original text")
    @MethodSource("encryptionCases")
    fun `decrypt should restore original text`(
        keyPhrase: String,
        originalText: String,
        fillChar: Char,
        encrypted: String
    ) {
        val key = PermutationCipherKeyParameter(keyPhrase, keyAlphabet, fillChar)
        assertEquals(originalText, cipher.decrypt(encrypted, key))
    }
}
