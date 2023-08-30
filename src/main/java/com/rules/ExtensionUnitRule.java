package com.rules;

public class ExtensionUnitRule implements EngineExecute<Context> {

    @Override
    public Context execute(final Context anIn) {
        return new Context(anIn.message().concat(",ExtensionUnitRule"));
    }

}
