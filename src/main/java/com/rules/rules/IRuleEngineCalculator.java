package com.rules.rules;

import java.util.List;

public interface IRuleEngineCalculator {

    List<RuleEngineCalculatorInfo> executed();

    List<RuleEngineCalculatorError> failed();

    String percentageExecuted();

}
