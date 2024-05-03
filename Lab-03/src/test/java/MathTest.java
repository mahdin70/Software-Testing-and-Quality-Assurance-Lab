import org.example.Math;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
    @Test
    public void testAdd(){
        Math math = new Math();
        assertEquals(5, math.add(2,3));
    }

}
