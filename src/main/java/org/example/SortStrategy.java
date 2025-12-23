package org.example;
import java.util.List;

public interface SortStrategy<T> {
    public void sort(List<T> list);
}
