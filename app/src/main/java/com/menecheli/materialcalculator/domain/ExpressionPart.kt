package com.menecheli.materialcalculator.domain

sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class Op(val operation: Operation) : ExpressionPart
    data class Parentheses(val tye: ParenthesesTye) : ExpressionPart
}

sealed interface ParenthesesTye {
    object Opening : ParenthesesTye
    object Closing : ParenthesesTye
}