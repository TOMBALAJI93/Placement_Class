
package com.rest.repository;

import com.rest.models.Menu;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private final List<Menu> menuItems = new ArrayList<>();

    public MenuRepository() {
        menuItems.add(new Menu(1, "Dosa", 15));
        menuItems.add(new Menu(2, "Idly", 10));
        menuItems.add(new Menu(3, "Vada", 5));
    }

    public List<Menu> show() {
        return menuItems;
    }
}
