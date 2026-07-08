import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("Connected!");
        } else {
            System.out.println("Connection failed!");
        }
        Scanner scanner = new Scanner(System.in);
        ContactDAO dao = new ContactDAO();
        boolean running = true;

        while (running) {

            int userChoice = getUserChoice(scanner);

            if (userChoice >= 1 && userChoice <= 6) {
                switch (userChoice) {
                    case 1 -> handleAdd(scanner, dao);
                    case 2 -> displayContacts(dao);
                    case 3 -> handleSearch(scanner, dao);
                    case 4 -> handleModify(scanner, dao);
                    case 5 -> handleDelete(scanner, dao);
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

    public static void handleAdd(Scanner scanner, ContactDAO dao) {
        String firstName = readNonBlank(scanner, "first name");
        String lastName = readNonBlank(scanner, "last name");
        String email = readNonBlank(scanner, "email");
        String phone = readNonBlank(scanner, "phone");
        Boolean isValidEmail = isEmailValid(email);
        if (isValidEmail) {
            Contact contact = new Contact(firstName, lastName, email, phone);
            dao.addContact(contact);
        } else {
            System.out.println("Email is not valid !");
        }

    }

    public static void displayContacts(ContactDAO dao) {
        List<Contact> contacts = dao.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contact found !");
        } else {
            for (Contact c : contacts) {
                System.out.println(c);
            }
        }
    }

    public static void handleSearch(Scanner scanner, ContactDAO dao) {
        System.out.print("Enter the last name: ");
        String name = scanner.nextLine();

        List<Contact> results = dao.searchContactByName(name);

        if (results.isEmpty()) {
            System.out.println("Contact not found");
        } else {
            for (Contact c : results) {
                System.out.println(c);
            }
        }
    }

    public static void handleModify(Scanner scanner, ContactDAO dao) {
        String newValue = "";

        System.out.print("Enter the userid: ");
        int contactId = scanner.nextInt();
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

            boolean modified = dao.updateContact(contactId, choice, newValue);

            if (modified) {
                System.out.println("Contact updated successfully !");
            } else {
                System.out.println("Contact not found!");
            }

        } else {
            System.out.println("Enter a valid choice (1-4)");
        }
    }

    public static void handleDelete(Scanner scanner, ContactDAO dao) {
        System.out.print("Enter the contact id: ");
        int contactId = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = dao.deleteContact(contactId);

        if (deleted) {
            System.out.println("Contact has been deleted successfully !");
        } else {
            System.out.println("Contact not found !");
        }
    }

    public static boolean isEmailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

}