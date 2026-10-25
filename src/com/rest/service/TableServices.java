
package com.rest.service;

import com.rest.models.Table;
import com.rest.repository.TableRepository;
import java.util.List;

public class TableServices {
    private final TableRepository repository;

    public TableServices() {
        repository = new TableRepository();
    }

    public List<Table> getAll() {
        return repository.show();
    }

    public void addTable(int id, int capacity) {
        repository.add(new Table(id, capacity));
    }

    public boolean editTable(int id, int capacity) {
        return repository.edit(id, capacity);
    }

    public boolean deleteTable(int id) {
        return repository.delete(id);
    }
}
