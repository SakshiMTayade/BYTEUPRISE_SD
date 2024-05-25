package byteUprise;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Scanner;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
class ContactManager {
    private List<Contact> contacts;
    private static final String FILE_NAME = "contacts.dat";

    public ContactManager() {
        contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void editContact(int index, Contact newContact) {
        if (index >= 0 && index < contacts.size()) {
            contacts.set(index, newContact);
            saveContacts();
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            saveContacts();
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    private void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contacts = (List<Contact>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, no contacts to load
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }
}
public class task2_contactManagementSystem {
        private static ContactManager contactManager = new ContactManager();
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            while (true) {
                printMenu();
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addContact();
                        break;
                    case 2:
                        viewContacts();
                        break;
                    case 3:
                        editContact();
                        break;
                    case 4:
                        deleteContact();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private static void printMenu() {
            System.out.println("Contact Manager Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
        }

        private static void addContact() {
            System.out.print("Enter contact name: ");
            String name = scanner.nextLine();

            System.out.print("Enter contact phone number: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Enter contact email: ");
            String email = scanner.nextLine();

            Contact contact = new Contact(name, phoneNumber, email);
            contactManager.addContact(contact);
            System.out.println("Contact added successfully.");
        }

        private static void viewContacts() {
            List<Contact> contacts = contactManager.getContacts();
            if (contacts.isEmpty()) {
                System.out.println("No contacts available.");
            } else {
                for (int i = 0; i < contacts.size(); i++) {
                    System.out.println((i + 1) + ". " + contacts.get(i));
                }
            }
        }

        private static void editContact() {
            System.out.print("Enter contact number to edit: ");
            int contactNumber = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter new contact name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new contact phone number: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Enter new contact email: ");
            String email = scanner.nextLine();

            Contact newContact = new Contact(name, phoneNumber, email);
            contactManager.editContact(contactNumber - 1, newContact);
            System.out.println("Contact edited successfully.");
        }

        private static void deleteContact() {
            System.out.print("Enter contact number to delete: ");
            int contactNumber = Integer.parseInt(scanner.nextLine());

            contactManager.deleteContact(contactNumber - 1);
            System.out.println("Contact deleted successfully.");
        }
}
