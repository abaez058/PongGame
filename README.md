# 🏓 Pong Game (Java Swing)

A modern take on the classic **Pong** arcade game built in **Java Swing**.  
This project demonstrates GUI programming, event handling, and real-time game loops using core Java libraries.

---

## 🎮 Features

- Two-player **Pong gameplay**
- Dynamic **ball and paddle movement**
- **Speed increases** after paddle hits for added challenge
- **Full-screen window** for immersive play
- Simple, smooth graphics using `JPanel` and `Graphics`
- **Score tracking** for both players
- Responsive **keyboard controls**

---

## 🕹️ Controls

| Player | Move Up | Move Down |
|:--------|:--------|:----------|
| Player 1 | `W` | `S` |
| Player 2 | `↑` (Up Arrow) | `↓` (Down Arrow) |

---

## 🧠 Technical Overview

- **Language:** Java  
- **Libraries Used:** `java.awt`, `javax.swing`  
- **Module:** `PongGame`  
- **Game Loop:** Managed via `javax.swing.Timer` (5ms interval)  
- **Event Handling:** `KeyListener` for paddle control  

The main logic for ball movement, collision detection, and scoring is handled in `actionPerformed()`.  
Rendering occurs inside a custom `PongPanel` subclass that draws paddles, ball, and scores.

---

## ⚙️ Setup & Run

### Prerequisites
- **Java 17+** installed  
- An IDE (like IntelliJ, VS Code, or Eclipse) or terminal access

### Steps

```bash
# Clone this repository
git clone https://github.com/<your-username>/PongGame.git
cd PongGame

# Compile the module
javac -d out src/pong/*.java module-info.java

# Run the game
java -p out -m PongGame/pong.PongGame
