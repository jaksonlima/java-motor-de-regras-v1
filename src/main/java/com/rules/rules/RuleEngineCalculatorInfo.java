package com.rules.rules;

public record RuleEngineCalculatorInfo(
        Integer order,
        String ruleName,
        Object input,
        Object output
) {
}
