package com.vova_cons.tanks_battle.utils;

@FunctionalInterface
public interface Callback {
    void handle();

    static void safeCall(Callback callback) {
        if (callback != null) {
            callback.handle();
        }
    }
}
