import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    // add contact
    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

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

        try (Connection connection = DatabaseConnection.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Contact contact = new Contact(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );

                contacts.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }


}
