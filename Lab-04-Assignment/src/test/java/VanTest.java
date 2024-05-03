import code.Van;
import code.DateTime;
import code.VehicleType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VanTest {

    private Van van;

    @BeforeEach
    public void setUp() {
        van = new Van("C_002", 2015, "Ford", "Avantador", 0, new VehicleType(15));
    }

    @Test
    @Order(1)
    @DisplayName("Testing the Late Fee")
    public void LateFeeTest(){
        assertEquals(17940.0, van.getLateFee(new DateTime(31, 7, 2025), new DateTime(1, 6, 2025)), 0.01);
    }

    @Test
    @Order(2)
    @DisplayName("Renting a Car for Valid Days")
    public void ReturnVehicleTest() {
        van.rent("CUS_001", new DateTime(20, 2, 2024), 3);
        assertTrue(van.returnVehicle(new DateTime(22, 2, 2024)));

    }

    @Test
    @Order(3)
    @DisplayName("Returning a Van Before Minimum Days")
    public void ReturnVehicleTestBeforeMinDays() {
        van.rent("CUS_002", new DateTime(2,6,2024), 3);
        assertFalse(van.returnVehicle(new DateTime(2,6,2024)));
    }

    @Test
    @Order(4)
    @DisplayName("Returning a Car After Maximum Days")
    public void ReturnVehicleTestAfterMaxDays() {
        van.rent("CUS_003", new DateTime(2,6,2024), 13);
        assertTrue(van.returnVehicle(new DateTime(18,6,2024)));
    }

    @Test
    @Order(5)
    @DisplayName("Returning a Car That Was Not Rented")
    public void ReturnVehicleNotRentedTest() {
        assertFalse(van.returnVehicle(new DateTime(1, 4, 2025)));
    }

    @Test
    @Order(6)
    @DisplayName("Completing Maintenance of a Van")
    public void CompleteMaintenanceTest() {
        van.performMaintenance();
        assertTrue(van.completeMaintenance(new DateTime(10, 3, 2022)));
    }

}