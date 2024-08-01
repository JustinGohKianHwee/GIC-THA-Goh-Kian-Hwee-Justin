import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldTest {
    private Field field;

    @Before
    public void setUp() {
        field = new Field(10, 10);
    }

    @Test
    public void testAddCarWithinBounds() {
        Car car = new Car("A", 1, 2, 'N', "FFRFF", field);
        assertTrue(field.addCar(car));
    }

    @Test
    public void testAddCarOutOfBounds() {
        Car car = new Car("A", 10, 10, 'N', "FFRFF", field);
        assertFalse(field.addCar(car));
    }

    @Test
    public void testPositionCheck() {
        assertTrue(field.isPositionFree(1, 1));
        assertFalse(field.isPositionFree(10, 10)); 
    }

    @Test
    public void testUpdateCarPositionWithinBounds() {
        Car car = new Car("A", 1, 2, 'N', "FFRFF", field);
        field.addCar(car);
        assertTrue(field.updateCarPosition(car, 2, 2));
    }

    @Test
    public void testUpdateCarPositionOutOfBounds() {
        Car car = new Car("A", 1, 2, 'N', "FFRFF", field);
        field.addCar(car);
        assertFalse(field.updateCarPosition(car, 10, 10));
    }

    @Test
    public void testCarMoveNorth() {
        Car car = new Car("A", 5, 5, 'N', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 6 N", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveEast() {
        Car car = new Car("A", 5, 5, 'E', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("6 5 E", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveSouth() {
        Car car = new Car("A", 5, 5, 'S', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 4 S", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveWest() {
        Car car = new Car("A", 5, 5, 'W', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("4 5 W", car.getCurrentPosition());
    }

    // Turn left tests
    @Test
    public void testCarTurnLeftFromNorth() {
        Car car = new Car("A", 5, 5, 'N', "L", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 W", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnLeftFromWest() {
        Car car = new Car("A", 5, 5, 'W', "L", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 S", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnLeftFromSouth() {
        Car car = new Car("A", 5, 5, 'S', "L", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 E", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnLeftFromEast() {
        Car car = new Car("A", 5, 5, 'E', "L", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 N", car.getCurrentPosition());
    }

    // Turn right tests
    @Test
    public void testCarTurnRightFromNorth() {
        Car car = new Car("A", 5, 5, 'N', "R", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 E", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnRightFromEast() {
        Car car = new Car("A", 5, 5, 'E', "R", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 S", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnRightFromSouth() {
        Car car = new Car("A", 5, 5, 'S', "R", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 W", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnRightFromWest() {
        Car car = new Car("A", 5, 5, 'W', "R", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 N", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveOutOfBoundsSouth() {
        Car car = new Car("A", 0, 0, 'S', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("0 0 S", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveOutOfBoundsWest() {
        Car car = new Car("A", 0, 0, 'W', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("0 0 W", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveOutOfBoundsNorth() {
        Car car = new Car("A", 9, 9, 'N', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("9 9 N", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveOutOfBoundsEast() {
        Car car = new Car("A", 9, 9, 'E', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("9 9 E", car.getCurrentPosition());
    }

    @Test
    public void testCarCommandsSequence() {
        Car car = new Car("A", 1, 2, 'N', "LFFRFFLFF", field);
        field.addCar(car);
        while (car.hasMoreCommands()) {
            car.executeNextCommand();
        }
        assertEquals("0 4 W", car.getCurrentPosition());
    }

    // Part 2 tests: Multiple cars and collision detection
    @Test
    public void testMultipleExecuteCommands() {
        Car carA = new Car("A", 1, 2, 'N', "F", field); // Should move to (1, 3)
        Car carB = new Car("B", 1, 3, 'E', "F", field); // Should move to (2, 3)
        field.addCar(carA);
        field.addCar(carB);
    
        while (field.anyCarHasMoreCommands()) {
            field.executeAllCarsOneStep();
            
            String posA = carA.getPosition();
            String posB = carB.getPosition(); 
    
            assertEquals("1,3", posA);
            assertEquals("2,3", posB);
        }
    
    }
    @Test
    public void testMultipleCarsNoCollision() {
        Car carA = new Car("A", 1, 2, 'N', "FFRFF", field);
        Car carB = new Car("B", 3, 3, 'E', "FFRFF", field);
        field.addCar(carA);
        field.addCar(carB);

        while (field.anyCarHasMoreCommands()) {
            String collision = field.executeAllCarsOneStep();
            if (!collision.equals("no collision")) {
                fail("Unexpected collision detected");
            }
        }

        assertEquals("3 4 E", carA.getCurrentPosition());
        assertEquals("5 1 S", carB.getCurrentPosition());
    }
    
    @Test
    public void testMultipleCarsCollision() {
        Car carA = new Car("A", 1, 1, 'N', "F", field); // Should move to (1, 2)
        Car carB = new Car("B", 1, 3, 'S', "F", field); // Should move to (1, 2)
        field.addCar(carA);
        field.addCar(carB);
    
        while (field.anyCarHasMoreCommands()) {
            String collision = field.executeAllCarsOneStep();
            
            // Check the positions after each step
            String posA = carA.getPosition(); // Should be "1,2" after the first move
            String posB = carB.getPosition(); // Should also be "1,2" after the first move
    
            if (collision != null) {
                assertEquals("A B\n1 2\n1", collision); // Correct expected position and step number
                return;
            }
    
            // These checks will ensure positions are correct before a collision is detected
            assertEquals("1,2", posA);
            assertEquals("1,2", posB);
        }
    
        fail("No collision detected when expected");
    }

    @Test
    public void testCollisionAvoidance() {
        Car carA = new Car("A", 0, 0, 'N', "FFFFFF", field);
        Car carB = new Car("B", 0, 1, 'N', "FFFFFF", field);
        field.addCar(carA);
        field.addCar(carB);

        while (field.anyCarHasMoreCommands()) {
            String collision = field.executeAllCarsOneStep();
            if (!collision.equals("no collision")) {
                fail("Unexpected collision detected");
            }
        }

        assertEquals("0 6 N", carA.getCurrentPosition());
        assertEquals("0 7 N", carB.getCurrentPosition());
    }

    @Test
    public void testCollisionDetectionAfterMultipleSteps() {
        Car carA = new Car("A", 1, 2, 'N', "FFRFFFFRRL", field);
        Car carB = new Car("B", 7, 8, 'W', "FFLFFFFFFF", field);
        field.addCar(carA);
        field.addCar(carB);

        while (field.anyCarHasMoreCommands()) {
            String collision = field.executeAllCarsOneStep();
            if (!collision.equals("no collision")) {
                assertEquals("A B\n5 4\n7", collision);
                return;
            }
        }

        fail("No collision detected when expected");
    }

    @Test
    public void threeWayCollision() {
        Car carA = new Car("A", 5, 0, 'N', "F", field);
        Car carB = new Car("B", 4, 1, 'E', "F", field);
        Car carC = new Car("C", 5, 2, 'S', "F", field);
        field.addCar(carA);
        field.addCar(carB);
        field.addCar(carC);

        while (field.anyCarHasMoreCommands()) {
            String collision = field.executeAllCarsOneStep();
            if (!collision.equals("no collision")) {
                assertEquals("A B C\n5 1\n1", collision);
                return;
            }
        }

        fail("No collision detected when expected");
    }
    @Test
public void testImmediateCollision() {
    Car carA = new Car("A", 0, 1, 'E', "F", field);
    Car carB = new Car("B", 2, 1, 'W', "F", field);
    field.addCar(carA);
    field.addCar(carB);

    while (field.anyCarHasMoreCommands()) {
        String collision = field.executeAllCarsOneStep();
        if (!collision.equals("no collision")) {
            assertEquals("A B\n1 1\n1", collision);
            return;
        }
    }

    fail("No collision detected when expected");
}

@Test
public void testSequentialCommandsWithCollision() {
    Car carA = new Car("A", 0, 0, 'N', "FFFFF", field);
    Car carB = new Car("B", 0, 5, 'S', "F", field);
    field.addCar(carA);
    field.addCar(carB);

    while (field.anyCarHasMoreCommands()) {
        String collision = field.executeAllCarsOneStep();
        if (!collision.equals("no collision")) {
            assertEquals("A B\n0 4\n4", collision);
            return;
        }
    }

    fail("No collision detected when expected");
}

@Test
public void testBoundaryCollision() {
    Car carA = new Car("A", 0, 0, 'E', "R", field);
    Car carB = new Car("B", 1, 0, 'W', "F", field);
    field.addCar(carA);
    field.addCar(carB);

    while (field.anyCarHasMoreCommands()) {
        String collision = field.executeAllCarsOneStep();
        if (!collision.equals("no collision")) {
            assertEquals("A B\n0 0\n1", collision);
            return;
        }
    }

    fail("No collision detected when expected");
}

@Test
public void testOverlappingPathsWithoutCollision() {
    Car carA = new Car("A", 1, 1, 'E', "FF", field);
    Car carB = new Car("B", 2, 1, 'W', "FF", field);
    field.addCar(carA);
    field.addCar(carB);

    while (field.anyCarHasMoreCommands()) {
        String collision = field.executeAllCarsOneStep();
        if (!collision.equals("no collision")) {
            fail("Unexpected collision detected");
        }
    }

    assertEquals("3 1 E", carA.getCurrentPosition());
    assertEquals("0 1 W", carB.getCurrentPosition());
}



}
