package classiccrypto.ciphers.columnartransposition

import classiccrypto.abstractions.Alphabet
import classiccrypto.alphabets.LATIN_CHARS_UPPER
import classiccrypto.alphabets.WHITE_SPACE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ColumnarTranspositionCipherTest {

    private val alphabet = Alphabet(LATIN_CHARS_UPPER + WHITE_SPACE)

    // --- Data Providers ---
    fun encryptionCases(): Stream<Arguments> = Stream.of(
        Arguments.of(2, "ABCDEFGHIJ", '=', "ACEGIBDFHJ"),
        Arguments.of(3, "ABCDEFGHIJ", '=', "ADGJBEH=CFI="),
        Arguments.of(5, "ABCDEFGHIJ", '=', "AFBGCHDIEJ")
    )

    fun basicCases(): Stream<Arguments> = Stream.of(
        Arguments.of(5, "Lorem ipsum", '='),
        Arguments.of(2, "HELLO WORLD", '*'),
        Arguments.of(3, "TEST STRING", '#')
    )


    // --- Tests ---
    @ParameterizedTest(name = "Encrypted output length ≥ input length with columnSize={0}")
    @MethodSource("basicCases")
    fun `encrypt output should be at least as long as input`(columnSize: Int, plainText: String, fillChar: Char) {
        val key = ColumnarTranspositionCipherKeyParameters(columnSize, fillChar)
        val cipher = ColumnarTranspositionCipher(alphabet)
        val result = cipher.encrypt(plainText, key)
        assertTrue(result.length >= plainText.length)
    }

    @ParameterizedTest(name = "Encryption with columnSize={0} should produce expected output")
    @MethodSource("encryptionCases")
    fun `encrypt should match expected output`(
        columnSize: Int,
        plainText: String,
        fillChar: Char,
        expected: String
    ) {
        val key = ColumnarTranspositionCipherKeyParameters(columnSize, fillChar)
        val cipher = ColumnarTranspositionCipher(alphabet)
        assertEquals(expected, cipher.encrypt(plainText, key))
    }

    @ParameterizedTest(name = "Decryption with columnSize={0} should restore original text")
    @MethodSource("encryptionCases")
    fun `decrypt should restore original text`(
        columnSize: Int,
        originalText: String,
        fillChar: Char,
        encrypted: String
    ) {
        val key = ColumnarTranspositionCipherKeyParameters(columnSize, fillChar)
        val cipher = ColumnarTranspositionCipher(alphabet)
        assertEquals(originalText, cipher.decrypt(encrypted, key))
    }
}