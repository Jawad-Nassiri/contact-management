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

    public boolean modifyContact(int id, int choice, String value) {
        for (Contact c : contacts) {
            if (c.getId() == id) {
                switch (choice) {
                    case 1 -> c.setFirstName(value);
                    case 2 -> c.setLastName(value);
                    case 3 -> c.setEmail(value);
                    case 4 -> c.setPhone(value);
                    default -> {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
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