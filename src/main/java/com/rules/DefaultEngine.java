package com.rules;

import java.util.List;

public class DefaultEngine<T> extends DefaultEngineRuleCalculator implements EngineRule<T> {

    private final List<EngineExecute<T>> rules;

    private DefaultEngine(final List<EngineExecute<T>> rules) {
        super(rules.size());
        this.rules = rules;
    }

    public static <T> DefaultEngine<T> create(final List<EngineExecute<T>> rules) {
        return new DefaultEngine<T>(rules);
    }

    @Override
    public T executeRules(T anIn) {

        T resultReduce = rules.stream().reduce(anIn, (input, rule) -> {
            try {

                return process(rule.getClass().getSimpleName(), () -> rule.execute(input));

            } catch (final Throwable e) {

                if (rule.skippedFailure()) return input;

                throw e;
            }

        }, (state, otherState) -> state);

        return resultReduce;
    }

}
