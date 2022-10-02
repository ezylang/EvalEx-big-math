/*
  Copyright 2012-2022 Udo Klimaschewski

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/
package com.ezylang.evalex.bigmath.operators.bigdecimalmath;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.bigmath.BigMathExpression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.operators.OperatorIfc;
import com.ezylang.evalex.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BigDecimalMathOperatorTest {

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "0^0 : 1",
        "0^1 : 0",
        "1^1 : 1",
        "1^2 : 1",
        "2^2 : 4",
        "-2^2 : 4",
        "2^2.5 : 5.6568542494923801952067548968387923142786875015077922927067189519629",
        "2.5^2 : 6.25",
        "2.5^2.5 : 9.8821176880261854124965423263522454178736098103913025839297026649769"
      })
  void testPowerOf(String expression, String expectedResult)
      throws EvaluationException, ParseException {

    assertThat(new BigMathExpression(expression).evaluate().getStringValue())
        .isEqualTo(expectedResult);
  }

  @Test
  void testPowerOfPrecedenceHigher() throws ParseException, EvaluationException {
    ExpressionConfiguration config =
        ExpressionConfiguration.builder()
            .powerOfPrecedence(OperatorIfc.OPERATOR_PRECEDENCE_POWER_HIGHER)
            .build();

    BigMathExpression expression = new BigMathExpression("-2^2", config);

    assertThat(expression.evaluate().getStringValue()).isEqualTo("-4");
  }

  @ParameterizedTest
  @ValueSource(strings = {"\"a\"^0", "0^\"a\"", "\"a\"^\"a\""})
  void testPowerOfTypeCheck(String expression) {

    assertThatThrownBy(() -> new BigMathExpression(expression).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Unsupported data types in operation");
  }
}
