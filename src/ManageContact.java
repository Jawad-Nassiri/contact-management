import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;

public class ManageContact {
    private final ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact c) {
        contacts.add(c);
    }

    public void displayContacts() {
        for (Contact c : contacts) {
            System.out.println(c);
        }
    }

    public Contact searchContactById(int id) {
        for (Contact c : contacts) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public Contact searchContactByName(String lastName) {
        for (Contact c : contacts) {
            if (c.getLastName().equals(lastName)) {
                return c;
            }
        }

        return null;
    }

    public void modifyContact(int id, int choice, String value) {
        Contact found = searchContactById(id);

        if (found == null) {
            System.out.println("The user not found! ");
            return;
        }

        switch (choice) {
            case 1 -> found.setFirstName(value);
            case 2 -> found.setLastName(value);
            case 3 -> found.setEmail(value);
            case 4 -> found.setPhone(value);
            default -> {
                System.out.println("Invalid choice");
                return;
            }
        }

        System.out.println("User modified successfully!");
    }

    public boolean deleteContact(int id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == id) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }

    public void saveContacts() {
        String filePath = "C:\\Users\\jawad\\Desktop\\contacts.txt";

        try (FileWriter writer = new FileWriter(filePath)) {

            for (Contact c : contacts) {
                writer.write(
                        c.getId() + "," +
                                c.getFirstName() + "," +
                                c.getLastName() + "," +
                                c.getEmail() + "," +
                                c.getPhone() + "\n"
                );
            }

            System.out.println("Contacts saved successfully!");

        } catch (Exception e) {
            System.out.println("Error saving contacts");
        }
    }

    public void loadContacts() {
        String filePath = "C:\\Users\\jawad\\Desktop\\contacts.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName = data[2];
                String email = data[3];
                String phone = data[4];

                Contact c = new Contact(id, firstName, lastName, email, phone);
                contacts.add(c);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (Exception e) {
            System.out.println("Error loading contacts");
        }
    }

}