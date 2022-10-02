EvalEx-big-math - an EvalEx extension to use the big-math project for calculations
==========

[EvalEx](https://github.com/ezylang/EvalEx) is a handy expression evaluator for Java, that allows to
evaluate simple mathematical and boolean expressions.

[Big-math](https://github.com/eobermuhlner/big-math) is a library by Eric Obermühlner and provides
advanced Java BigDecimal math functions (pow, sqrt, log, sin, ...) using an arbitrary precision.

EvalEx-big-math adds the advanced math functions from big-math to EvalEx.

## How to use it

The easiest way is to use the `BigMathExpression` class, which is the same as the `Expression`
class, but has all the new functions configured:

```java
BigMathExpression expression = new BigMathExpression("SIN(x)");
EvaluationValue result = expression.with("x", 10).evaluate(); 

ExpressionConfiguration configuration = ExpressionConfiguration.builder()
    .decimalPlacesRounding(2)
    .build();

BigMathExpression expression = new BigMathExpression("SIN(x)", configuration);
EvaluationValue result = expression.with("x", 10).evaluate(); 
```

Alternatively, create a new EvalEx configuration and simply add all the functions, existing
functions will be overridden:

```java
ExpressionConfiguration configuration =
    ExpressionConfiguration.defaultConfiguration()
        .withAdditionalFunctions(BigMathFunctions.allFunctions());
        
Expression expression = new Expression("SIN(x)", configuration); 
```

You may choose only to add the functions you need by specifying them separately:

```java
ExpressionConfiguration configuration =
    ExpressionConfiguration.defaultConfiguration()
        .withAdditionalFunctions(
        Map.entry("SIN", new BigMathSinFunction()),
        Map.entry("COS", new BigMathCosFunction()));
        
Expression expression = new Expression("SIN(x) + COS(y)", configuration); 
```

Instead of overriding the existing functions, you can also add them with new names:

```java
ExpressionConfiguration configuration =
    ExpressionConfiguration.defaultConfiguration()
        .withAdditionalFunctions(
        Map.entry("BIG_SIN", new BigMathSinFunction()),
        Map.entry("BIG_COS", new BigMathCosFunction()));
        
Expression expression = new Expression("BIG_SIN(x) + BIG_COS(y)", configuration); 
```

## New Functions

| Name         | Description                                              |
|--------------|----------------------------------------------------------|
| ACOTH(value) | Returns the the hyperbolic arc-co-tangent (in radians)   |
| BN(value)    | Calculates the Bernoulli number for the specified index. |
| EXP(value)   | Calculates the natural exponent of x (e<sup>x</sup>).    |

## Overridden Basic Functions

| Name         | Description                                                                                    |
|--------------|------------------------------------------------------------------------------------------------|
| LOG(value)                                 | The natural logarithm (base e) of a value                                                                               |

## Overridden Trigonometric Functions

| Name         | Description                                                                                    |
|--------------|------------------------------------------------------------------------------------------------|
| ACOS(value)  | Returns the the arc-cosine (in degrees)                                                        |
| ACOSH(value) | Returns the the hyperbolic arc-cosine (in degrees)                                             |
| ACOSR(value) | Returns the the arc-cosine (in radians)                                                        |
| ACOT(value)  | Returns the the arc-co-tangent (in degrees)                                                    |
| ACOTR(value) | Returns the the arc-co-tangent (in radians)                                                    |
| ASIN(value)  | Returns the the arc-sine (in degrees)                                                          |
| ASINH(value) | Returns the hyperbolic arc-sine (in degrees)                                                   |
| ASINR(value) | Returns the arc-sine (in radians)                                                              |
| ATAN2(y, x)  | Returns the angle of atan2 (in degrees)                                                        |
| ATAN2R(y, x) | Returns the angle of atan2 (in radians)                                                        |
| ATAN(value)  | Returns the arc-tangent (in degrees)                                                           |
| ATANH(value) | Returns the hyperbolic arc-tangent (in degrees)                                                |
| ATANR(value) | Returns the arc-tangent (in radians)                                                           |
| COS(value)   | Returns the cosine of an angle (in degrees)                                                    |
| COSH(value)  | Returns the hyperbolic cosine of a value                                                       |
| COSR(value)  | Returns the cosine of an angle (in radians)                                                    |
| COT(value)   | Returns the co-tangent of an angle (in degrees)                                                |
| COTH(value)  | Returns the hyperbolic co-tangent of a value                                                   |
| COTR(value)  | Returns the co-tangent of an angle (in radians)                                                |
| CSC(value)   | Returns the co-secant of an angle (in degrees)                                                 |
| CSCH(value)  | Returns the hyperbolic co-secant of a value                                                    |
| CSCR(value)  | Returns the co-secant of an angle (in radians)                                                 |
| DEG(rad)     | Converts an angle measured in radians to an approximately equivalent angle measured in degrees |
| RAD(degrees) | Converts an angle measured in degrees to an approximately equivalent angle measured in radians |
| SEC(value)   | Returns the secant of an angle (in degrees)                                                    |
| SECH(value)  | Returns the hyperbolic secant of an angle                                                      |
| SECR(value)  | Returns the secant of an angle (in radians)                                                    |
| SIN(value)   | Returns the sine of an angle (in degrees)                                                      |
| SINH(value)  | Returns the hyperbolic sine of a value                                                         |
| SINR(value)  | Returns the sine of an angle (in radians)                                                      |
| TAN(value)   | Returns the tangent of an angle (in degrees)                                                   |
| TANH(value)  | Returns the hyperbolic tangent of a value                                                      |
| TANR(value)  | Returns the tangent of an angle (in radians)                                                   |