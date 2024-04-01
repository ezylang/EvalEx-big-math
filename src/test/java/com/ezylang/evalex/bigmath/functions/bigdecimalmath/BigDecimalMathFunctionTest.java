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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.bigmath.BigMathExpression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;
import java.math.MathContext;
import java.math.RoundingMode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BigDecimalMathFunctionTest {

  ExpressionConfiguration configuration =
      ExpressionConfiguration.defaultConfiguration()
          .withAdditionalFunctions(BigDecimalMathFunctions.allFunctions());

  protected void assertExpressionHasExpectedResult(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertThat(evaluate(expression, configuration).getStringValue()).isEqualTo(expectedResult);
  }

  private EvaluationValue evaluate(String expressionString, ExpressionConfiguration configuration)
      throws EvaluationException, ParseException {
    Expression expression = new Expression(expressionString, configuration);

    return expression.evaluate();
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ACOS(0) : 90",
        "ACOS(1) : 0",
        "ACOS(-1) : 180",
      })
  void testAcos(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testAcosHighException() {
    assertThatThrownBy(() -> evaluate("ACOS(1.1)", configuration))
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Illegal acos(x) for x > 1: x = 1.1");
  }

  @Test
  void testAcosLowException() {
    assertThatThrownBy(() -> evaluate("ACOS(-1.1)", configuration))
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Illegal acos(x) for x < -1: x = -1.1");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ACOSR(0) : 1.5707963267948966192313216916397514420985846996875529104874722961539",
        "ACOSR(1) : 0",
        "ACOSR(-1) : 3.1415926535897932384626433832795028841971693993751058209749445923078",
      })
  void testAcosR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ACOSH(1) : 0",
        "ACOSH(2) : 1.3169578969248167086250463473079684440269819714675164797684722569205",
        "ACOSH(3) : 1.7627471740390860504652186499595846180563206565232708215065912173068"
      })
  void testAcosh(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @ValueSource(strings = {"ACOSH(-1)", "ACOSH(0)", "ACOSH(0.5)", "ACOSH(0.9)"})
  void testAcoshException(String expression) {
    assertThatThrownBy(() -> evaluate(expression, configuration))
        .isInstanceOf(EvaluationException.class)
        .hasMessageContaining("for x");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ACOT(1) : 45",
        "ACOT(-1) : 135",
      })
  void testAcot(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ACOTH(-1.5) : -0.80471895621705018730037966661309381976280067713425886095632394573709",
        "ACOTH(1.5) : 0.80471895621705018730037966661309381976280067713425886095632394573709"
      })
  void testAcotH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ACOTR(1) : 0.78539816339744830961566084581987572104929234984377645524373614807695",
        "ACOTR(-1) : 2.3561944901923449288469825374596271631478770495313293657312084442309",
      })
  void testAcotR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testAcotRThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("ACOTR(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @Test
  void testAcotThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("ACOT(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ASIN(0) : 0",
        "ASIN(1) : 90",
        "ASIN(-1) : -90",
      })
  void testAsin(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ASINH(0) : 0",
        "ASINH(1) : 0.88137358701954302523260932497979230902816032826163541075329560865338",
        "ASINH(-1) : -0.88137358701954302523260932497979230902816032826163541075329560865338",
      })
  void testAsinH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ASINR(0) : 0",
        "ASINR(1) : 1.5707963267948966192313216916397514420985846996875529104874722961539",
        "ASINR(-1) : -1.5707963267948966192313216916397514420985846996875529104874722961539",
      })
  void testAsinR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ATAN(0) : 0",
        "ATAN(1) : 45",
        "ATAN(-1) : -45",
      })
  void testAtan(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ATAN2(0,1) : 0",
        "ATAN2(0,-1) : 180",
        "ATAN2(1,0) : 90",
        "ATAN2(1,1) : 45",
        "ATAN2(1,-1) : 135",
        "ATAN2(-1,0) : -90",
        "ATAN2(-1,1) : -45",
        "ATAN2(-1,-1) : -135",
      })
  void testAtan2(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ATAN2R(0,1) : 0",
        "ATAN2R(0,-1) : 3.1415926535897932384626433832795028841971693993751058209749445923078",
        "ATAN2R(1,0) : 1.5707963267948966192313216916397514420985846996875529104874722961539",
        "ATAN2R(1,1) : 0.78539816339744830961566084581987572104929234984377645524373614807695",
        "ATAN2R(1,-1) : 2.3561944901923449288469825374596271631478770495313293657312084442309",
        "ATAN2R(-1,0) : -1.5707963267948966192313216916397514420985846996875529104874722961539",
        "ATAN2R(-1,1) : -0.78539816339744830961566084581987572104929234984377645524373614807695",
        "ATAN2R(-1,-1) : -2.3561944901923449288469825374596271631478770495313293657312084442309",
      })
  void testAtan2R(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testAtan2RThrowsException() {
    Assertions.assertThatThrownBy(
            () -> new BigMathExpression("ATAN2R(0,0)", configuration).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Illegal atan2(y, x) for x = 0; y = 0");
  }

  @Test
  void testAtan2ThrowsException() {
    Assertions.assertThatThrownBy(
            () -> new BigMathExpression("ATAN2(0,0)", configuration).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Illegal atan2(y, x) for x = 0; y = 0");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ATANH(0) : 0",
        "ATANH(0.9) : 1.4722194895832202300045137159439267686186896306495644092689801182046",
        "ATANH(-0.9) : -1.4722194895832202300045137159439267686186896306495644092689801182046",
      })
  void testAtanH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @ValueSource(doubles = {-1.1, -1.0, 1.0, 1.1})
  void testAtanHThrowsException(double d) {
    Assertions.assertThatThrownBy(
            () -> new Expression("ATANH(x)", configuration).with("x", d).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessageStartingWith("Illegal atanh(x) for x");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ATANR(0) : 0",
        "ATANR(1) : 0.78539816339744830961566084581987572104929234984377645524373614807695",
        "ATANR(-1) : -0.78539816339744830961566084581987572104929234984377645524373614807695",
      })
  void testAtanR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "BN(0) : 1",
        "BN(0.5) : 1",
        "BN(0.9) : 1",
        "BN(1) : -0.5",
        "BN(1.2) : -0.5",
        "BN(2) : 0.16666666666666666666666666666666666666666666666666666666666666666667",
        "BN(3) : 0"
      })
  void testBernoulli(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @ValueSource(strings = {"BN(-1)", "BN(-0.5)"})
  void testBernoulliException(String expression) {
    assertThatThrownBy(() -> evaluate(expression, configuration))
        .isInstanceOf(EvaluationException.class)
        .hasMessageContaining("Parameter must not be negative");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "CSC(1) : 57.298688498550183476612683735173779889969877177276091549613256621128",
        "CSC(19) : 3.0715534867572423567483210381945563571489806493259975195281774660734",
        "CSC(-19) : -3.0715534867572423567483210381945563571489806493259975195281774660734"
      })
  void testCSC(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "CSCH(1) : 0.85091812823932154513384276328717528418172466091033961699042115172901",
        "CSCH(19) : 0.000000011205592875074535431784406117275266992907327316678712183509168050644",
        "CSCH(-19) : -0.000000011205592875074535431784406117275266992907327316678712183509168050644"
      })
  void testCSCH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "CSCR(1) : 1.1883951057781212162615994523745510035278298340979626252652536663592",
        "CSCR(19) : 6.6721284860375060477794840958751656176410259023522778817189490916002",
        "CSCR(-19) : -6.6721284860375060477794840958751656176410259023522778817189490916002"
      })
  void testCSCR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "COT(1) : 57.28996163075942468727814753711257798021752223514392647258110360653",
        "COT(19) : 2.9042108776758228025793255345270912540312619218462216683630247113938",
        "COT(-19) : -2.9042108776758228025793255345270912540312619218462216683630247113938"
      })
  void testCoTan(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "COS(0) : 1",
        "COS(1) : 0.99984769515639123915701155881391485169274031058318593965832071451154",
        "COS(19) : 0.9455185755993168103481247075194031776764587259189527030101408209676",
        "COS(-19) : 0.9455185755993168103481247075194031776764587259189527030101408209676"
      })
  void testCos(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "COSH(0) : 1",
        "COSH(1) : 1.5430806348152437784779056207570616826015291123658637047374022147108",
        "COSH(-1) : 1.5430806348152437784779056207570616826015291123658637047374022147108",
      })
  void testCosH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "COSR(0) : 1",
        "COSR(1) : 0.5403023058681397174009366074429766037323104206179222276700972553811",
        "COSR(19) : 0.9887046181866692528983528565481572681733945559567684649703787304743",
        "COSR(-19) : 0.9887046181866692528983528565481572681733945559567684649703787304743"
      })
  void testCosR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "COTH(1) : 1.3130352854993313036361612469308478329120139412404526555431529675671",
        "COTH(5) : 1.0000908039820193755366579205216876038305474375218907541594086559576",
        "COTH(-5) : -1.0000908039820193755366579205216876038305474375218907541594086559576"
      })
  void testCotH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testCotHThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("COTH(0)", configuration).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "COTR(1) : 0.64209261593433070300641998659426562023027811391817137910116228042628",
        "COTR(19) : 6.5967642472801119903729439538219289597475962396777740388404820143114",
        "COTR(-19) : -6.5967642472801119903729439538219289597475962396777740388404820143114"
      })
  void testCotR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testCotRThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("COTR(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @Test
  void testCotThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("COT(0)", configuration).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @Test
  void testCscHThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("CSCH(0)", configuration).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @Test
  void testCscRThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("CSCR(0)", configuration).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @Test
  void testCscThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("CSC(0)", configuration).evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "DEG(0) : 0",
        "DEG(1) : 57.295779513082320876798154814105170332405472466564321549160243861203",
        "DEG(90) : 5156.6201561774088789118339332694653299164925219907889394244219475083",
        "DEG(-90) : -5156.6201561774088789118339332694653299164925219907889394244219475083"
      })
  void testDeg(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testE() throws EvaluationException, ParseException {
    assertThat(evaluate("e()", configuration).getStringValue())
        .isEqualTo("2.7182818284590452353602874713526624977572470936999595749669676277241");
  }

  @Test
  void testELowerPrecision() throws EvaluationException, ParseException {
    ExpressionConfiguration config =
        ExpressionConfiguration.builder()
            .mathContext(new MathContext(10, RoundingMode.HALF_EVEN))
            .build()
            .withAdditionalFunctions(BigDecimalMathFunctions.allFunctions());
    assertThat(evaluate("e()", config).getStringValue()).isEqualTo("2.718281828");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "EXP(0) : 1",
        "EXP(1) : 2.7182818284590452353602874713526624977572470936999595749669676277241",
        "EXP(2) : 7.3890560989306502272304274605750078131803155705518473240871278225226",
        "EXP(4) : 54.598150033144239078110261202860878402790737038614068725826593958554",
        "EXP(-1) : 0.36787944117144232159552377016146086744581113103176783450783680169746"
      })
  void testExp(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "EXPONENT(0) : 0",
        "EXPONENT(1) : 0",
        "EXPONENT(1234.42) : 3",
        "EXPONENT(-1234) : 3",
        "EXPONENT(49393993.939393) : 7"
      })
  void testExponent(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "FACT(-2.5) : 2.3632718012073547030642233111215269103967326081631828376184103864705",
        "FACT(0) : 1",
        "FACT(1) : 1",
        "FACT(5.7) : 413.4075167652706955633101100320104010646407873244548491658848029494",
        "FACT(10) : 3628800",
        "FACT(50) : 30414093201713378043612608166064768844377641568960512000000000000"
      })
  void testFactorial(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "FRACTIONALPART(0) : 0",
        "FRACTIONALPART(1) : 0",
        "FRACTIONALPART(1234.42) : 0.42",
        "FRACTIONALPART(-1234.92838) : -0.92838",
        "FRACTIONALPART(49393993.939393949492) : 0.939393949492"
      })
  void testFractionalPart(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "GAMMA(1) : 1",
        "GAMMA(10) : 362880",
        "GAMMA(34.8) : 145495640229953539293680410894926318036.54766085088369648260644951753"
      })
  void testGamma(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "INTEGRALPART(0) : 0",
        "INTEGRALPART(1) : 1",
        "INTEGRALPART(1234.42) : 1234",
        "INTEGRALPART(-1234.92838) : -1234",
        "INTEGRALPART(49393993.939393949492) :49393993"
      })
  void testIntegralPart(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "LOG(1) : 0",
        "LOG(10) : 2.3025850929940456840179914546843642076011014886287729760333279009676",
        "LOG(2.12345) : 0.7530421244614831003325675669738882329045292348677417031664109839051",
        "LOG(1567) : 7.3569182423560208279887799997255625975873700034398077118652945652125"
      })
  void testLog(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "LOG10(1) : 0",
        "LOG10(10) : 1",
        "LOG10(2.12345) : 0.32704203929432388276732686480064255538938448402407087683012738343244",
        "LOG10(1567) : 3.1950689964685901310045843028380378844870821067582126070296443075022"
      })
  void testLog10(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testLog10Negative() {
    assertThatThrownBy(() -> new BigMathExpression("LOG10(-1)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be negative");
  }

  @Test
  void testLog10Zero() {
    assertThatThrownBy(() -> new BigMathExpression("LOG10(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "LOG2(1) : 0",
        "LOG2(10) : 3.3219280948873623478703194294893901758648313930245806120547563958159",
        "LOG2(2.12345) : 1.0864101385410712327404540829104449213821624126500036929251012953616",
        "LOG2(1567) : 10.61378946447258027106930251500412913237266357264773607805987640923"
      })
  void testLog2(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testLog2Negative() {
    assertThatThrownBy(() -> new BigMathExpression("LOG2(-1)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be negative");
  }

  @Test
  void testLog2Zero() {
    assertThatThrownBy(() -> new BigMathExpression("LOG2(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "MANTISSA(0) : 0",
        "MANTISSA(1) : 1",
        "MANTISSA(1234.42) : 1.23442",
        "MANTISSA(-1234) : -1.234",
        "MANTISSA(49393993.939393) : 4.9393993939393"
      })
  void testMantissa(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testPi() throws EvaluationException, ParseException {
    assertThat(evaluate("pi()", configuration).getStringValue())
        .isEqualTo("3.1415926535897932384626433832795028841971693993751058209749445923078");
  }

  @Test
  void testPiLowerPrecision() throws EvaluationException, ParseException {
    ExpressionConfiguration config =
        ExpressionConfiguration.builder()
            .mathContext(new MathContext(10, RoundingMode.HALF_EVEN))
            .build()
            .withAdditionalFunctions(BigDecimalMathFunctions.allFunctions());
    assertThat(evaluate("pi()", config).getStringValue()).isEqualTo("3.141592654");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "RAD(0) : 0",
        "RAD(1) : 0.01745329251994329576923690768488612713442871888541725456097191440171",
        "RAD(45) : 0.78539816339744830961566084581987572104929234984377645524373614807695",
        "RAD(50) : 0.8726646259971647884618453842443063567214359442708627280485957200855",
        "RAD(90) : 1.5707963267948966192313216916397514420985846996875529104874722961539",
        "RAD(-90) : -1.5707963267948966192313216916397514420985846996875529104874722961539"
      })
  void testRad(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "RECIPROCAL(1) : 1",
        "RECIPROCAL(10) : 0.1",
        "RECIPROCAL(3) : 0.33333333333333333333333333333333333333333333333333333333333333333333",
        "RECIPROCAL(14.8) : 0.067567567567567567567567567567567567567567567567567567567567567567568"
      })
  void testReciprocal(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testReciprocalZero() {
    assertThatThrownBy(() -> new BigMathExpression("RECIPROCAL(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "ROOT(0, 1) : 0",
        "ROOT(0, 2) : 0",
        "ROOT(1, 1) : 1",
        "ROOT(1, 2) : 1",
        "ROOT(2, 2) : 1.4142135623730950488016887242096980785696718753769480731766797379907",
        "ROOT(6.8, 2.5) : 2.1527994067793906954203846880353543694065195606450150222981530863809"
      })
  void testRoot(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testRootNegative() {
    assertThatThrownBy(() -> new BigMathExpression("ROOT(-1, 1)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be negative");
  }

  @Test
  void testRootZero() {
    assertThatThrownBy(() -> new BigMathExpression("ROOT(2, 0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "SEC(1) : 1.000152328043907665428426434212573801478911804221445564714033783037",
        "SEC(19) : 1.0576206811866706550878639673129101886797137583689813190946914273507",
        "SEC(-19) : 1.0576206811866706550878639673129101886797137583689813190946914273507"
      })
  void testSec(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "SECH(1) : 0.6480542736638853995749773532261503231084893120719420230378653373187",
        "SECH(19) : 0.000000011205592875074534728267525147550607410557275795303491797481645783726",
        "SECH(-19) : 0.000000011205592875074534728267525147550607410557275795303491797481645783726"
      })
  void testSecH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testSecHThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("SECH(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "SECR(1) : 1.8508157176809256179117532413986501934703966550940092988351582778588",
        "SECR(19) : 1.0114244250563398887771625171798917686891765389075409305573477291315",
        "SECR(-19) : 1.0114244250563398887771625171798917686891765389075409305573477291315"
      })
  void testSecR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testSecRThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("SECR(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @Test
  void testSecThrowsException() {
    Assertions.assertThatThrownBy(() -> new BigMathExpression("SEC(0)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be zero");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "SIGNIFICANTDIGITS(-23.8) : 3",
        "SIGNIFICANTDIGITS(0) : 1",
        "SIGNIFICANTDIGITS(1) : 1",
        "SIGNIFICANTDIGITS(123) : 3",
        "SIGNIFICANTDIGITS(123.456) : 6"
      })
  void testSignificantDigits(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "SIN(0) : 0",
        "SIN(1) : 0.017452406437283512819418978516316192472252720307139642683612427640597",
        "SIN(90) : 1",
        "SIN(-90) : -1"
      })
  void testSin(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "SINH(0) : 0",
        "SINH(1) : 1.1752011936438014568823818505956008151557179813340958702295654130133",
        "SINH(-1) : -1.1752011936438014568823818505956008151557179813340958702295654130133",
      })
  void testSinH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "SINR(0) : 0",
        "SINR(1) : 0.84147098480789650665250232163029899962256306079837106567275170999191",
        "SINR(90) : 0.8939966636005578905182694984042098800464305236663045123522694151395",
        "SINR(-90) : -0.8939966636005578905182694984042098800464305236663045123522694151395"
      })
  void testSinR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "SQRT(0) : 0",
        "SQRT(1) : 1",
        "SQRT(2) : 1.4142135623730950488016887242096980785696718753769480731766797379907",
        "SQRT(6.8) : 2.6076809620810594858331886229716737666112375115640261835801587397935"
      })
  void testSqr(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @Test
  void testSqrNegative() {
    assertThatThrownBy(() -> new BigMathExpression("SQRT(-1)").evaluate())
        .isInstanceOf(EvaluationException.class)
        .hasMessage("Parameter must not be negative");
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "TAN(0) : 0",
        "TAN(1) : 0.017455064928217585765128895219727824314101588839875276904711427102105",
        "TAN(19) : 0.34432761328966524195726583938310886978495815130530290696244251911289",
        "TAN(-19) : -0.34432761328966524195726583938310886978495815130530290696244251911289"
      })
  void testTan(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "TANH(0) : 0",
        "TANH(1) : 0.76159415595576488811945828260479359041276859725793655159681050012195",
        "TANH(-1) : -0.76159415595576488811945828260479359041276859725793655159681050012195",
      })
  void testTanH(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {
        "TANR(0) : 0",
        "TANR(1) : 1.5574077246549022305069748074583601730872507723815200383839466056989",
        "TANR(19) : 0.1515894706124000692467340983330426302838871482096273371729680479204",
        "TANR(-19) : -0.1515894706124000692467340983330426302838871482096273371729680479204"
      })
  void testTanR(String expression, String expectedResult)
      throws EvaluationException, ParseException {
    assertExpressionHasExpectedResult(expression, expectedResult);
  }
}
