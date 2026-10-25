
package com.rest.CLI;

import com.rest.models.Table;
import com.rest.service.TableServices;
import java.util.Scanner;

public class TableCLI {
    private final TableServices service;
    Scanner sc = new Scanner(System.in);

    public TableCLI(TableServices service) {
        this.service = service;
    }

    public void start() {
        int choice;

        do {
            System.out.println("\nTable Management");
            System.out.println("1. All Tables");
            System.out.println("2. Add Table");
            System.out.println("3. Edit Table");
            System.out.println("4. Delete Table");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Table t : service.getAll()) {
                        System.out.println(
                            "ID: " + t.getId()
                            + ", Capacity: " + t.getCapasity()
                            + ", Status: " + t.getStatus()
                        );
                    }
                    break;

                case 2:
                    System.out.print("Enter table ID: ");
                    int newId = sc.nextInt();

                    System.out.print("Enter capacity: ");
                    int capacity = sc.nextInt();

                    service.addTable(newId, capacity);
                    System.out.println("Table added successfully!");
                    break;

                case 3:
                    System.out.print("Enter table ID to edit: ");
                    int editId = sc.nextInt();

                    System.out.print("Enter new capacity: ");
                    int newCapacity = sc.nextInt();

                    if (service.editTable(editId, newCapacity)) {
                        System.out.println("Table updated successfully!");
                    } else {
                        System.out.println("Table not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter table ID to delete: ");
                    int deleteId = sc.nextInt();

                    if (service.deleteTable(deleteId)) {
                        System.out.println("Table deleted successfully!");
                    } else {
                        System.out.println("Table not found!");
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}
