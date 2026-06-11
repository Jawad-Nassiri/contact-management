import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        create manager object to manage contacts
        ManageContact manager = new ManageContact();

//        create contacts
        Contact c1 = new Contact(1, "Ali", "Benali", "ali@gmail.com", "0612345678");
        Contact c2 = new Contact(2, "Sara", "Martin", "sara@gmail.com", "0623456789");
        Contact c3 = new Contact(3, "John", "Smith", "john.smith@gmail.com", "0634567890");
        Contact c4 = new Contact(4, "Emma", "Johnson", "emma.johnson@gmail.com", "0645678901");
        Contact c5 = new Contact(5, "Michael", "Brown", "michael.brown@gmail.com", "0656789012");
        Contact c6 = new Contact(6, "Olivia", "Davis", "olivia.davis@gmail.com", "0667890123");
        Contact c7 = new Contact(7, "William", "Miller", "william.miller@gmail.com", "0678901234");
        Contact c8 = new Contact(8, "Sophia", "Wilson", "sophia.wilson@gmail.com", "0689012345");
        Contact c9 = new Contact(9, "James", "Moore", "james.moore@gmail.com", "0690123456");
        Contact c10 = new Contact(10, "Isabella", "Taylor", "isabella.taylor@gmail.com", "0601234567");

//        add contacts to the list
        manager.addContact(c1);
        manager.addContact(c2);
        manager.addContact(c3);
        manager.addContact(c4);
        manager.addContact(c5);
        manager.addContact(c6);
        manager.addContact(c7);
        manager.addContact(c8);
        manager.addContact(c9);
        manager.addContact(c10);


//        show all contacts
//        manager.showContacts();

//        search for a contact and store the result in the variable found
//        Contact found = manager.searchContact("Moore");

//        show result if contact found and a not found message if not
//        if (found != null) {
//            System.out.println("Search result => " + found);
//        } else {
//            System.out.println("Contact not found");
//        }


//        modifying a contact
        System.out.print("Enter user id you want to modify: ");
        int userId = scanner.nextInt();

        Contact found = manager.searchUserById(userId);

        if(found != null) {
            System.out.println("What do you want to change?");
            System.out.println("1 => First name");
            System.out.println("2 => Last name");
            System.out.println("3 => Email");
            System.out.println("4 => Phone");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 4) {
                System.out.println("Enter a valid number 1-4");
                return;
            }

            switch (choice) {
                case 1 -> System.out.print("Enter new first name: ");
                case 2 -> System.out.print("Enter new last name: ");
                case 3 -> System.out.print("Enter new email: ");
                case 4 -> System.out.print("Enter new phone number: ");
            }

            String value = scanner.nextLine();

            manager.modifyContact(userId, choice, value);
            manager.showContacts();

        } else {
            System.out.println("User not found !");
        }

        scanner.close();
    }
}