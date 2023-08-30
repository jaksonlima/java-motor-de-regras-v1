package com.rules;

public class ExtensionMobileRule implements EngineExecute<Context> {

    @Override
    public Context execute(final Context anIn) {
        return new Context(anIn.message().concat(",ExtensionMobileRule"));
    }

}
