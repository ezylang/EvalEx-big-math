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
package com.ezylang.evalex.bigmath.functions;

import static com.ezylang.evalex.bigmath.utils.BigMathExtension.toRadians;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.ezylang.evalex.functions.FunctionParameter;
import java.math.BigDecimal;
import java.math.MathContext;

/** Returns the co-secant of an angle (in degrees). */
@FunctionParameter(name = "x", nonZero = true)
public class BigMathCscFunction extends AbstractBigMathFunction {

  @Override
  protected BigDecimal evaluateBigMath(MathContext mathContext, BigDecimal... parameters) {
    /* Formula: csc(x) = 1 / sin(x) */
    return BigDecimal.ONE.divide(
        BigDecimalMath.sin(toRadians(parameters[0], mathContext), mathContext), mathContext);
  }
}
