# ✈️ MyAircraftWar: Android Real-Time Multiplayer Combat

> A high-performance, object-oriented aircraft combat game built from scratch using Java and Android SDK.

![Java](https://img.shields.io/badge/Language-Java-orange)
![Android](https://img.shields.io/badge/Platform-Android-green)
![Architecture](https://img.shields.io/badge/Architecture-MVC-blue)
![Network](https://img.shields.io/badge/Network-WebSocket-red)

## 📖 Introduction

**MyAircraftWar** is not just a game. It is a comprehensive practice of **Object-Oriented Programming** and **Design Patterns**.

Beyond standard gameplay, this project implements a custom game engine architecture using `SurfaceView` for high-performance rendering and features a full-stack real-time multiplayer module powered by WebSockets. It serves as a robust example of solving concurrency, memory management, and network synchronization challenges in Android development.

## 🚀 Key Features

- **Real-Time Multiplayer:** Online matchmaking and low-latency state synchronization allowing players to battle against each other.
- **High-Performance Rendering:** Custom multi-threaded game loop using `SurfaceView` to ensure smoothness.
- **Memory Optimization:** Implemented **Object Pooling** for high-frequency entities (bullets, enemies, particles).
- **Data Persistence:** User profile and score tracking capabilities.

## 🛠️ Technical Architecture

### 1. Modular Game Engine
Designed a decoupled architecture to separate logic from rendering:
- **SurfaceView & Multi-threading:** Logic updates and rendering are handled in separate threads to prevent UI blocking.
- **Component-Based Design:** Easy to extend new enemy types or weapons without modifying the core engine.

### 2. Design Patterns Implemented
This project is a hands-on exploration of classic design patterns:
- **Singleton:** For managing global game state and resource loading.
- **Factory Pattern:** For dynamic generation of different enemy aircraft and items.
- **Observer Pattern:** For event handling (e.g., score updates, game over notifications).
- **Object Pool Pattern:** Reusing objects to reduce memory allocation overhead.
- **Strategy Pattern:** For interchangeable weapon firing behaviors.

### 3. Network Synchronization
- **WebSocket Protocol:** Established persistent connections for real-time bidirectional communication.
- **State Synchronization:** Optimized packet size and update frequency to handle player movement and projectile syncing smoothly.

