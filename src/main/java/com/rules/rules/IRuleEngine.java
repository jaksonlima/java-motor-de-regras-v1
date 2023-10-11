package com.rules.rules;

import java.util.Optional;

public interface IRuleEngine<IN extends IRuleInput, OUT extends IRuleOutput> {

    Optional<OUT> executeRules(IN anIn);

    RuleTuple<? extends IRuleEngine<IN, OUT>, Optional<OUT>> executeTupleRules(IN anIn);

}
