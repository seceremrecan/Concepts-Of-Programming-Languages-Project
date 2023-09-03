# Concepts-Of-Programming-Languages-Project

This C program simulates a game where different colonies engage in battles and resource management. Each colony is represented by a set of parameters, including population, food supply, and strategies. The game continues until only one colony remains.

## Project Structure

The project is designed with modularity and object-oriented principles in mind. It includes the following components:

- Tactics: Abstract class representing different battle strategies.
- Two Different Tactics Implementations: For example, ATactic and BTactic.
- Production Techniques: Abstract class representing various resource production methods.
- Two Different Production Techniques Implementations: For example, AProduction and BProduction.
- Colonies: Represents a colony with population, food supply, and other attributes.
- Game Engine: Manages the game loop, battles, and colony interactions.
- Test Program: Demonstrates the functionality of the system.

## Game Rules

- Colonies are initialized with random population sizes and food supplies.
- Each colony has its own battle tactics and resource production techniques.
- Colonies engage in battles based on their tactics, with outcomes determined by random factors.
- After each turn, populations increase by 20%, and food supplies decrease by half the current population.
- Colonies with zero or negative population or food supply are eliminated from the game.
- The game continues until only one colony remains.

## Usage

-> When you run the program, please do not enter a number by leaving a space at first, if you do, the program will terminate.

-> When you run the program, please enter only the number, otherwise the program will terminate.

-> If the program does not work, compile it again.

-> go to the dist file run the following command: "java -jar Program.jar".
