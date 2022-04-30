package org.paradigm.util

object Base37 {

    /**
     * Encodes a string into its base37 integral value
     *
     * @param value the string to encode
     * @return the base37 value
     */
    fun encode(value: String): Long {
        var str = ""
        for (element in value) {
            val c = element
            str = when (c) {
                in 'a'..'z' -> str + c
                in 'A'..'Z' -> str + (c.code + 97 - 65).toChar()
                in '0'..'9' -> str + c
                else -> "$str "
            }
        }
        str = str.trim { it <= ' ' }
        if (str.length > 12) str = str.substring(0, 12)
        var base = 0L
        for (c in str) {
            base *= 37L
            if (c in 'a'..'z') base += (1 + c.code - 97).toLong() else if (c in '0'..'9') base += (27 + c.code - 48).toLong()
        }
        return base
    }

    /**
     * Decodes a base37 integral value into a string
     *
     * @param base the base37 value
     * @return the decoded string value
     */
    fun decode(value: Long): String? {
        var base = value
        if (base < 0L) return "invalid_name"
        var str = ""
        while (base != 0L) {
            val remainder = (base % 37L).toInt()
            base /= 37L
            str = if (remainder == 0) " $str" else if (remainder < 27) {
                if (remainder % 37L == 0L) (remainder + 65 - 1).toChar()
                    .toString() + str else (remainder + 97 - 1).toChar()
                    .toString() + str
            } else {
                (value + 48 - 27).toChar().toString() + str
            }
        }
        return str
    }
}