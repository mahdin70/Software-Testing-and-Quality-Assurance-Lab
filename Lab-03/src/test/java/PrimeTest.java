import org.example.Prime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimeTest {
    @Test
    public void testIsPrimeFor2() {
        Prime prime = new Prime();
        assertTrue(prime.isPrime(2));
    }

    @Test
    public void testIsPrimeFor20() {
        Prime prime = new Prime();
        assertFalse(prime.isPrime(20));
    }

    @Test
    public void testIsPrimeFor47() {
        Prime prime = new Prime();
        assertTrue(prime.isPrime(47));
    }

    @Test
    public void testIsPrimeFor933() {
        Prime prime = new Prime();
        assertFalse(prime.isPrime(933));
    }

    @Test
    public void testIsPrimeFor1000000000000(){
        Prime prime = new Prime();
        assertFalse(prime.isPrime(1000000000000L));
    }

    @Test
    void isPrimeThrowsForNonInteger() {
        Prime prime = new Prime();
        assertThrows(IllegalArgumentException.class, () -> prime.isPrime(2.5));
    }

    @Test
    void isPrimeThrowsForIntegerLessThan2() {
        Prime prime = new Prime();
        assertThrows(IllegalArgumentException.class, () -> prime.isPrime(1));
    }

    @Test
    void isPrimeThrowsForNegativeInteger() {
        Prime prime = new Prime();
        assertThrows(IllegalArgumentException.class, () -> prime.isPrime(-14));
    }

    @Test
    void isPrimeThrowsForTooLargeInteger() {
        Prime prime = new Prime();
        assertThrows(IllegalArgumentException.class, () -> prime.isPrime(1000000000001L));
    }
}

