Development Timeline
Project Start Date: 26/08/24

Date: 27/08/24
Details: Initial planning and setup for the AutoDrivingApp project. Defined the problem statement and identified key components required for implementation.
Part 1 Development:

Start Date: 27/08/24
End Date: 27/08/24
Details:
Developed core functionality for handling a single car's movement on a grid.
Implemented basic command parsing (L, R, F) and movement logic.
Set up the Field and Car classes to handle the environment and car behavior.
Initial testing and debugging of basic movement and boundary checks.
Part 2 Development:

Start Date: 29/08/24
End Date: 30/08/24
Details:
Extended functionality to handle multiple cars and detect collisions.
Implemented simultaneous command execution for multiple cars.
Developed logic for detecting collisions, including scenarios where multiple cars attempt to occupy the same position.
Comprehensive testing including edge cases and various scenarios for collision detection.
Additional debugging and optimization.

Assumptions and Design Decisions
Car Movement and Commands:

Each car follows a strict sequence of commands provided at initialization.
Commands include 'L' (left turn), 'R' (right turn), and 'F' (forward movement).
Cars can start facing any cardinal direction (N, E, S, W).
Grid Boundaries and Occupied Positions:

The grid is defined by specified width and height, starting from (0,0) to (width-1, height-1).
Cars cannot move outside the grid boundaries. Any command that would move a car out of bounds is ignored, and the car remains in its current position.
Cars occupy discrete grid positions identified by their coordinates (x, y).
Collision Detection and Handling:

Collisions are detected based on simultaneous movement commands leading to the same grid position.
If multiple cars move to the same position at the same time, a collision is reported. The system identifies the involved cars and the collision coordinates.
The project assumes simultaneous movement, meaning all cars execute their respective commands for a given time step before checking for collisions.
If a collision is detected, subsequent commands for the involved cars are ignored, and the system reports the collision. 

However, for this project, scenarios where car A and B swap positions will not be considered a collision. This is because of the explicit definition of collision given in the instructions being ONLY that collisions occur when they move into the same position.
Would clarify to address this for further improvement, as order of car movement (simultaneous or ordered) will have to be taken into account and changed.

Edge Case Handling:

The system is tested against various edge cases, including:
Cars starting at the same position.
Cars moving towards each other.
Cars following overlapping paths at different times.
Cars attempting to move out of bounds.
Testing and Validation
Unit Tests:

Comprehensive unit tests were developed to validate the functionality of both Part 1 and Part 2.
Tests cover basic movement, boundary checks, command parsing, and complex collision scenarios.
Special attention was given to handling simultaneous movements and ensuring accurate collision detection.
Integration Tests:

Integration tests ensure that the system functions correctly when multiple cars are introduced.
Tests verify the correctness of collision reporting and final positions of all cars.

Conclusion and Future Improvements
The AutoDrivingApp successfully handles multiple cars on a grid, detecting collisions based on simultaneous movement.
Future improvements may include:
Introducing varied command types for more complex movement patterns.
Implementing priority rules for movement and collision handling.
Clarifying definition of collision (expanding the definition)