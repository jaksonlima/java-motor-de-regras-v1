package com.rules;

public class ExtensionRule implements EngineExecute<Context> {

    @Override
    public Context execute(final Context anIn) {
        throw new UnsupportedOperationException("Ero ao executar a sql no banco de dados");
    }

}
