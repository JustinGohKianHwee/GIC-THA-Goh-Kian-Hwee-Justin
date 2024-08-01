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
        Car car = new Car(1, 2, 'N', "FFRFF", field);
        assertTrue(field.addCar(car));
    }

    @Test
    public void testAddCarOutOfBounds() {
        Car car = new Car(10, 10, 'N', "FFRFF", field);
        assertFalse(field.addCar(car));
    }

    @Test
    public void testPositionCheck() {
        assertTrue(field.isPositionFree(1, 1)); 
        assertFalse(field.isPositionFree(10, 10)); 
    }

    @Test
    public void testUpdateCarPositionWithinBounds() {
        Car car = new Car(1, 2, 'N', "FFRFF", field);
        field.addCar(car);
        assertTrue(field.updateCarPosition(car, 2, 2));
    }

    @Test
    public void testUpdateCarPositionOutOfBounds() {
        Car car = new Car(1, 2, 'N', "FFRFF", field);
        field.addCar(car);
        assertFalse(field.updateCarPosition(car, 10, 10)); 
    }

    @Test
    public void testCarMoveNorth() {
        Car car = new Car(5, 5, 'N', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 6 N", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveEast() {
        Car car = new Car(5, 5, 'E', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("6 5 E", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveSouth() {
        Car car = new Car(5, 5, 'S', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 4 S", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveWest() {
        Car car = new Car(5, 5, 'W', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("4 5 W", car.getCurrentPosition());
    }

    // Turn left tests
    @Test
    public void testCarTurnLeftFromNorth() {
        Car car = new Car(5, 5, 'N', "L", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 W", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnLeftFromWest() {
        Car car = new Car(5, 5, 'W', "L", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 S", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnLeftFromSouth() {
        Car car = new Car(5, 5, 'S', "L", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 E", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnLeftFromEast() {
        Car car = new Car(5, 5, 'E', "L", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 N", car.getCurrentPosition());
    }

    // Turn right tests
    @Test
    public void testCarTurnRightFromNorth() {
        Car car = new Car(5, 5, 'N', "R", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 E", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnRightFromEast() {
        Car car = new Car(5, 5, 'E', "R", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 S", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnRightFromSouth() {
        Car car = new Car(5, 5, 'S', "R", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 W", car.getCurrentPosition());
    }

    @Test
    public void testCarTurnRightFromWest() {
        Car car = new Car(5, 5, 'W', "R", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("5 5 N", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveOutOfBoundsSouth() {
        Car car = new Car(0, 0, 'S', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("0 0 S", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveOutOfBoundsWest() {
        Car car = new Car(0, 0, 'W', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("0 0 W", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveOutOfBoundsNorth() {
        Car car = new Car(9, 9, 'N', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("9 9 N", car.getCurrentPosition());
    }

    @Test
    public void testCarMoveOutOfBoundsEast() {
        Car car = new Car(9, 9, 'E', "F", field);
        field.addCar(car);
        car.executeNextCommand();
        assertEquals("9 9 E", car.getCurrentPosition());
    }

    @Test
    public void testCarCommandsSequence() {
        Car car = new Car(1, 2, 'N', "LFFRFFLFF", field);
        field.addCar(car);
        while (car.hasMoreCommands()) {
            car.executeNextCommand();
        }
        assertEquals("0 4 W", car.getCurrentPosition());
    }
}
