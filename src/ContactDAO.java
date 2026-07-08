import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    // add contact
    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getEmail());
            ps.setString(4, contact.getPhone());

            ps.executeUpdate();
            System.out.println("Contact added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    display contacts
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();

        String sql = "SELECT * FROM contacts";

        try (Connection connection = DatabaseConnection.getConnection(); Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Contact contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"));

                contacts.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    //    search by name
    public List<Contact> searchContactByName(String name) {
        List<Contact> contacts = new ArrayList<>();

        String sql = "SELECT * FROM contacts WHERE last_name LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contact contact = new Contact(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("phone"));

                contacts.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    //    search a contact by id
    public Contact searchContactById(int id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Contact(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("phone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //    modify contact
    public boolean updateContact(int id, int choice, String newValue) {
        String column = "";

        switch (choice) {
            case 1 -> column = "first_name";
            case 2 -> column = "last_name";
            case 3 -> column = "email";
            case 4 -> column = "phone";
            default -> {
                return false;
            }
        }

        String sql = "UPDATE contacts SET " + column + " = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newValue);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //    delete contact
    public boolean deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
