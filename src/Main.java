import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ManageContact manager = new ManageContact();

        Contact c1 = new Contact(1, "Ali", "Benali", "ali@gmail.com", "0612345678");
        Contact c2 = new Contact(2, "Sara", "Martin", "sara@gmail.com", "0623456789");

        manager.addContact(c1);
        manager.addContact(c2);

        manager.showContacts();

        System.out.println("Search result:");
        Contact found = manager.searchContact("Benali");

        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Not found");
        }

        scanner.close();
    }
}