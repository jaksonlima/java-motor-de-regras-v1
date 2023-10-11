package com.rules.command;

import com.rules.rules.IRule;
import com.rules.rules.impl.RuleInput;
import com.rules.rules.impl.RuleOutput;

import java.util.Optional;

public class ExtensionUnitRule implements IRule<RuleInput, RuleOutput> {

    @Override
    public RuleOutput execute(RuleInput anIn, Optional<RuleOutput> aOut) {
        throw new UnsupportedOperationException("UnsupportedOperationException");
    }

    @Override
    public boolean isExecute(RuleInput anIn, Optional<RuleOutput> aOut) {
        return IRule.super.isExecute(anIn, aOut);
    }

}
