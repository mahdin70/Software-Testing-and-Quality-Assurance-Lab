import code.DateTime;
import code.Vehicle;
import code.VehicleType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VehicleTest {
    private static DateTime rentDate;
    private static Vehicle vehicle;


    @BeforeAll
    public static void setUp() {
        VehicleType vehicleType = new VehicleType(4);
        vehicle = new Vehicle("C_005", 2014, "Ford", "Mustang", 0, vehicleType);
        rentDate = new DateTime(18, 6, 2024);
    }

    @Test
    @Order(1)
    @DisplayName("Test getting vehicle details")
    public void GetVehicleDetails() {
        String details = vehicle.getDetails();
        assertNotNull(details);
    }

    @Test
    @Order(2)
    @DisplayName("Test getting vehicle ID")
    public void GetVehicleId() {
        String vehicleId = vehicle.getVehicleId();
        assertEquals("C_005", vehicleId);
    }

    @Test
    @Order(3)
    @DisplayName("Test renting vehicle with invalid rental days")
    public void RentVehicleInvalidDays() {
        boolean rented = vehicle.rent("CUS_001", rentDate, 15);
        assertFalse(rented);
    }

    @Test
    @Order(4)
    @DisplayName("Test renting vehicle with insufficient rental days")
    public void RentVehicleInsufficientDays() {
        boolean rented = vehicle.rent("CUS_001", rentDate, 1);
        assertFalse(rented);
    }

    @Test
    @Order(5)
    @DisplayName("Test renting vehicle successfully")
    public void RentVehicleSuccessful() {
        boolean rented = vehicle.rent("CUS_002", rentDate, 3);
        assertTrue(rented);
    }
}
