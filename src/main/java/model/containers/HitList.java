package model.containers;

import model.entites.Hit;

import java.util.ArrayList;

public interface HitList<T> {

    void add(T obj);

    T get(int index);

    ArrayList<T> getList();

    void clear();

}
