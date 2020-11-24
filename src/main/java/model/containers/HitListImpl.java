package model.containers;

import model.entites.Hit;

import java.util.ArrayList;

public class HitListImpl implements HitList<Hit> {

    private ArrayList<Hit> list;

    public HitListImpl(){
        list = new ArrayList<Hit>();
    }

    @Override
    public void add(Hit obj) {
        list.add(obj);
    }

    @Override
    public Hit get(int index) {
        return list.get(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public ArrayList<Hit> getList() {
        return list;
    }
}
