package org.example;

import java.util.List;

public class SortingContext<T> {
    private SortStrategy<T> strategy;

    public SortingContext(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void sort(List<T> list) {
        strategy.sort(list);
    }
}
