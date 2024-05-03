import code.Car;
import code.DateTime;
import code.VehicleType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarTest {
    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car("C_001", 2015, "Ford", "Mustang", 0, new VehicleType(4));
    }

    @Test
    @Order(1)
    @DisplayName("Testing the Late Fee")
    public void LateFeeTest(){
        assertEquals(5850.0, car.getLateFee(new DateTime(31, 7, 2025), new DateTime(1, 6, 2025)), 0.01);
    }

    @Test
    @Order(2)
    @DisplayName("Renting a Car for Valid Days")
    public void ReturnVehicleTest() {
        car.rent("CUS_001", new DateTime(20, 2, 2025), 3);
        assertTrue(car.returnVehicle(new DateTime(22, 2, 2025)));
    }

    @Test
    @Order(3)
    @DisplayName("Returning a Car Before Minimum Days")
    public void ReturnVehicleTestBeforeMinDays() {
        car.rent("CUS_002", new DateTime(1,3,2022), 3);
        assertFalse(car.returnVehicle(new DateTime(2,3,2022)));
    }

    @Test
    @Order(4)
    @DisplayName("Returning a Car After Maximum Days")
    public void ReturnVehicleTestAfterMaxDays() {
        car.rent("CUS_003", new DateTime(12,3,2022), 13);
        assertTrue(car.returnVehicle(new DateTime(25,3,2022)));
    }

    @Test
    @Order(5)
    @DisplayName("Returning a Car That Was Not Rented")
    public void ReturnVehicleNotRentedTest() {
        assertFalse(car.returnVehicle(new DateTime(1, 4, 2025)));
    }

    @Test
    @Order(6)
    @DisplayName("Completing Maintenance of a Car")
    public void CompleteMaintenanceTest() {
        car.performMaintenance();
        assertTrue(car.completeMaintenance());
    }

    @Test
    @Order(7)
    @DisplayName("Completing Maintenance of a Car Not in Maintenance Status")
    public void CompleteMaintenanceNotInMaintenanceTest() {
        assertFalse(car.completeMaintenance());
    }
}
