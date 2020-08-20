package com.example.demo.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbsrtactMapper<E,D> {

    public abstract D convertToDto(E e);

    public abstract E converToEntity(D d);

    public List<D> convertToDtoList(List<E> eList) {
        return convertList(eList, s -> convertToDto(s));
    }

    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }
}
