package com.toxicaven

import java.util.*

internal object Interpreter {
    private val sc = Scanner(System.`in`)
    private const val length = 65535
    private val mem = ByteArray(length)
    private var dataPointer = 0

    private const val bfMinus = "α" // -
    private const val bfPlus = "β" // +
    private const val bfLessThan = "γ" // <
    private const val bfGreaterThan = "δ" // >
    private const val bfPeriod = "ε" // .
    private const val bfComma = "ζ" // ,
    private const val bfOpenBracket = "η" // [
    private const val bfCloseBracket = "θ" // ]

    fun interpretBF(code: String) {
        var l = 0
        var i = 0
        while (i < code.length) {
            if (code[i] == '>') {
                dataPointer = if (dataPointer == length - 1) 0 else dataPointer + 1
            } else if (code[i] == '<') {
                dataPointer = if (dataPointer == 0) length - 1 else dataPointer - 1
            } else if (code[i] == '+') {
                mem[dataPointer]++
            } else if (code[i] == '-') {
                mem[dataPointer]--
            } else if (code[i] == '.') {
                print(mem[dataPointer].toInt().toChar())
            } else if (code[i] == ',') {
                mem[dataPointer] = sc.next()[0].code.toByte()
            } else if (code[i] == '[') {
                if (mem[dataPointer] == 0.toByte()) {
                    i++
                    while (l > 0 || code[i] != ']') {
                        if (code[i] == '[') l++
                        if (code[i] == ']') l--
                        i++
                    }
                }
            } else if (code[i] == ']') {
                if (mem[dataPointer] != 0.toByte()) {
                    i--
                    while (l > 0 || code[i] != '[') {
                        if (code[i] == ']') l++
                        if (code[i] == '[') l--
                        i--
                    }
                    i--
                }
            }
            i++
        }
    }

    fun toEso(input: String) {
        val limiter = Regex(pattern = "[a-zA-Z0-9]", options = setOf(RegexOption.IGNORE_CASE))
        val post = input.replace(limiter, "")
        var converted = post.replace("-", bfMinus)
        converted = converted.replace("+", bfPlus)
        converted = converted.replace(",", bfComma)
        converted = converted.replace(".", bfPeriod)
        converted = converted.replace("[", bfOpenBracket)
        converted = converted.replace("]", bfCloseBracket)
        converted = converted.replace("<", bfLessThan)
        val final = converted.replace(">", bfGreaterThan)
        print("-----AvenEso-----\nInput BF: $post\nOutput Eso: $final\n")
    }

    fun fromEso(input: String, solve: Boolean) {
        var converted = input.replace(bfMinus, "-")
        converted = converted.replace(bfPlus, "+")
        converted = converted.replace(bfComma, ",")
        converted = converted.replace(bfPeriod, ".")
        converted = converted.replace(bfOpenBracket, "[")
        converted = converted.replace(bfCloseBracket, "]")
        converted = converted.replace(bfLessThan, "<")
        val final = converted.replace(bfGreaterThan, ">")
        print("-----AvenEso-----\nEso: $input\nBF: $final\n")
        if (solve) print("Solved: ${interpretBF(final)}\n")
    }
}