package com.rules;

import com.rules.command.ExtensionMobileRule;
import com.rules.command.ExtensionRule;
import com.rules.command.ExtensionUnitRule;
import com.rules.rules.impl.DefaultRuleEngine;
import com.rules.rules.impl.RuleInput;
import com.rules.rules.impl.RuleOutput;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        final var engineRules = List.of(
                new ExtensionRule(),
                new ExtensionRule(),
                new ExtensionUnitRule(),
                new ExtensionUnitRule(),
                new ExtensionMobileRule(),
                new ExtensionMobileRule()
        );

        final var contextRoot = new RuleInput("INIT=");

        Optional<RuleOutput> ruleOutput = DefaultRuleEngine
                .registerRules(engineRules)
                .activeSkippedFailure()
                .executeRules(contextRoot);

        final var tupleRules = DefaultRuleEngine
                .registerRules(engineRules)
                .activeSkippedFailure()
                .executeTupleRules(contextRoot);

        DefaultRuleEngine<RuleInput, RuleOutput> left = tupleRules.left();

        Optional<RuleOutput> right = tupleRules.right();

//        RuleTuple<DefaultRuleEngine<RuleInput, RuleOutput>, Optional<RuleOutput>> result =
//                DefaultRuleEngine
//                        .registerRules(engineRules)
//                        .executeRules(contextRoot);

//        final Optional<RuleOutput> context = contextDefaultEngine.executeRules(contextRoot);
//
//        System.out.println(context);
//        System.out.println(context6DefaultEngine.percentage());
//        System.out.println(contextDefaultEngine.order());
//        System.out.println(contextDefaultEngine.status());
        System.out.println("Result");
    }

}