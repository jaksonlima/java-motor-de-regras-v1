package com.rules;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.lang.String.valueOf;

public class DefaultEngineRuleCalculator implements EngineRuleCalculator {

    private final Integer totalRules;
    private final Map<Integer, String> order;
    private final Map<Integer, String> status;
    private Integer totalExecution;

    public DefaultEngineRuleCalculator(final Integer totalRules) {
        this.totalRules = totalRules;
        this.order = new HashMap<>();
        this.status = new HashMap<>();
        this.totalExecution = 0;
    }

    @Override
    public Map<Integer, String> order() {
        return this.order;
    }

    @Override
    public Map<Integer, String> status() {
        return this.status;
    }

    @Override
    public String percentage() {
        return valueOf((totalExecution * 100) / totalRules)
                .concat("%");
    }

    @Override
    public <T> T process(final String aRuleName, final Supplier<T> aSupplier) {
        try {
            T result = aSupplier.get();

            processSuccess(aRuleName);

            return result;
        } catch (final Throwable e) {

            processError(aRuleName, e);

            throw e;
        }
    }

    private void processSuccess(final String aRuleName) {
        this.processOrderExecute(aRuleName);
        this.processPercentage();
    }

    private void processOrderExecute(final String aRuleName) {
        final var anOrderSize = this.order().size();

        this.order().put(anOrderSize, aRuleName);
    }

    private void processPercentage() {
        totalExecution = Math.addExact(totalExecution, 1);
    }

    private void processError(final String aRuleName, final Throwable e) {
        int anStatusSize = this.status().size();

        this.status().put(anStatusSize, "%s=%s".formatted(aRuleName, e.getMessage()));
    }

}
