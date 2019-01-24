package com.teamacronymcoders.base.util.collections;

import net.minecraft.util.NonNullList;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class NonnullListCollector<T> implements Collector<T, NonNullList<T>, NonNullList<T>> {
    private final Set<Collector.Characteristics> CH_ID = Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));

    @Override
    public Supplier<NonNullList<T>> supplier() {
        return NonNullList::create;
    }

    @Override
    public BiConsumer<NonNullList<T>, T> accumulator() {
        return NonNullList::add;
    }

    @Override
    public BinaryOperator<NonNullList<T>> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<NonNullList<T>, NonNullList<T>> finisher() {
        return i -> (NonNullList<T>) i;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return CH_ID;
    }
}
