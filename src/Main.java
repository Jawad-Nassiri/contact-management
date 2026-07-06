import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir") + "/contacts.txt");
        Scanner scanner = new Scanner(System.in);
        ManageContact manager = new ManageContact();
        manager.loadContacts();
        boolean running = true;

        while (running) {

            int userChoice = getUserChoice(scanner);

            if (userChoice >= 1 && userChoice <= 6) {
                switch (userChoice) {
                    case 1 -> handleAdd(scanner, manager);
                    case 2 -> manager.displayContacts();
                    case 3 -> handleSearch(scanner, manager);
                    case 4 -> handleModify(scanner, manager);
                    case 5 -> handleDelete(scanner, manager);
                    case 6 -> {
                        System.out.println("Exit successfully !");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice");
                }
            } else {
                System.out.println("Enter a valid choice (1-6)");
            }
        }

        scanner.close();
    }

    public static int getUserChoice(Scanner scanner) {
        System.out.println("********************************");
        System.out.println("    Contact Management App");
        System.out.println("********************************");
        System.out.println("1 -> Add");
        System.out.println("2 -> Display");
        System.out.println("3 -> Search");
        System.out.println("4 -> Modify");
        System.out.println("5 -> Delete");
        System.out.println("6 -> Exit");

        System.out.print("Enter a number (1-6): ");
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        return userChoice;
    }

    public static String readNonBlank(Scanner scanner, String field) {
        while (true) {
            System.out.print("Enter the " + field + ": ");
            String value = scanner.nextLine();
            if (!value.isBlank()) return value;
            System.out.println("Enter a valid " + field + "!");
        }
    }

    public static void handleAdd(Scanner scanner, ManageContact manager) {
        int id;
        while (true) {
            try {
                System.out.print("Enter the id: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
        String firstName = readNonBlank(scanner, "first name");
        String lastName = readNonBlank(scanner, "last name");
        String email = readNonBlank(scanner, "email");
        String phone = readNonBlank(scanner, "phone");
        Contact contact = new Contact(id, firstName, lastName, email, phone);
        manager.addContact(contact);
        manager.saveContacts();
    }

    public static void handleSearch(Scanner scanner, ManageContact manager) {
        System.out.print("Enter the last name: ");
        String name = scanner.nextLine();

        ArrayList<Contact> results = manager.searchContactByName(name);

        if (results.isEmpty()) {
            System.out.println("Contact not found");
        } else {
            for (Contact c : results) {
                System.out.println(c);
            }
        }
    }

    public static void handleModify(Scanner scanner, ManageContact manager) {
        String newValue = "";

        System.out.print("Enter the userid: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("1 -> First Name");
        System.out.println("2 -> Last Name");
        System.out.println("3 -> Email");
        System.out.println("4 -> Phone Number");

        System.out.print("Enter a number to set a new value (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= 4) {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the new first name: ");
                    newValue = scanner.nextLine();
                }
                case 2 -> {
                    System.out.print("Enter the new last name: ");
                    newValue = scanner.nextLine();
                }
                case 3 -> {
                    System.out.print("Enter the new email: ");
                    newValue = scanner.nextLine();
                }
                case 4 -> {
                    System.out.print("Enter the new phone number: ");
                    newValue = scanner.nextLine();
                }
                default -> System.out.println("Choice is not valid !");
            }

            boolean modified = manager.modifyContact(userId, choice, newValue);

            if (modified) {
                manager.saveContacts();
            } else {
                System.out.println("Contact not found!");
            }

        } else {
            System.out.println("Enter a valid choice (1-4)");
        }
    }

    public static void handleDelete(Scanner scanner, ManageContact manager) {
        System.out.print("Enter the userid: ");
        int userid = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = manager.deleteContact(userid);

        if (deleted) {
            System.out.println("User has been deleted successfully !");
            manager.saveContacts();
        } else {
            System.out.println("User not found !");
        }
    }

}