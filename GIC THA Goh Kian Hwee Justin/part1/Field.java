import java.util.HashSet;
import java.util.Set;

public class Field {
    private int width;
    private int height;
    private Set<String> occupiedPositions; 

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.occupiedPositions = new HashSet<>();
    }

    public boolean addCar(Car car) {
        int carX = car.getX();
        int carY = car.getY();
        if (carX < 0 || carX >= width || carY < 0 || carY >= height) {
            // Car is out of bounds
            return false;
        }
        String position = car.getPosition();
        if (occupiedPositions.contains(position)) {
            return false; 
        }
        occupiedPositions.add(position);
        return true;
    }

    public boolean isPositionFree(int x, int y) {
        String position = x + "," + y;
        if (x >= this.width || y >= this.height) {
            return false;
        }
        return !occupiedPositions.contains(position);
    }

    public boolean updateCarPosition(Car car, int newX, int newY) {
        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return false; // Out of bounds
        }
        String newPosition = newX + "," + newY;
        if (occupiedPositions.contains(newPosition)) {
            return false; // Position already occupied
        }
        occupiedPositions.remove(car.getPosition());
        car.setPosition(newX, newY);
        occupiedPositions.add(newPosition);
        return true;
    }

    public void executeAllCarsOneStep(Car[] cars) {
        for (Car car : cars) {
            car.executeNextCommand();
        }
    }
}
