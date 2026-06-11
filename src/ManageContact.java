import java.util.ArrayList;

public class ManageContact {

    private final ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact c) {
        contacts.add(c);
    }

    public void showContacts() {
        for (Contact c : contacts) {
            System.out.println(c);
        }
    }

    public Contact searchContact(String name) {
        for (Contact c : contacts) {
            if (c.getLastName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public Contact searchUserById(int id) {
        for (Contact c : contacts) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void modifyContact(int id, int choice, String value) {

        Contact found = searchUserById(id);

        if (found == null) {
            System.out.println("User not found");
        }

        switch (choice) {
            case 1 -> found.setFirstName(value);
            case 2 -> found.setLastName(value);
            case 3 -> found.setEmail(value);
            case 4 -> found.setPhone(value);
            default -> {
                System.out.println("Error");
            }
        }

        System.out.println("User has been modified successfully !");

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
}