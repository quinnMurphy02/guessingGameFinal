package guessingGameFinal1;

//Programmer: Quinn Murphy
//Date: 9/24/2022
//Lab #1: Guessing Game
//References: Old programs, book, ppt slides
//This basically is just my old program revamped.
//My main is now one line long, and I have 
//A much better constructor system. The main
//difference in this vs my old one was I used
//an array to store my score board values, as
//previously I had half the game in main in
//order to keep track of variables in main. So now I 
//simply pass one array into two other methods.
//I also kept the methods static, and just return
//the array of values. Also I have fixed most of
//the text printing problems using several techniques.

//real code here

import java.util.Random;
import java.util.Scanner; // importing scanner and random number generator
//REAL CODE DO NOT TOUCH
//this was the one

public class guessingGame {

	public static final int MAX = 100;

	public static void main(String[] args) { // main
		new guessingGame();
	}

	public guessingGame() { // constructor with methods
		double guessesPerGame = 0;
		double totalGuesses = 0;
		double totalGamesPlayed = 0;
		double bestGame = 99; // I had to make a placeholder value, just so
								// user first game will be best game.
		double[] endScreenAnswers = new double[4]; // just 4 values in here
		endScreenAnswers[0] = totalGamesPlayed;
		endScreenAnswers[1] = totalGuesses;
		endScreenAnswers[2] = guessesPerGame;
		endScreenAnswers[3] = bestGame;

		introGame();
		playGame(endScreenAnswers); // pass in array to method
		resultsScreen(endScreenAnswers);

	}

	public static double[] playGame(double[] endScreenAnswers) {
		Scanner scan = new Scanner(System.in); // import stuff
		Random random = new Random();
		int answer = random.nextInt(MAX) + 1; // compensating because java will start counting at zero
		double userGuess = 0;
		boolean gameOver = false; // this is how the while loop will know when to stop
		String againChoice = ""; // dont worry about string in here, the user will input it. Java made me define
									// the string, or else it would error.
		double amountOfGuesses = 1;
		boolean fullGameOver = false; // full game over means user didnt type yes

		while (!fullGameOver) {

			while (!gameOver) { // game will end when true

				System.out.print("Your guess? ");
				userGuess = scan.nextInt(); // give user input to guess

				if (userGuess == answer) { // pretty simple here, if user gets it right, the playGame method ends
					System.out.println();
					String guessDecimal = String.format("%.0f", amountOfGuesses); // formats amount of guesses... no
																					// decimal

					System.out.printf("You got it right in " + guessDecimal + " tries! \n");

					if (amountOfGuesses < endScreenAnswers[3]) { // best game code here.
						endScreenAnswers[3] = amountOfGuesses; // if current games guess count > stored count...
					} else { 									// stored count becomes current count
						endScreenAnswers[3] = endScreenAnswers[3];
					}

					endScreenAnswers[1] = amountOfGuesses + endScreenAnswers[1]; // adding to amount of guesses
																					// for scoreboard

					endScreenAnswers[0]++; // total games played increases when user gets answer right.

					gameOver = true; // end of first while loop

				}

				else if (userGuess > answer) {
					System.out.println("Lower!!");
					amountOfGuesses++;
					gameOver = false; // use of booleans to prevent
										// infinite loop
				}

				else {
					System.out.println("Higher!!");
					amountOfGuesses++;
					gameOver = false;

				}

			}

			System.out.println("Wanna play again?? Type Y/y...");
			againChoice = scan.next();

			if (againChoice.contains("y") || againChoice.contains("Y")) {
				answer = random.nextInt(MAX) + 1; // compensating because java will start counting at zero,
													// resetting random number

				amountOfGuesses = 1; // reset amount of guesses
				System.out.println("I'm thinking of a number between 1 and 100... "); // dialogue
				gameOver = false;

			}

			else {
				System.out.println("Okay!");

				fullGameOver = true;// end main while loop

			}

		}

		return endScreenAnswers;

	}

	public static void resultsScreen(double[] endScreenAnswers) { // calculates guesses per game, then prints all
																	// statistics

		double guessesPerGame = endScreenAnswers[1] / endScreenAnswers[0];
		int guessesNoDecimal = (int) endScreenAnswers[1]; // convert float to int for format
		int gamesNoDecimal = (int) endScreenAnswers[0];
		int bestGameNoDecimal = (int) endScreenAnswers[3];
		System.out.println("Total Guesses = " + guessesNoDecimal);
		System.out.println("Total Games Played = " + gamesNoDecimal);
		System.out.println("Avg Guesses/Game = " + guessesPerGame);
		System.out.println("Best Game = " + bestGameNoDecimal);
	}

	public static void introGame() { // just types the introduction to the game, nothing else
		System.out.println("This program allows you to play a guessing game.");
		System.out.println("I will think of a number between 1 and");
		System.out.println("100 and will allow you to guess until");
		System.out.println("you get it. For each guess, I will tell you");
		System.out.println("whether the right answer is higher or lower");
		System.out.println("than your guess.");
		System.out.println();
		System.out.println();
	}

}