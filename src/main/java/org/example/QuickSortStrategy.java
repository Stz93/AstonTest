package org.example;

import java.util.List;

public abstract class QuickSortStrategy<T> implements SortStrategy<T> {
    protected abstract int compare(T first, T second);

    private void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            int p = partition(list, low, high);
            quickSort(list, low, p - 1);
            quickSort(list, p + 1, high);
        }
    }

    private int partition(List<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    @Override
    public void sort(List<T> list) {
        if (list != null && list.size() >= 2) {
            quickSort(list, 0, list.size() - 1);
        }
    }
}
