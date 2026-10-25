package com.rest.service;

import com.rest.repository.MenuRepository;
import com.rest.models.Menu;
import java.util.List;

public class MenuServices {
    private final MenuRepository repository;

    public MenuServices() {
        repository = new MenuRepository();
    }

    public List<Menu> getAll() {
        return repository.show();
    }
    
}
