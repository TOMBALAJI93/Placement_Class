package com.rest.repository;

import com.rest.models.Table;
import java.util.ArrayList;
import java.util.List;

public class TableRepository{
    private final List<Table> tables = new ArrayList<>();

    public TableRepository() {
        tables.add(new Table(1,4));
        tables.add(new Table(2,2));
        tables.add(new Table(3,7));
    }

    public List<Table> show(){
        return tables;
    }

    public void add(Table table) {
        tables.add(table);
    }

    public boolean edit(int id, int capacity) {
        for (Table t : tables) {
            if (t.getId() == id) {
                t.setCapacity(capacity);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        return tables.removeIf(t -> t.getId() == id);
    }

}