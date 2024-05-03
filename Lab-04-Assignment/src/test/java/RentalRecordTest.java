import code.DateTime;
import code.RentalRecord;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RentalRecordTest {
    private static DateTime estimatedReturnDate;
    private static DateTime rentDate;

    @Test
    @Order(1)
    @DisplayName("Test Get Details Without Data")
    public void ToStringNoData() {
        rentDate = new DateTime(5, 4, 2022);
        estimatedReturnDate = new DateTime(10, 4, 2022);
        RentalRecord record = new RentalRecord("R_005", rentDate, estimatedReturnDate);
        String expected = "R_005:05/04/2022:10/04/2022:none:none:none";
        assertEquals(expected, record.toString());
    }

    @Test
    @Order(2)
    @DisplayName("Test Get Details With Data")
    public void ToStringWithData() {
        rentDate = new DateTime(5, 4, 2022);
        estimatedReturnDate = new DateTime(10, 4, 2022);
        DateTime actualReturnDate = new DateTime(12, 4, 2022);
        RentalRecord record = new RentalRecord("R_006", rentDate, estimatedReturnDate);
        record.setData(actualReturnDate, 200.50, 40.25);
        String expected = "R_006:05/04/2022:10/04/2022:12/04/2022:200.5:40.25";
        assertEquals(expected, record.toString());
    }

    @Test
    @Order(3)
    @DisplayName("Test Get Estimated Return Date")
    public void GetEstimatedReturnDate() {
        rentDate = new DateTime(5, 4, 2022);
        estimatedReturnDate = new DateTime(10, 4, 2022);
        RentalRecord record = new RentalRecord("R_007", rentDate, estimatedReturnDate);
        assertEquals(estimatedReturnDate, record.getEstimatedReturnDate());
    }

    @Test
    @Order(4)
    @DisplayName("Test Get Rent Date")
    public void GetRentDate() {
        rentDate = new DateTime(10, 4, 2022);
        estimatedReturnDate = new DateTime(10, 4, 2022);
        RentalRecord record = new RentalRecord("R_008", rentDate, estimatedReturnDate);
        assertEquals(rentDate, record.getRentDate());
    }
}
