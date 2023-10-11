package com.rules.rules;

public interface IRuleOutput {
    default String simpleName() {
        return this.getClass().getSimpleName();
    }
}
