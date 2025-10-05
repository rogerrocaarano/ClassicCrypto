package lib.ciphers.keyparameters

data class ColumnarTranspositionCipherKeyParameters(
    val columnSize: Int,
    val fillChar: Char
)