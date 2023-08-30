package com.rules;

import java.util.List;
import java.util.concurrent.Executors;

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

        Executors.newVirtualThreadPerTaskExecutor();

        final var contextDefaultEngine = DefaultEngine.create(engineRules);

        Context contextRoot = new Context("INIT=");

        final var context = contextDefaultEngine.executeRules(contextRoot);

        System.out.println(context);
        System.out.println(contextDefaultEngine.percentage());
        System.out.println(contextDefaultEngine.order());
        System.out.println(contextDefaultEngine.status());
    }

}