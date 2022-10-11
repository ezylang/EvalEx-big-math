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

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.MathContext;

/** Returns the number e with the configured {@link MathContext} precision. The value is cached. */
public class BigMathEFunction extends AbstractBigMathFunction {

  @Override
  protected BigDecimal evaluateBigMath(MathContext mathContext, BigDecimal... parameters) {
    return BigDecimalMath.e(mathContext);
  }
}
