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

import ch.obermuhlner.math.big.BigDecimalMath;
import com.ezylang.evalex.functions.FunctionParameter;
import java.math.BigDecimal;
import java.math.MathContext;

/** Returns the hyperbolic co-secant of a value. */
@FunctionParameter(name = "x", nonZero = true)
public class BigMathCscHFunction extends AbstractBigMathFunction {

  @Override
  protected BigDecimal evaluateBigMath(MathContext mathContext, BigDecimal... parameters) {
    /* Formula: csch(x) = 1 / sinh(x) */
    return BigDecimal.ONE.divide(BigDecimalMath.sinh(parameters[0], mathContext), mathContext);
  }
}
