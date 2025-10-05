import lib.abstractions.Alphabet
import lib.ciphers.columnartransposition.ColumnarTranspositionCipher
import lib.ciphers.columnartransposition.ColumnarTranspositionCipherKeyParameters
import lib.alphabets.LATIN_CHARS
import lib.alphabets.WHITE_SPACE

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val alphabet = Alphabet(LATIN_CHARS + WHITE_SPACE)

    val cipher = ColumnarTranspositionCipher(alphabet)
    val keyParameters = ColumnarTranspositionCipherKeyParameters(columnSize = 3, fillChar = '=')

    val plaintext = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
    val encrypted = cipher.encrypt(plaintext, keyParameters)
    val decrypted = cipher.decrypt(encrypted, keyParameters)

    println("Encrypted: $encrypted")
    println("Decrypted: $decrypted")

}