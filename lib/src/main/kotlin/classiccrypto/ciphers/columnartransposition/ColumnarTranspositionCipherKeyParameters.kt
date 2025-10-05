package classiccrypto.ciphers.columnartransposition

data class ColumnarTranspositionCipherKeyParameters(
    val columnSize: Int,
    val fillChar: Char
)