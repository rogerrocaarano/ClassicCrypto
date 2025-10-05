package lib.ciphers.permutation

import lib.abstractions.Alphabet
import kotlin.text.iterator

data class PermutationCipherKeyParameter(
    val keyPhrase: String,
    val keyAlphabet: Alphabet,
    val fillChar: Char
) {
    init {
        require(keyPhrase.isNotEmpty())
        require(keyAlphabet.validateText(keyPhrase))
    }

    private fun getMinValChar(s: String): Char {
        var minChar = s[0]
        for (char in s) {
            if (keyAlphabet.indexOf(char) < keyAlphabet.indexOf(minChar)) {
                minChar = char
            }
        }
        return minChar
    }

    private fun getCharIndexesFromKeyPhrase(c: Char): List<Int> {
        val indexes = mutableListOf<Int>()
        for (i in keyPhrase.indices) {
            if (keyPhrase[i] == c) {
                indexes.add(i)
            }
        }

        return indexes
    }

    fun permutation(): List<Int> {
        var temp = keyPhrase
        val perm = MutableList(keyPhrase.length) { -1 }

        var i = 0

        while (temp.isNotEmpty()) {
            val minChar = getMinValChar(temp)
            val indexes = getCharIndexesFromKeyPhrase(minChar)
            for (index in indexes) {
                perm[index] = i
                i++
            }
            temp = temp.replace(minChar.toString(), "")
        }

        return perm
    }
}