package com.carole.secure.api.feign.config;

import java.util.function.Consumer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;

/**
 * @author CaroLe
 * @Date 2023/11/5 13:27
 * @Description
 */
public class EmptyObjectProvider<T> implements ObjectProvider<T> {

    EmptyObjectProvider() {}

    public T getObject(Object... args) throws BeansException {
        return null;
    }

    public T getIfAvailable() throws BeansException {
        return null;
    }

    public T getIfUnique() throws BeansException {
        return null;
    }

    public T getObject() throws BeansException {
        return null;
    }

    public void forEach(Consumer action) {}
}
