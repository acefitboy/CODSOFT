import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;
    private static final String FILE_PATH = "address_book.txt";

    public AddressBook() {
        contacts = new ArrayList<>();
        loadContactsFromFile();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContactsToFile();
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        saveContactsToFile();
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private void loadContactsFromFile() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    Contact contact = new Contact(data[0].trim(), data[1].trim(), data[2].trim());
                    contacts.add(contact);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Address book file not found. It will be created when you add contacts.");
        }
    }

    private void saveContactsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + ", " + contact.getPhoneNumber() + ", " + contact.getEmailAddress());
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts to the address book file.");
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        while (true) {
            System.out.println("\nAddress Book System Options:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added successfully.");
                    break;
                case 2:
                    System.out.print("Enter the name of the contact to remove: ");
                    String contactToRemove = scanner.nextLine();
                    Contact foundContact = addressBook.searchContact(contactToRemove);
                    if (foundContact != null) {
                        addressBook.removeContact(foundContact);
                        System.out.println("Contact removed successfully.");
                    } else {
                        System.out.println("Contact not found in the address book.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the name of the contact to search: ");
                    String contactToSearch = scanner.nextLine();
                    Contact searchedContact = addressBook.searchContact(contactToSearch);
                    if (searchedContact != null) {
                        System.out.println("Contact found: " + searchedContact);
                    } else {
                        System.out.println("Contact not found in the address book.");
                    }
                    break;
                case 4:
                    System.out.println("All Contacts in Address Book:");
                    addressBook.displayAllContacts();
                    break;
                case 5:
                    System.out.println("Exiting Address Book System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
