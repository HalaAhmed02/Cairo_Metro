🚇 Cairo Metro Route Planner
A Pure Kotlin Native console application that simplifies navigating the Cairo Metro system. It calculates the shortest path between stations, estimates travel time, and handles line transfers seamlessly.

🌟 Features
Smart Routing: Implementation of the BFS (Breadth-First Search) algorithm to find the shortest path in a graph-based metro network.

Interchange Detection: Automatically identifies when a user needs to switch lines (e.g., switching at Sadat or Shohadaa).

Trip Analytics: Provides:

Full list of stations along the route.

Total number of stations.

Estimated travel time based on station count.

Ticket fare calculation.

Clean Architecture: Built following SOLID principles with a dedicated Domain layer (UseCases, Models, and Repository interfaces).

🏗️ Architecture & Logic
The project is structured using Clean Architecture to ensure the core logic is independent of any external frameworks:

Domain Layer: Contains the business logic (FindRouteUseCase, CalculateFareUseCase).

Data Structures: The metro network is represented as an Adjacency List (Graph).

Algorithm: Uses BFS to guarantee the minimum number of stations between any two points.

🛠️ Tech Stack
Language: Kotlin

Target: Kotlin Native / JVM (Console Application)

Pattern: Repository Pattern & Use Cases

🚀 How to Run
Clone the repository:

Bash
git clone https://github.com/HalaAhmed02/Cairo_Metro.git
Build the project: (Assuming you use Gradle)

Bash
./gradlew build
Run the App:

Bash
./gradlew run
📸 Sample Output
Plaintext
Enter Start Station: Helwan
Enter Destination: Dokki

--- Route Found ---
Path: Helwan -> ... -> Sadat (Switch to Line 2) -> Opera -> Dokki
Total Stations: 22
Estimated Time: 45 mins
Fare: 15 EGP
💡 نصيحة زيادة:
في الـ GitHub، في الجزء الـ About (على اليمين)، حطي الكلمات دي كـ Tags:
kotlin clean-architecture bfs-algorithm data-structures console-app cairo-metro
