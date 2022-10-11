EvalEx-big-math - an EvalEx extension to use the big-math project for calculations
==========

[![Build](https://github.com/ezylang/EvalEx-big-math/actions/workflows/build.yml/badge.svg)](https://github.com/ezylang/EvalEx-big-math/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ezylang_EvalEx-big-math&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ezylang_EvalEx-big-math)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ezylang_EvalEx-big-math&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ezylang_EvalEx-big-math)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ezylang_EvalEx-big-math&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=ezylang_EvalEx-big-math)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ezylang_EvalEx-big-math&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ezylang_EvalEx-big-math)

[EvalEx](https://github.com/ezylang/EvalEx) is a handy expression evaluator for Java. It allows to
evaluate mathematical and boolean expressions.

[Big-math](https://github.com/eobermuhlner/big-math) is a library by Eric Obermühlner. It provides
advanced Java BigDecimal math functions using an arbitrary precision.

EvalEx-big-math adds the advanced math functions from big-math to EvalEx.

## How to use it

The easiest way is to use the `BigMathExpression` class, which is the same as the `Expression`
class, but has all the new functions and operators configured:

```java
BigMathExpression expression = new BigMathExpression("SIN(x)");
EvaluationValue result = expression.with("x", 10).evaluate(); 

ExpressionConfiguration configuration = ExpressionConfiguration.builder()
    .decimalPlacesRounding(2)
    .build();

BigMathExpression expression = new BigMathExpression("SIN(x)", configuration);
EvaluationValue result = expression.with("x", 10).evaluate(); 
```

Alternatively, create a new EvalEx configuration and simply add all the functions and operators,
existing functions and operators will be overridden:

```java
ExpressionConfiguration configuration =
    ExpressionConfiguration.defaultConfiguration()
        .withAdditionalFunctions(BigMathFunctions.allFunctions())
        .withAdditionalOperators(BigDecimalMathOperators.allOperators());
        
Expression expression = new Expression("SIN(x) ^ COS(y)", configuration); 
```

You may choose only to add the functions and operators you need by specifying them separately:

```java
ExpressionConfiguration configuration =
    ExpressionConfiguration.defaultConfiguration()
        .withAdditionalFunctions(
            Map.entry("SIN", new BigMathSinFunction()),
            Map.entry("COS", new BigMathCosFunction()))
        .withAdditionalOperators(
            Map.entry("^", new BigMathInfixPowerOfOperator()));

        
Expression expression = new Expression("SIN(x) ^ COS(y)", configuration); 
```

Instead of overriding the existing functions and operators, you can also add them with new names:

```java
ExpressionConfiguration configuration =
    ExpressionConfiguration.defaultConfiguration()
        .withAdditionalFunctions(
            Map.entry("BIG_SIN", new BigMathSinFunction()),
            Map.entry("BIG_COS", new BigMathCosFunction()))
        .withAdditionalOperators(
            Map.entry("^", new BigMathInfixPowerOfOperator()));
        
Expression expression = new Expression("BIG_SIN(x) ^ BIG_COS(y)", configuration); 
```

## Discussion

For announcements, questions and ideas visit
the [Discussions area](https://github.com/ezylang/EvalEx-big-math/discussions).

## Download / Including

You can download the binaries, source code and JavaDoc jars from
[Maven Central](https://search.maven.org/search?q=a:%22EvalEx-big-math%22).\
You will find there also copy/paste templates for including EvalEx in your project with build
systems like Maven or Gradle.

| :warning: **Attention:** You have to add EvalEx and EvalEx-big-math dependencies to your project |
|--------------------------------------------------------------------------------------------------|

## New Functions

| Name                 | Description                                                                                         |
|----------------------|-----------------------------------------------------------------------------------------------------|
| BN(x)                | Calculates the Bernoulli number for the specified index.                                            |
| E()                  | Returns the number e with the configured  precision. The value is cached                            |
| EXP(x)               | Calculates the natural exponent of x (e<sup>x</sup>).                                               |
| EXPONENT(x)          | Returns the exponent of the specified BigDecimal written as <i>mantissa * 10<sup>exponent</sup></i> |
| FRACTIONALPART(x)    | Returns the fractional part of x (right of the decimal point).                                      |
| GAMMA(x)             | Calculates the gamma function of x.                                                                 |
| INTEGRALPART(x)      | Returns the integral part of x (left of the decimal point).                                         |
| LOG2(x)              | The natural logarithm (base e) of x to te base of 2.                                                |
| MANTISSA(x)          | Returns the mantissa of the specified BigDecimal written as <i>mantissa * 10<sup>exponent</sup></i> |
| PI()                 | Returns the number PI with the configured  precision. The value is cached.                          |
| RECIPROCAL(x)        | Returns the reciprocal of x.                                                                        |
| ROOT(x, n)           | Calculates the nth root of x.                                                                       |
| SIGNIFICANTDIGITS(x) | Returns the number of significant digits of x.                                                      |

## Overridden Basic Functions

| Name     | Description                                           |
|----------|-------------------------------------------------------|
| FACT(x)  | Calculates the factorial of x.                        |
| LOG(x)   | The natural logarithm (base e) of x.                  |
| LOG10(x) | The natural logarithm (base e) of x to te base of 10. |

## Overridden Basic perators

| Name | Description              |
|------|--------------------------|
| ^    | The power-of operator.   |

## Overridden Trigonometric Functions

| Name         | Description                                                                                    |
|--------------|------------------------------------------------------------------------------------------------|
| ACOS(x)      | Returns the the arc-cosine (in degrees)                                                        |
| ACOSH(x)     | Returns the the hyperbolic arc-cosine (in degrees)                                             |
| ACOSR(x)     | Returns the the arc-cosine (in radians)                                                        |
| ACOT(x)      | Returns the the arc-co-tangent (in degrees)                                                    |
| ACOTH(x)     | Returns the the hyperbolic arc-co-tangent (in radians)                                         |
| ACOTR(x)     | Returns the the arc-co-tangent (in radians)                                                    |
| ASIN(x)      | Returns the the arc-sine (in degrees)                                                          |
| ASINH(x)     | Returns the hyperbolic arc-sine (in degrees)                                                   |
| ASINR(x)     | Returns the arc-sine (in radians)                                                              |
| ATAN2(y, x)  | Returns the angle of atan2 (in degrees)                                                        |
| ATAN2R(y, x) | Returns the angle of atan2 (in radians)                                                        |
| ATAN(x)      | Returns the arc-tangent (in degrees)                                                           |
| ATANH(x)     | Returns the hyperbolic arc-tangent (in degrees)                                                |
| ATANR(x)     | Returns the arc-tangent (in radians)                                                           |
| COS(x)       | Returns the cosine of an angle (in degrees)                                                    |
| COSH(x)      | Returns the hyperbolic cosine of x                                                             |
| COSR(x)      | Returns the cosine of an angle (in radians)                                                    |
| COT(x)       | Returns the co-tangent of an angle (in degrees)                                                |
| COTH(x)      | Returns the hyperbolic co-tangent of x                                                         |
| COTR(x)      | Returns the co-tangent of an angle (in radians)                                                |
| CSC(x)       | Returns the co-secant of an angle (in degrees)                                                 |
| CSCH(x)      | Returns the hyperbolic co-secant of x                                                          |
| CSCR(x)      | Returns the co-secant of an angle (in radians)                                                 |
| DEG(rad)     | Converts an angle measured in radians to an approximately equivalent angle measured in degrees |
| RAD(degrees) | Converts an angle measured in degrees to an approximately equivalent angle measured in radians |
| SEC(x)       | Returns the secant of an angle (in degrees)                                                    |
| SECH(x)      | Returns the hyperbolic secant of an angle                                                      |
| SECR(x)      | Returns the secant of an angle (in radians)                                                    |
| SIN(x)       | Returns the sine of an angle (in degrees)                                                      |
| SINH(x)      | Returns the hyperbolic sine of x                                                               |
| SINR(x)      | Returns the sine of an angle (in radians)                                                      |
| TAN(x)       | Returns the tangent of an angle (in degrees)                                                   |
| TANH(x)      | Returns the hyperbolic tangent of x                                                            |
| TANR(x)      | Returns the tangent of an angle (in radians)                                                   |

## Author and License

Copyright 2012-2022 by Udo Klimaschewski

**Thanks to all who contributed to this
project: [Contributors](https://github.com/ezylang/EvalEx-big-math/graphs/contributors)**

The software is licensed under the Apache License, Version 2.0 (
see [LICENSE](https://raw.githubusercontent.com/ezylang/EvalEx-big-math/main/LICENSE) file).

[EvalEx](https://github.com/ezylang/EvalEx) is licensed under
the [Apache 2.0 License](https://github.com/ezylang/EvalEx/blob/main/LICENSE) and has a Copyright
2012-2022 by Udo Klimaschewski.

[Big-math](https://github.com/eobermuhlner/big-math) is licensed under
the [MIT License](https://raw.githubusercontent.com/eobermuhlner/big-math/master/LICENSE.txt) and
has a Copyright 2017 by Eric Obermühlner. 
