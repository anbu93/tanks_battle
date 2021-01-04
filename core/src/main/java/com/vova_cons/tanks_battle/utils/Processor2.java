package com.vova_cons.tanks_battle.utils;

@FunctionalInterface
public interface Processor2 <T, K> {
    boolean process(T value1, K value2);
}
