package com.rules.rules;

public interface IRuleInput {
    default String simpleName() {
        return this.getClass().getSimpleName();
    }
}
