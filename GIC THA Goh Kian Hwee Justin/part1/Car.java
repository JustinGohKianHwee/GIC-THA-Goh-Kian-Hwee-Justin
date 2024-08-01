public class Car {
    private int x;
    private int y;
    private char direction;
    private Field field;
    private String commands;
    private int commandIndex;

    public Car(int x, int y, char direction, String commands, Field field) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.commands = commands;
        this.field = field;
        this.commandIndex = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPosition() {
        return x + "," + y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getCurrentPosition() {
        return x + " " + y + " " + direction;
    }

    public boolean hasMoreCommands() {
        return commandIndex < commands.length();
    }

    public void executeNextCommand() {
        if (commandIndex < commands.length()) {
            char command = commands.charAt(commandIndex);
            commandIndex++;
            switch (command) {
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
                    break;
                case 'F':
                    moveForward();
                    break;
            }
        }
    }

    private void turnLeft() {
        switch (direction) {
            case 'N':
                direction = 'W';
                break;
            case 'W':
                direction = 'S';
                break;
            case 'S':
                direction = 'E';
                break;
            case 'E':
                direction = 'N';
                break;
        }
    }

    private void turnRight() {
        switch (direction) {
            case 'N':
                direction = 'E';
                break;
            case 'E':
                direction = 'S';
                break;
            case 'S':
                direction = 'W';
                break;
            case 'W':
                direction = 'N';
                break;
        }
    }

    private void moveForward() {
        int newX = x;
        int newY = y;

        switch (direction) {
            case 'N':
                newY++;
                break;
            case 'E':
                newX++;
                break;
            case 'S':
                newY--;
                break;
            case 'W':
                newX--;
                break;
        }

        if (field.updateCarPosition(this, newX, newY)) {
            x = newX;
            y = newY;
        }
    }
}
