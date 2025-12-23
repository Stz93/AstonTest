package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class AdditionalQuickSortStrategy<T> extends QuickSortStrategy<T> {

    private class Evens {
        private final List<T> evens;
        private final List<Integer> indexesOfEvens;

        Evens(List<T> evens, List<Integer> indexesOfEvens) {
            this.evens = evens;
            this.indexesOfEvens = indexesOfEvens;
        }
    }

    @Override
    public void sort(List<T> list) {
        var evens = getEvens(list);
        for (var i = 0; i < evens.evens.size(); i++) {
            list.set(evens.indexesOfEvens.get(i), evens.evens.get(i));
        }
    }

    private Evens getEvens(List<T> list) {
        var evens = new ArrayList<T>();
        var indexesOfEvens = new ArrayList<Integer>();

        for (var i = 0; i < list.size(); i++) {
            if (getNumericField(list.get(i)) % 2 == 0) {
                evens.add(list.get(i));
                indexesOfEvens.add(i);
            }
        }

        super.sort(evens);

        return new Evens(evens, indexesOfEvens);
    }

    protected abstract int getNumericField (T object);
}