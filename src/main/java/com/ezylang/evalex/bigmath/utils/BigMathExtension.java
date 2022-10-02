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
package com.ezylang.evalex.bigmath.utils;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.MathContext;

public class BigMathExtension {

  private BigMathExtension() {
    // No instantiation
  }

  /**
   * Rounds value according to the math context.
   *
   * <p>Fixes a bug in {@link BigDecimal#round(MathContext)}
   *
   * @param value The value to round.
   * @param mathContext The {@link MathContext} to use.
   * @return The rounded value.
   * @see "https://github.com/eobermuhlner/big-math/issues/58"
   */
  public static BigDecimal round(BigDecimal value, MathContext mathContext) {
    if (value.abs().compareTo(ONE.scaleByPowerOfTen(-mathContext.getPrecision())) < 0) {
      return ZERO;
    }
    return value.round(mathContext);
  }

  /**
   * Converts from degrees to radians using a cached PI constant of correct scale.
   *
   * @param degrees An angle in degrees.
   * @param mathContext The {@link MathContext} to use.
   * @return An angle in radians.
   */
  public static BigDecimal toRadians(BigDecimal degrees, MathContext mathContext) {
    MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());

    // Radians = Degrees * (π / 180)
    BigDecimal factor = BigDecimalMath.pi(mc).divide(BigDecimal.valueOf(180), mc);
    return round(degrees.multiply(factor, mc), mathContext);
  }

  /**
   * Converts from radians to degrees using a cached PI constant of correct scale.
   *
   * @param radians An angle in radians.
   * @param mathContext The {@link MathContext} to use.
   * @return An angle in degrees.
   */
  public static BigDecimal toDegrees(BigDecimal radians, MathContext mathContext) {
    MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());

    // Degrees = Radians * (180 / π).
    BigDecimal factor = BigDecimal.valueOf(180).divide(BigDecimalMath.pi(mc), mc);
    return round(radians.multiply(factor, mc), mathContext);
  }
}
