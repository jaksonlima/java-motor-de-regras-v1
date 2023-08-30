package com.rules;

public interface EngineExecute<T> {

    T execute(T anIn);

    default boolean skippedFailure() {
        return false;
    }

}
