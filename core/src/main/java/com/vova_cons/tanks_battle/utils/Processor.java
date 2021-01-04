package com.vova_cons.tanks_battle.utils;

@FunctionalInterface
public interface Processor <T> {
    boolean process(T value);
}
