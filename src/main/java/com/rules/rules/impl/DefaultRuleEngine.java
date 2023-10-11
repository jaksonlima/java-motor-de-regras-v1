package com.rules.rules.impl;

import com.rules.rules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

public class DefaultRuleEngine<IN extends IRuleInput, OUT extends IRuleOutput>
        implements IRuleEngine<IN, OUT>, IRuleEngineCalculator {

    private final List<IRule<IN, OUT>> rules;
    private final List<RuleEngineCalculatorInfo> executed;
    private final List<RuleEngineCalculatorError> failed;
    private final Integer totalRules;
    private Integer totalExecution;
    private boolean skippedFailure;

    private DefaultRuleEngine(final List<IRule<IN, OUT>> rules) {
        this.rules = rules;
        this.executed = new ArrayList<>();
        this.failed = new ArrayList<>();
        this.totalRules = rules.size();
        this.totalExecution = 0;
        this.skippedFailure = false;
    }

    public static <IN extends IRuleInput, OUT extends IRuleOutput> DefaultRuleEngine<IN, OUT> registerRules(
            final List<IRule<IN, OUT>> rules
    ) {
        return new DefaultRuleEngine<>(rules);
    }

    @Override
    public Optional<OUT> executeRules(final IN anIn) {

        final var resultRules = rules.stream()
                .reduce(Optional.<OUT>empty(), (output, rule) -> {
                    try {

                        if (rule.isExecute(anIn, output)) {
                            final OUT execute = rule.execute(anIn, output);

                            processSuccess(rule, anIn, execute);

                            return Optional.ofNullable(execute);
                        }

                        return output;

                    } catch (final Throwable e) {

                        processError(rule, anIn, output.orElse(null), e);

                        if (skippedFailure) return output;

                        throw e;
                    }
                }, (outState, outOtherState) -> outState);

        return resultRules;
    }

    @Override
    public RuleTuple<DefaultRuleEngine<IN, OUT>, Optional<OUT>> executeTupleRules(IN anIn) {
        return RuleTuple.with(this, executeRules(anIn));
    }

    @Override
    public List<RuleEngineCalculatorInfo> executed() {
        return this.executed;
    }

    @Override
    public List<RuleEngineCalculatorError> failed() {
        return this.failed;
    }

    @Override
    public String percentageExecuted() {
        return valueOf((totalExecution / totalRules) * 100)
                .concat("%");
    }

    public DefaultRuleEngine<IN, OUT> activeSkippedFailure() {
        this.skippedFailure = true;
        return this;
    }

    private void processSuccess(
            final IRule<IN, OUT> aRule,
            final IN anIn,
            final OUT anOut
    ) {
        this.processPercentage();

        final var anCalculatorInfo = new RuleEngineCalculatorInfo(
                this.executed().size(),
                aRule.ruleName(),
                anIn,
                anOut
        );

        this.executed().add(anCalculatorInfo);
    }

    private void processError(
            final IRule<IN, OUT> aRule,
            final IN anIn,
            final OUT anOut,
            final Throwable e
    ) {
        final var anCalculatorError = new RuleEngineCalculatorError(
                this.failed().size(),
                aRule.ruleName(),
                anIn,
                anOut,
                e.getMessage()
        );

        this.failed().add(anCalculatorError);
    }

    private void processPercentage() {
        totalExecution = Math.addExact(totalExecution, 1);
    }

}
