package ru.nasa.front.steps;

public class BaseSteps<T> {
    T page;

    public T getPage() {
        return page;
    }

    public void setPage(T page) {
        this.page = page;
    }
}
