import java.util.Scanner;

public class AutoDrivingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            
            if (!sc.hasNextInt()) break;  
            int width = sc.nextInt();
            int height = sc.nextInt();
            sc.nextLine(); 

            Field field = new Field(width, height);

            
            int startX = sc.nextInt();
            int startY = sc.nextInt();
            char startDirection = sc.next().charAt(0);
            sc.nextLine();

            
            if (startX < 0 || startX >= width || startY < 0 || startY >= height) {
                System.out.println("Initial position out of bounds: " + startX + " " + startY);
                
                if (sc.hasNextLine()) sc.nextLine();
                continue; 
            }

            
            String commands = sc.nextLine().trim();

            
            Car car = new Car(startX, startY, startDirection, commands, field);
            if (!field.addCar(car)) {
                System.out.println("Car could not be placed due to collision or boundary issue at: " + car.getCurrentPosition());
                if (sc.hasNextLine()) sc.nextLine();
                continue;  
            }

            while (car.hasMoreCommands()) {
                field.executeAllCarsOneStep(new Car[]{car});
            }

            System.out.println(car.getCurrentPosition());

            if (sc.hasNextLine()) sc.nextLine();
        }

        sc.close();
    }
}
