import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService service;

    @BeforeEach
    public void setup() {
        service = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertEquals("John", service.getContacts().get("1").getFirstName());
    }

    @Test
    public void testAddDuplicateContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact);
        });
        assertEquals("Contact already exists or is null", exception.getMessage());
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.deleteContact("1");
        assertFalse(service.getContacts().containsKey("1"));
    }

    @Test
    public void testDeleteNonexistentContact() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("1");
        });
        assertEquals("Contact does not exist", exception.getMessage());
    }

    @Test
    public void testUpdateContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.updateContact("1", "Jane", "Doe", "0987654321", "321 Main St");
        Contact updatedContact = service.getContacts().get("1");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("0987654321", updatedContact.getPhone());
    }

    @Test
    public void testUpdateNonexistentContact() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("1", "Jane", "Doe", "0987654321", "321 Main St");
        });
        assertEquals("Contact does not exist", exception.getMessage());
    }
}

