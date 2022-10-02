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
package com.ezylang.evalex.bigmath;

import com.ezylang.evalex.Expression;
import com.ezylang.evalex.bigmath.functions.bigdecimalmath.BigDecimalMathFunctions;
import com.ezylang.evalex.bigmath.operators.bigdecimalmath.BigDecimalMathOperators;
import com.ezylang.evalex.config.ExpressionConfiguration;

/** An expression that has the big-math functions configured by default. */
public class BigMathExpression extends Expression {

  public BigMathExpression(String expressionString) {
    super(
        expressionString,
        ExpressionConfiguration.defaultConfiguration()
            .withAdditionalFunctions(BigDecimalMathFunctions.allFunctions())
            .withAdditionalOperators(BigDecimalMathOperators.allOperators()));
  }

  public BigMathExpression(String expressionString, ExpressionConfiguration configuration) {
    super(
        expressionString,
        configuration
            .withAdditionalFunctions(BigDecimalMathFunctions.allFunctions())
            .withAdditionalOperators(BigDecimalMathOperators.allOperators()));
  }
}
