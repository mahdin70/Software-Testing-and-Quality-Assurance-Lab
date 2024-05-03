import code.DateTime;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DateTimeTest {

    @Test
    @Order(1)
    @DisplayName("Test difference in days")
    public void testDiffDays() {
        DateTime startDate = new DateTime(1, 1, 2022);
        DateTime endDate = new DateTime(10, 1, 2022);
        assertEquals(9, DateTime.diffDays(endDate, startDate));
    }

    @Test
    @Order(2)
    @DisplayName("Test getNameOfDay method")
    public void testGetNameOfDay() {
        DateTime dateTime = new DateTime(15, 3, 2022);
        assertEquals("Tuesday", dateTime.getNameOfDay());
    }

    @Test
    @Order(3)
    @DisplayName("Test getCurrentTime method")
    public void testGetCurrentTime() {
        String expectedFormat = "HH:mm:ss";
        String currentTime = DateTime.getCurrentTime();
        assertEquals(expectedFormat.length(), currentTime.length());
    }

    @Test
    @Order(4)
    @DisplayName("Test getFormattedDate method")
    public void testGetFormattedDate() {
        DateTime dateTime = new DateTime(20, 3, 2022);
        assertEquals("20/03/2022", dateTime.getFormattedDate());
    }

    @Test
    @Order(5)
    @DisplayName("Test getEightDigitDate method")
    public void testGetEightDigitDate() {
        DateTime dateTime = new DateTime(25, 3, 2022);
        assertEquals("25032022", dateTime.getEightDigitDate());
    }

    @Test
    @Order(6)
    @DisplayName("Test setAdvance method")
    public void testSetAdvance() {
        DateTime dateTime = new DateTime(1, 1, 2022);
        dateTime.setAdvance(2, 1, 30); // Advances by 2 days, 1 hour, and 30 minutes
        DateTime expectedDateTime = new DateTime(3, 1, 2022);
        assertEquals(expectedDateTime.getFormattedDate(), dateTime.getFormattedDate());
    }
}
