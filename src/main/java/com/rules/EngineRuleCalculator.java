package com.rules;

import java.util.Map;
import java.util.function.Supplier;

public interface EngineRuleCalculator {

    Map<Integer, String> order();

    Map<Integer, String> status();

    String percentage();

    <T> T process(String aRuleName, Supplier<T> aSupplier);

}
