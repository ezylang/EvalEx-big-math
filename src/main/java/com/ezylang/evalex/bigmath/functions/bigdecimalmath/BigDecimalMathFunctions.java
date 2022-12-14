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

import com.ezylang.evalex.functions.FunctionIfc;
import java.util.Map;
import java.util.Map.Entry;

/** Collects all the functions. */
public class BigDecimalMathFunctions {

  private BigDecimalMathFunctions() {
    // no instantiation
  }

  public static Map.Entry<String, FunctionIfc>[] allFunctions() {
    return ALL_FUNCTIONS;
  }

  @SuppressWarnings("unchecked")
  protected static final Map.Entry<String, FunctionIfc>[] ALL_FUNCTIONS =
      new Entry[] {
        Map.entry("ACOS", new BigMathAcosFunction()),
        Map.entry("ACOSH", new BigMathAcosHFunction()),
        Map.entry("ACOSR", new BigMathAcosRFunction()),
        Map.entry("ACOT", new BigMathAcotFunction()),
        Map.entry("ACOTH", new BigMathAcotHFunction()),
        Map.entry("ACOTR", new BigMathAcotRFunction()),
        Map.entry("ASIN", new BigMathAsinFunction()),
        Map.entry("ASINH", new BigMathAsinHFunction()),
        Map.entry("ASINR", new BigMathAsinRFunction()),
        Map.entry("ATAN2", new BigMathAtan2Function()),
        Map.entry("ATAN2R", new BigMathAtan2RFunction()),
        Map.entry("ATAN", new BigMathAtanFunction()),
        Map.entry("ATANH", new BigMathAtanHFunction()),
        Map.entry("ATANR", new BigMathAtanRFunction()),
        Map.entry("BN", new BigMathBernoulliFunction()),
        Map.entry("COS", new BigMathCosFunction()),
        Map.entry("COSH", new BigMathCosHFunction()),
        Map.entry("COSR", new BigMathCosRFunction()),
        Map.entry("COT", new BigMathCotFunction()),
        Map.entry("COTH", new BigMathCotHFunction()),
        Map.entry("COTR", new BigMathCotRFunction()),
        Map.entry("CSC", new BigMathCscFunction()),
        Map.entry("CSCH", new BigMathCscHFunction()),
        Map.entry("CSCR", new BigMathCscRFunction()),
        Map.entry("DEG", new BigMathDegFunction()),
        Map.entry("E", new BigMathEFunction()),
        Map.entry("EXP", new BigMathExpFunction()),
        Map.entry("EXPONENT", new BigMathExponentFunction()),
        Map.entry("FACT", new BigMathFactorialFunction()),
        Map.entry("FRACTIONALPART", new BigMathFractionalPartFunction()),
        Map.entry("GAMMA", new BigMathGammaFunction()),
        Map.entry("INTEGRALPART", new BigMathIntegralPartFunction()),
        Map.entry("LOG", new BigMathLogFunction()),
        Map.entry("LOG10", new BigMathLog10Function()),
        Map.entry("LOG2", new BigMathLog2Function()),
        Map.entry("MANTISSA", new BigMathMantissaFunction()),
        Map.entry("PI", new BigMathPiFunction()),
        Map.entry("RAD", new BigMathRadFunction()),
        Map.entry("RECIPROCAL", new BigMathReciprocalFunction()),
        Map.entry("ROOT", new BigMathRootFunction()),
        Map.entry("SEC", new BigMathSecFunction()),
        Map.entry("SECH", new BigMathSecHFunction()),
        Map.entry("SECR", new BigMathSecRFunction()),
        Map.entry("SIGNIFICANTDIGITS", new BigMathSignificantDigitsFunction()),
        Map.entry("SIN", new BigMathSinFunction()),
        Map.entry("SINH", new BigMathSinHFunction()),
        Map.entry("SINR", new BigMathSinRFunction()),
        Map.entry("SQRT", new BigMathSqrtFunction()),
        Map.entry("TAN", new BigMathTanFunction()),
        Map.entry("TANH", new BigMathTanHFunction()),
        Map.entry("TANR", new BigMathTanRFunction())
      };
}
