package com.otocado.types;

import java.util.List;

public class ListPair {

    private List<String> first;
    private List<String> second;
    private List<String> third;

    public ListPair(List<String> first, List<String> second) {
        this.first = first;
        this.second = second;
    }

    public List<String> getFirst() {
        return first;
    }

    public void setFirst(List<String> first) {
        this.first = first;
    }

    public List<String> getSecond() {
        return second;
    }

    public void setSecond(List<String> second) {
        this.second = second;
    }
}
