package com.rest.CLI;

import java.util.Scanner;
import com.rest.models.Menu;
import com.rest.service.MenuServices;

public class MenuCLI {
    private final MenuServices service;
    Scanner sc = new Scanner(System.in);

    public MenuCLI(MenuServices service) {
        this.service = service;
    }

    public void start() {
        int choice;

        do {
            System.out.println("\nRestaurant Menu");
            System.out.println("1. Show All Menu Items");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Menu m : service.getAll()) {
                        System.out.println(
                            "ID: " + m.getId()
                            + ", Name: " + m.getName()
                            + ", Price: Rs." + m.getPrice()
                        );
                    }
                    break;

                case 0:
                    System.out.println("Exiting Menu...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }
}