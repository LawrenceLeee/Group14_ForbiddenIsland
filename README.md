

# Forbidden Island Multiplayer Cooperative Card Game

## Project Introduction

This project implements an online multiplayer version of the classic cooperative board game "Forbidden Island" using JavaFX and Netty.It adopts a client-server architecture and supports multiple players to play together online, with real-time state synchronization and command communication. The project emphasizes object-oriented design and modular development.

---

## Implemented Features

- Multiplayer: Supports 2 to 4 players to team up and play online.
- Core Gameplay Reproduction: completely restoration reproduces the basic rules and victory/defeat conditions of the physical board game.
- Intelligent Command Processing: Uses the command pattern and message dispatch mechanism to handle player operations.
- Animation and Interaction: Implements user interfaces and key animation effects based on JavaFX.
- Network Communication: Constructs a communication system between the server and client using the Netty framework.

---

## Usage Instructions

### Environment Requirements

- JDK 17+
- Maven 3.8+
- JavaFX SDK（can be configured through IntelliJ）
- Recommended IDE：IntelliJ IDEA

### Launch Method

1. **Clone and Import the project**

   ```bash
   git clone https://github.com/yourusername/forbidden-island.git
   ```

​	**Alternatively**：Download and unzip the project zip file.

​	Use IntelliJ IDEA to select "Import Project" → Choose the unzipped directory → Import as a Maven project.

2. **build project**

   ```bash
   mvn clean install
   ```

3. **Run the Server Side**

   Run `GameServer.java` in the IDE to start the server.

4. **Run the Client Side**

​	Run `MainApp.java` in the IDE to start the JavaFX interface.

### Online Instructions

- Online play requires being on the same network.
- After the room owner starts the game, the console will send a room number, and other members can join the room through the room number.
- Wait for the room owner to start the game.

## Project Structure Description

```nginx
C:.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/bdic/forbiddenisland/
│   │   │       ├── controller/ # Handles user requests and turn logic
│   │   │       │   ├── CommandManager.java
│   │   │       │   ├── RoomEventListener.java
│   │   │       │   ├── RoomEventManager.java
│   │   │       │   ├── SessionManager.java
│   │   │       │   └── commands/
│   │   │       │       ├── CreateRoomCommand.java
│   │   │       │       ├── GameCommand.java
│   │   │       │       ├── GiveCommand.java
│   │   │       │       ├── JoinCommand.java
│   │   │       │       ├── JoinSessionCommand.java
│   │   │       │       ├── MoveCommand.java
│   │   │       │       ├── NextTurnCommand.java
│   │   │       │       ├── ShoreUpCommand.java
│   │   │       │       ├── StartGameCommand.java
│   │   │       │       └── UpdateTileCommand.java
│   │   │       │
│   │   │       ├── model/ # Core data structures and business logic
│   │   │       │   ├── Card.java
│   │   │       │   ├── Deck.java
│   │   │       │   ├── GameModel.java
│   │   │       │   ├── ModelChangeListener.java
│   │   │       │   ├── Player.java
│   │   │       │   ├── Profession.java
│   │   │       │   ├── Tile.java
│   │   │       │   ├── TileStatus.java
│   │   │       │   ├── TreasureCard.java
│   │   │       │   ├── TreasureCardType.java
│   │   │       │   └── TreasureDeck.java
│   │   │       │
│   │   │       ├── network/ #  Client message processing, Netty communication
│   │   │       │   ├── ClientHandler.java
│   │   │       │   ├── JsonUtil.java
│   │   │       │   ├── Message.java
│   │   │       │   ├── MessageType.java
│   │   │       │   ├── NetworkManager.java
│   │   │       │   └── handler/
│   │   │       │       ├── MessageHandler.java
│   │   │       │       ├── MessageHandlerFactory.java
│   │   │       │       └── impl/
│   │   │       │           ├── GiveHandler.java
│   │   │       │           ├── JoinHandler.java
│   │   │       │           ├── MoveHandler.java
│   │   │       │           ├── NextTurnHandler.java
│   │   │       │           ├── RoomSetupHandler.java
│   │   │       │           ├── ShoreUpHandler.java
│   │   │       │           ├── StartGameHandler.java
│   │   │       │           └── UpdateTileHandler.java
│   │   │       │
│   │   │       ├── server/ # Room management and request scheduling
│   │   │       │   ├── GameRoomManager.java
│   │   │       │   ├── GameServer.java
│   │   │       │   └── ServerHandler.java
│   │   │       │
│   │   │       ├── util/ # Image, animation, and prompt management
│   │   │       │   ├── Animations.java
│   │   │       │   ├── ImageFactory.java
│   │   │       │   ├── ImageType.java
│   │   │       │   └── Notifier.java
│   │   │       │
│   │   │       └── view/ # JavaFX interface controllers
│   │   │           ├── GameController.java
│   │   │           ├── GameView.java
│   │   │           ├── StartMenuController.java
│   │   │           └── StartMenuView.java
│   │   │
│   │   └── resources/ # Static resource directory
│   │       └── edu/bdic/forbiddenisland/
│   │           └── images/
│   │               └── back/ # Image resources
│
└── README.md #  Project description file
```

## Function Implementation and Advantages

### Architecture Function Implementation Description

---

| Architecture Layer              | Package Name                                         | Function Implementation Description                          |
| ------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------ |
| **Model Layer**                 | `edu.bdic.forbiddenisland.model`                     | Defines the core data structures of the game, such as `Player` (player), `Tile` (tile), `Deck` (deck), `GameModel` (game state centralized management). Constructs the foundation of game business logic, implements changes and validation of roles, cards, and map states, and stores all game states. |
| **Controller Layer**            | `edu.bdic.forbiddenisland.controller`                | The controller accepts actions generated by user interactions in the View layer, converts them into commands (such as `MoveCommand`, `GiveCommand`), and sends them to the server through `CommandManager`. It also listens for game state changes returned by the server and passes them back to the View layer to update the interface. Uses the command pattern (Command Pattern) to keep the logic clear and extensible. |
| **Network Communication Layer** | `edu.bdic.forbiddenisland.network`                   | Uses Netty to encapsulate the communication mechanism. `NetworkManager` manages client connections, `MessageHandler` handles message sending and receiving. The sub-package `handler.impl` implements the processing of specific commands (such as `MoveHandler`, `GiveHandler`), used for the data interaction logic between the client and the server. The underlying layer uses `Message` and `MessageType` to implement serialized communication. |
| **Server Management Layer**     | `edu.bdic.forbiddenisland.server`                    | Contains `GameServer`, `GameRoomManager`, `ServerHandler`, used for managing game rooms, listening for client connections, scheduling command logic, and broadcasting updates. The core logic processing is completed on the server side to ensure fairness and state consistency. |
| **Utility Classes**             | `edu.bdic.forbiddenisland.util`                      | Implements general functions, such as `Animations.java` handles card and map sinking animations; `ImageFactory` and `ImageType` manage image resources; `Notifier.java` implements event pop-ups and prompt auxiliary UI functions. |
| **View Layer**                  | `edu.bdic.forbiddenisland.view`                      | Responsible for displaying the game interface, map, buttons, and other components, containing JavaFX-written UI classes, such as `GameView.java`, `StartMenuView.java`, responsible for user interaction, and centralizing all graphical interface logic, and updating the interface state through listening to the Controller layer. |
| **Resource Management**         | `src/main/resources/edu/bdic/forbiddenisland/images` | Stores all image resources, such as tile images, character portraits, card back images, etc., providing |

#### Process Overview

1. **Game launch**

   - The client displays the start menu, and the player creates or joins a room
   - The client displays the start menu, and the player creates or joins a room

2. **Room Management and Game Start**

   - When the room owner creates a room, a room number is automatically generated in the console, and other players can join the room by entering the room number.
   - After players join the room and are ready, the room owner initiates the `StartGameCommand` to begin the game

3. **Game Loop**

   - Each player takes turns to perform up to 3 actions per turn (move, shore up, collect treasures, trade cards, etc.).
   - When a player clicks "Move," they can move to an adjacent tile (unless they have a special skill).
   - Clicking "Shore Up" allows the player to restore the submerged state of the tile they are on.
   - Clicking "Give" enables card trading between players.
   - Clicking "Capture" allows the player to draw a treasure card.
   - Clicking "Next Turn" passes the turn to the next player.
   - Clicking "Use Skill" allows the player to use their special ability.
   - These operations are converted into corresponding commands by the Controller and sent to the server for processing.
   - The server updates the game state and broadcasts it to the clients to refresh the UI.

4. **Card System and State Synchronization**

   - The server manages the drawing and event handling of treasure cards and flood cards.
   - Tile sinking and water level rising events are synchronized to the clients through the `UpdateTileCommand`.

5. **Victory or Defeat Conditions**

   - Victory Condition: Players collect all 4 treasures and use the helicopter card to escape the island.
   - Defeat Conditions: The treasure tile sinks, the helicopter pad tile sinks, the water level reaches the top, or players are trapped.
   - After the game ends, the interface returns to the lobby.


---

### Architecture Advantages

**The project adopts the MVC architecture combined with a client-server model**, offering the following benefits:

- **Clear Module Separation and Responsibilities**
  - The `Model` layer manages the game's business data (e.g., players, map, cards), facilitating logic reuse and expansion.
  - The `View` layer, built with JavaFX, separates the interface logic from the business logic, enhancing maintainability.
  - The `Controller` layer coordinates between the `Model` and `View`, managing player operations and command encapsulation.
  - The `Server` layer handles core server-side logic, such as room management and state synchronization.
  - The `Network` layer and `Server` layer are responsible for client-server communication logic, implementing stable and efficient message transmission with Netty.
  - The `Util` utility classes centralize functions like animations, image resources, and prompts, improving code reusability.
- **High Extensibility**
  - Game commands are encapsulated into `Command` classes; adding new commands only requires extending the command and handler.
  - Event listening and message processing use a unified management mechanism, making it easy to expand more network behaviors.
  - Factory patterns are used to manage different message handlers and resource loading, enhancing code decoupling.
- **Optimized User Experience**
  - Animations and dynamic UI elements enhance the interaction experience.
  - Separation of view controllers facilitates the development and switching of multiple interfaces

## Acknowledgments

 **This project was created by the UCD team for educational purposes only.**

We would like to express our gratitude to Dr. Alzubair Hassan and the teaching assistants for their guidance and support throughout this period.
