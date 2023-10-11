package com.rules.rules;

import java.util.Optional;

public interface IRule<IN extends IRuleInput, OUT extends IRuleOutput> {

    OUT execute(IN anIn, Optional<OUT> aOut);

    default boolean isExecute(IN anIn, Optional<OUT> aOut) {
        return true;
    }

    default String ruleName() {
        return this.getClass().getSimpleName();
    }

}
