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

import com.ezylang.evalex.bigmath.utils.BigDecimalMathExtension;
import com.ezylang.evalex.functions.FunctionParameter;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Converts an angle measured in degrees to an approximately equivalent angle measured in radians.
 */
@FunctionParameter(name = "radians")
public class BigMathRadFunction extends AbstractBigMathFunction {

  @Override
  protected BigDecimal evaluateBigMath(MathContext mathContext, BigDecimal... parameters) {
    return BigDecimalMathExtension.toRadians(parameters[0], mathContext);
  }
}
