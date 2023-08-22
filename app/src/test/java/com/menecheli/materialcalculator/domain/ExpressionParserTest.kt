package com.menecheli.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`() {
        // 1. GIVEN
        parser = ExpressionParser("3+5-3x4/3")

        // 2. DO SOMETHING WITH WHAT'S GIVEN
        val actual = parser.parse()

        // 3. ASSERT EXPECTED == ACTUAL
        val expect = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
        )

        assertThat(expect).isEqualTo(actual)
    }

    @Test
    fun `Expression with parentheses is properly parsed`() {
        parser = ExpressionParser("4-(4x5)")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesTye.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesTye.Closing),
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with decimals is properly parsed`() {
        parser = ExpressionParser("3.2-4.21+14.8")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(3.2),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(4.21),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(14.8),
            )
        assertThat(expected).isEqualTo(actual)
    }
}