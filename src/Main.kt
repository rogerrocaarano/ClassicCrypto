import abstractions.Alphabet
import implementations.ColumnarTranspositionCipher
import implementations.CesarCipher
import implementations.keyparameters.CesarKey
import implementations.keyparameters.ColumnarTranspositionCipherKeyParameters
import implementations.alphabets.LATIN_CHARS
import implementations.alphabets.WHITE_SPACE
import implementations.SimpleSubstitutionCipher
import implementations.keyparameters.SimpleSubstitutionKey

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

fun main() {

    println(" Cifrado ColumnarTranspositionCipher ")

    val alphabet = Alphabet(LATIN_CHARS + WHITE_SPACE)  

    val cipheri = ColumnarTranspositionCipher(alphabet)
    val keyParametersi = ColumnarTranspositionCipherKeyParameters(columnSize = 3, fillChar = '=')

    val plaintexti = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
    val encryptedi = cipheri.encrypt(plaintexti, keyParametersi)
    val decryptedi = cipheri.decrypt(encryptedi, keyParametersi)

    println("Encrypted: $encryptedi")
    println("Decrypted: $decryptedi")

      println(" Cifrado Cesar ")  

    val cipher = CesarCipher(alphabet, ignoreEspecialChars = false)
    val keyParameters = CesarKey(9)

    val plaintext = "LOREM IPSUM DOLOR SIT AMET CONSECTETUR ADIPISCING ELIT"
    val encrypted = cipher.encrypt(plaintext, keyParameters)
    val decrypted = cipher.decrypt(encrypted, keyParameters) 

    println("Alphabet: ${LATIN_CHARS.take(10)}...")
    println("Key: $keyParameters")
    println("Plaintext:  $plaintext")
    println("Encrypted: $encrypted")
    println("Decrypted: $decrypted")

      println("Cifrado de sustitucion simple")

    val cipher5 = SimpleSubstitutionCipher(alphabet, ignoreEspecialChars = false)
    val randomKey = cipher5.generateRandomKey()    //genera una clave "aleatoria"
    val plaintext5 = "RANDOM KEY EXAMPLE"
    val encrypted5 = cipher5.encrypt(plaintext5, randomKey)
    val decrypted5 = cipher5.decrypt(encrypted5, randomKey)

    //println(cipher5.getKeyInfo(randomKey))
    println("Plaintext:  $plaintext5")
    println("Encrypted: $encrypted5")
    println("Decrypted: $decrypted5")
}
