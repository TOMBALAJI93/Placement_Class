import com.rest.CLI.BillCLI;
import com.rest.CLI.MenuCLI;
import com.rest.CLI.OrderCLI;
import com.rest.CLI.PaymentCLI;
import com.rest.CLI.TableCLI;
import com.rest.service.BillServices;
import com.rest.service.MenuServices;
import com.rest.service.OrderServices;
import com.rest.service.PaymentServices;
import com.rest.service.TableServices;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TableServices tableService = new TableServices();
        MenuServices menuService = new MenuServices();

        OrderServices orderService =
                new OrderServices(menuService, tableService);

        BillServices billService = new BillServices();

        PaymentServices paymentService =
                new PaymentServices(billService);

        TableCLI tableCli = new TableCLI(tableService);
        MenuCLI menuCli = new MenuCLI(menuService);

        OrderCLI orderCli =
                new OrderCLI(orderService, menuService, sc);

        BillCLI billCli =
                new BillCLI(billService, orderService, sc);

        PaymentCLI paymentCli = new PaymentCLI(paymentService,billService,sc);

        int choice;

        do {
            System.out.println("\n===== RESTAURANT MANAGEMENT =====");
            System.out.println("1. Table Management");
            System.out.println("2. Menu Management");
            System.out.println("3. Order Management");
            System.out.println("4. Bill Management");
            System.out.println("5. Payment Management");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    tableCli.start();
                    break;

                case 2:
                    menuCli.start();
                    break;

                case 3:
                    orderCli.start();
                    break;

                case 4:
                    billCli.start();
                    break;

                case 5:
                    paymentCli.start();
                    break;

                case 0:
                    System.out.println(
                            "Exiting Restaurant Management..."
                    );
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
