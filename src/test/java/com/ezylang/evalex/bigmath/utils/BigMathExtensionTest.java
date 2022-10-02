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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BigMathExtensionTest {

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "-90 : -1.5707963267948966192313216916397514420985846996875529104874722961539",
        "0 : 0",
        "90 : 1.5707963267948966192313216916397514420985846996875529104874722961539",
        "360 : 6.2831853071795864769252867665590057683943387987502116419498891846156",
        "145 : 2.530727415391777886539351614308488434492164238385501911340927588248"
      })
  void testToRadians(String degrees, String radians) {
    MathContext mathContext = new MathContext(68, RoundingMode.HALF_EVEN);

    BigDecimal deg = new BigDecimal(degrees, mathContext);
    Assertions.assertThat(
            BigMathExtension.toRadians(deg, mathContext).stripTrailingZeros().toPlainString())
        .isEqualTo(radians);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {"-90 : -1.5708", "0 : 0", "90 : 1.5708", "360 : 6.28319", "145 : 2.53073"})
  void testToRadiansLowScale(String degrees, String radians) {
    MathContext mathContext = new MathContext(6, RoundingMode.HALF_EVEN);

    BigDecimal deg = new BigDecimal(degrees, mathContext);
    Assertions.assertThat(
            BigMathExtension.toRadians(deg, mathContext).stripTrailingZeros().toPlainString())
        .isEqualTo(radians);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "-1.5707963267948966192313216916397514420985846996875529104874722961539 : -90",
        "0.00000000000000000000000000000000000000000000000000000000000000000000 : 0",
        "1.5707963267948966192313216916397514420985846996875529104874722961539 : 90",
        "6.2831853071795864769252867665590057683943387987502116419498891846156 : 360",
        "2.5307274153917778865393516143084884344921642383855019113409275882480 : 145"
      })
  void testToDegrees(String radians, String degrees) {
    MathContext mathContext = new MathContext(68, RoundingMode.HALF_EVEN);

    BigDecimal rad = new BigDecimal(radians, mathContext);
    Assertions.assertThat(
            BigMathExtension.toDegrees(rad, mathContext).stripTrailingZeros().toPlainString())
        .isEqualTo(degrees);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "-1.5708 : -90.0002",
        "0.00000 : 0",
        "1.5708 : 90.0002",
        "6.28319 : 360",
        "2.53073 : 145"
      })
  void testToDegreesLowScale(String radians, String degrees) {
    MathContext mathContext = new MathContext(6, RoundingMode.HALF_EVEN);

    BigDecimal rad = new BigDecimal(radians, mathContext);
    Assertions.assertThat(
            BigMathExtension.toDegrees(rad, mathContext).stripTrailingZeros().toPlainString())
        .isEqualTo(degrees);
  }
}
