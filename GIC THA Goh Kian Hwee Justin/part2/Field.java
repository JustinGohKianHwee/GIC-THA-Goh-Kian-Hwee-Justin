import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Field {
    private int width;
    private int height;
    private int stepNo;
    private Map<String, Car> cars; 
    private Set<String> occupiedPositions;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.cars = new HashMap<>();
        this.occupiedPositions = new HashSet<>();
        this.stepNo = 0;
    }

    public boolean addCar(Car car) {
        String position = car.getPosition();
        if (occupiedPositions.contains(position)) {
            return false; 
        }
        if (isPositionFree(car.getX(), car.getY())) {
            occupiedPositions.add(position);
            cars.put(car.getId(), car);
            return true;
        }
        return false;
    }

    public boolean isPositionFree(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    public boolean updateCarPosition(Car car, int newX, int newY) {
        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return false; // Out of bounds
        }
        String newPosition = newX + "," + newY;
        String oldPosition = car.getPosition();

        //System.out.println("Before Update - Occupied Positions: " + occupiedPositions);

        occupiedPositions.remove(oldPosition);
        car.setPosition(newX, newY);
        occupiedPositions.add(newPosition);

        return true;
    }

    public boolean anyCarHasMoreCommands() {
        for (Car car : cars.values()) {
            if (car.hasMoreCommands()) {
                return true;
            }
        }
        return false;
    }

    public String executeAllCarsOneStep() {
        stepNo++;
        Map<String, Set<String>> newPositions = new HashMap<>();
        Set<String> collisionIds = new HashSet<>();
        String collisionDetails = null;

        for (Car car : cars.values()) {
            //System.out.println("Car " + car.getId() + " initial position: " + car.getPosition()); 

            car.executeNextCommand();
            String newPosition = car.getPosition();
            //System.out.println("Car " + car.getId() + " moved to: " + newPosition);

            // Collect positions for potential collision detection
            if (newPositions.containsKey(newPosition)) {
                newPositions.get(newPosition).add(car.getId());
                collisionIds.add(car.getId());
                collisionIds.addAll(newPositions.get(newPosition));
            } else {
                Set<String> carSet = new HashSet<>();
                carSet.add(car.getId());
                newPositions.put(newPosition, carSet);
            }
        }

        // Check if any positions have more than one car ID, indicating a collision
        for (Map.Entry<String, Set<String>> entry : newPositions.entrySet()) {
            if (entry.getValue().size() > 1) {
                collisionDetails = String.join(" ", entry.getValue()) + "\n" +
                                  entry.getKey().replace(",", " ") + "\n" + stepNo;
                break; // Found a collision, no need to continue
            }
            else {
                collisionDetails = "no collision";
            }
        }

        // Update the occupied positions after checking for collisions
        occupiedPositions.clear();
        for (String position : newPositions.keySet()) {
            occupiedPositions.add(position);
        }

        return collisionDetails;
    }
}
