import code.DateTime;
import code.VehicleType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleTypeTest {
    private static VehicleType carType;
    private static VehicleType vanType;

    @BeforeAll
    public static void setUp() {
        carType = new VehicleType(3, new DateTime(4));
        vanType = new VehicleType(10, new DateTime(4));
    }
    @Test
    @DisplayName("Test can rent car for minimum days")
    public void CanRentCarForMinimumDay() {
        DateTime rentDate = new DateTime(18, 6, 2023);
        assertEquals(1, carType.canBeRentedForMinimumDays(rentDate, "carType"));
    }

    @Test
    @DisplayName("Test if under maintenance")
    public void UnderMaintenance() {
        DateTime rentDate = new DateTime(8);
        assertTrue(vanType.IsUnderMaintenance(rentDate, "vanType", 8));
    }

    @Test
    @DisplayName("Test getting seats for van")
    public void GetSeatsForVan() {
        assertEquals(10, vanType.getSeats("vanType"));
    }

    @Test
    @DisplayName("Test setting car seats")
    public void SetCarSeats() {
        carType.setCarSeats(4);
        assertEquals(4, carType.getCarSeats());
    }

    @Test
    @DisplayName("Test getting seats for car")
    public void GetSeatsForCar() {
        assertEquals(3, carType.getSeats("carType"));
    }
}
