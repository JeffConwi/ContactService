import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void testContactConstructorValidData() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testContactIdTooLong() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
        assertEquals("Invalid ID", exception.getMessage());
    }

    @Test
    public void testContactFirstNameTooLong() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "Johnathan", "Doe", "1234567890", "123 Main St");
        });
        assertEquals("Invalid first name", exception.getMessage());
    }

    @Test
    public void testContactLastNameTooLong() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "DoeDoeDoe", "1234567890", "123 Main St");
        });
        assertEquals("Invalid last name", exception.getMessage());
    }

    @Test
    public void testContactPhoneNotExactDigits() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", "123456789", "123 Main St");
        });
        assertEquals("Invalid phone number", exception.getMessage());
    }

    @Test
    public void testContactAddressTooLong() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", "1234567890", "123 Main Street New York City");
        });
        assertEquals("Invalid address", exception.getMessage());
    }
}

