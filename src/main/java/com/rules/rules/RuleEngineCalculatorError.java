package com.rules.rules;

public record RuleEngineCalculatorError(
        Integer order,
        String ruleName,
        Object input,
        Object output,
        String errorMessage
) {
}
