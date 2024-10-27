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
package com.ezylang.evalex.bigmath.functions.bigdecimalmath;

import static com.ezylang.evalex.bigmath.utils.BigDecimalMathExtension.round;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.parser.Token;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

/**
 * Abstract base class for BigDecimalMath calculations.
 *
 * <p>Does parameter conversion from/to {@link EvaluationValue} and {@link BigDecimal}
 *
 * <p>Also maps {@link ArithmeticException} to {@link EvaluationException}.
 */
public abstract class AbstractBigMathFunction extends AbstractFunction {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token token, EvaluationValue... evaluationValues)
      throws EvaluationException {
    try {
      BigDecimal[] values =
          Arrays.stream(evaluationValues)
              .map(EvaluationValue::getNumberValue)
              .toArray(BigDecimal[]::new);
      MathContext mathContext = expression.getConfiguration().getMathContext();
      return expression.convertValue(round(evaluateBigMath(mathContext, values), mathContext));
    } catch (ArithmeticException e) {
      throw new EvaluationException(token, e.getMessage());
    }
  }

  protected abstract BigDecimal evaluateBigMath(MathContext mathContext, BigDecimal... parameters)
      throws EvaluationException;
}
