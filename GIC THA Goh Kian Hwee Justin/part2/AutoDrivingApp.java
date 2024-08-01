import java.util.Scanner;

public class AutoDrivingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        int width = sc.nextInt();
        int height = sc.nextInt();
        sc.nextLine(); 
        //System.out.println("Field dimensions: " + width + "x" + height);

        Field field = new Field(width, height);

        // Reading car details
        while (sc.hasNextLine()) {
            String carId = sc.nextLine().trim(); 
            if (carId.isEmpty()) continue; 
            //System.out.println("Car ID: " + carId);

            if (!sc.hasNextInt()) break;  
            int startX = sc.nextInt();
            int startY = sc.nextInt();
            char startDirection = sc.next().charAt(0);
            sc.nextLine(); 

            
            String commands = sc.nextLine().trim();
            //System.out.println("StartX: " + startX + " StartY: " + startY + " Direction: " + startDirection);
            //System.out.println("Commands: " + commands);

            // Initialize the car and add to the field
            Car car = new Car(carId, startX, startY, startDirection, commands, field);
            if (!field.addCar(car)) {
                System.out.println("Car " + carId + " could not be placed due to collision or boundary issue at: " + car.getCurrentPosition());
                if (sc.hasNextLine()) sc.nextLine(); 
                continue;  // Skip to next car
            }

            if (sc.hasNextLine()) sc.nextLine();
        }

        sc.close();

        
        while (field.anyCarHasMoreCommands()) {
            String collision = field.executeAllCarsOneStep();
            if (!collision.equals("no collision")) {
                System.out.println(collision);
                return; 
            }
        }

        System.out.println("no collision");
    }
}
