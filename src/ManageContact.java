//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.io.FileWriter;
//
//public class ManageContact {
//
////    public void displayContacts() {
////        for (Contact c : contacts) {
////            System.out.println(c);
////        }
////    }
//
//    public Contact searchContactById(int id) {
//        for (Contact c : contacts) {
//            if (c.getId() == id) {
//                return c;
//            }
//        }
//
//        return null;
//    }
//
//    public ArrayList<Contact> searchContactByName(String name) {
//        ArrayList<Contact> results = new ArrayList<>();
//
//        for (Contact c : contacts) {
//            if (c.getLastName().toLowerCase().startsWith(name.toLowerCase())) {
//                results.add(c);
//            }
//        }
//
//        return results;
//    }
//
//    public boolean modifyContact(int id, int choice, String value) {
//        Contact found = searchContactById(id);
//
//        if (found == null) {
//            System.out.println("User not found! ");
//            return false;
//        }
//
//        switch (choice) {
//            case 1 -> found.setFirstName(value);
//            case 2 -> found.setLastName(value);
//            case 3 -> found.setEmail(value);
//            case 4 -> found.setPhone(value);
//            default -> {
//                System.out.println("Invalid choice");
//                return false;
//            }
//        }
//
//        System.out.println("User modified successfully!");
//        return true;
//    }
//
//    public boolean deleteContact(int id) {
//        return contacts.removeIf(c -> c.getId() == id);
//    }
//
//    public void saveContacts() {
//        String filePath = System.getProperty("user.dir") + "/contacts.txt";
//
//        try (FileWriter writer = new FileWriter(filePath)) {
//
//            for (Contact c : contacts) {
//                writer.write(
//                        c.getId() + "," +
//                                c.getFirstName() + "," +
//                                c.getLastName() + "," +
//                                c.getEmail() + "," +
//                                c.getPhone() + "\n"
//                );
//            }
//
//            System.out.println("Contacts saved successfully!");
//
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    public void loadContacts() {
//        String filePath = System.getProperty("user.dir") + "/contacts.txt";
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//
//                if (line.isBlank() || !line.contains(",")) {
//                    continue;
//                }
//
//                String[] data = line.split(",");
//
//                if (data.length != 5) {
//                    continue;
//                }
//
//                try {
//                    int id = Integer.parseInt(data[0]);
//
//                    Contact c = new Contact(
//                            data[1],
//                            data[2],
//                            data[3],
//                            data[4]
//                    );
//
//                    contacts.add(c);
//
//                } catch (NumberFormatException e) {
//                    System.out.println("Skipping line: invalid ID format");
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//}