package com.toxicaven

internal object Converter {

    @JvmStatic
    fun main (args: Array<String>) {
        if (args.isEmpty()) {
            print("-----AvenEso-----\n" +
                    "0. Convert eso to BF\n" +
                    "1. Solve eso (eso -> BF -> output)\n" +
                    "2. Solve BF (BF -> output)\n" +
                    "3. Convert BF to eso\n" +
                    "-----------------\n" +
                    "The first argument should be one of the above operators,\n" +
                    "and the second argument should be either BF or Eso,\n" +
                    "depending on the operator given.\n" +
                    "You may need to wrap the BF or Eso in Quotations (\"\")\n")
            return
        }
        when (args[0]) {
            "0" -> {Interpreter.fromEso(args[1], false)}
            "1" -> {Interpreter.fromEso(args[1], true)}
            "2" -> {print("-----AvenEso-----\nInput BF: $args[1]\nOutput: ")
                    Interpreter.interpretBF(args[1])}
            "3" -> {Interpreter.toEso(args[1])}
            else -> print("An invalid operation ID was provided.")
        }
    }
}