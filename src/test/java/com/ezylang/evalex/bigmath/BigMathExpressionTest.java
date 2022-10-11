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
package com.ezylang.evalex.bigmath;

import com.ezylang.evalex.bigmath.functions.bigdecimalmath.BigMathFunctions;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.functions.FunctionIfc;
import java.util.Map.Entry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BigMathExpressionTest {

  @Test
  void testDefaultConstructor() {
    BigMathExpression expression = new BigMathExpression("SIN(X)");
    assertAllFunctionsInConfiguration(expression);
  }

  @Test
  void testConfigurationConstructor() {
    BigMathExpression expression =
        new BigMathExpression("SIN(X)", ExpressionConfiguration.builder().build());
    assertAllFunctionsInConfiguration(expression);
  }

  private void assertAllFunctionsInConfiguration(BigMathExpression expression) {
    for (Entry<String, FunctionIfc> entry : BigMathFunctions.allFunctions()) {
      FunctionIfc function =
          expression.getConfiguration().getFunctionDictionary().getFunction(entry.getKey());
      Assertions.assertThat(function).isEqualTo(entry.getValue());
    }
  }
}
