package com.martin.registry;

import com.martin.factory.Factory;

public interface Builder<T> {

    void register(String label, Factory<T> factory);
}
