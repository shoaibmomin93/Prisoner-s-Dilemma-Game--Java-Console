/**
 * *********************************************************
 * Progammer: MOMIN,SHOAIB (Z1777526) 
 * 
 * Date Created: October 3, 2016
 *
 * Purpose: This is a Prisoners Dilemma game. The user and the computer can either remain silent
 * or betray each other. The application terminates by displaying the Winner for each game.
 ***********************************************************
 */
package pdgameapp;

import java.util.ArrayList;
import java.util.Date; // import required packages
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;

// This class provides an interface for user input. It reads in data and passes 
// other classes for data processing. Validation for Input is performed
// for the correct values .The output is displayed here as well.
public class PDGameApp {

    public static void main(String[] args) {

        boolean play = true;
        int strategy = 0;  // initialize variables used for validation
        int decision = 0;
        final String filename = "f1.txt";
        final int numberOfRounds = 5;
        Scanner sc = new Scanner(System.in);  // declare the scanner to read console input

        HashMap<String, GameStat> hashMap1 = new HashMap<>();  // hashmap is used to store the details of each game
        System.out.println("***Starting A Game of Prisoner's Dilemma ***" + "\n\n");
        while (play) {       // as long as the user wants to play a game
            String currentTime = (new Date()).toString(); // use date as key in the hashmap
            PDGame pd1 = new PDGame(filename);
            System.out.println("HERE ARE STRATEGIES AVAILABLE FOR THE COMPUTER\n");
            ArrayList<String> compStratergies = pd1.getStrategies();   // get the available stratergies from PDGame 
            ListIterator<String> LI = compStratergies.listIterator();  // display them using a ListIterator
            int index = 1;  // this is use to number the options that will be displayed
            while (LI.hasNext()) {  // as long as there is data in the next, run the loop
                String s = LI.next();
                System.out.println(index + " " + s);  // print it
                index++;
            }
            System.out.println("Select a strategy from above for the Computer to use in the " + numberOfRounds + " rounds : " + "\n");

            boolean strategyBool = true;
            while (strategyBool) {  // as long as  right input is entered, run the loop
                try {
                    strategy = sc.nextInt();
                    if (strategy >= 1 && strategy <= 3) {  // if the input is a number and within the range, quit the loop

                        strategyBool = false;

                    } else {   // else, ask the user to enter a valid input
                        System.out.println("Choose either 1 or 2 or 3 (Stratergy)");
                    }

                } catch (InputMismatchException e) {  // if the input is not a number, ask for a valid input
                    System.out.println("Choose either 1 or 2 or 3 (Stratergy)");
                    sc.nextLine();  // clear the buffer

                }

            }  // end stratergyBool while loop
            pd1.setStrategy(strategy);  // set the stratergy in PDGame
            int i = 0;   // each game has 5 rounds
            while (i < numberOfRounds) {
                System.out.println("\nBEGIN A ROUND - Here are your 2 choices");  // display the choices
                System.out.println("1. Cooperate and remain silent.");
                System.out.println("2. Betray and testify against. \n");
                System.out.println("What is your decision for this round?");
                boolean decisionBool = true;  // this variable is used to check if the input for decision is right
                while (decisionBool) {
                    try {   // used to check input mismatch
                        decision = sc.nextInt();
                        if (decision == 1 || decision == 2) {  // if it is a valid choice, quit the loop
                            decisionBool = false;
                        } else {  // else display a message and ask the user to reenter a valid number
                            System.out.println("Choose either 1 or 2 (Choice)");
                        }

                    } catch (InputMismatchException e) {  // if the input is not a number
                        System.out.println("Choose either 1 or 2 (Choice)"); // display the message
                        sc.nextLine();  // clear the buffer
                    }

                } // end while of decisionBool

                String roundResult = pd1.playRound(decision); // returns the result of each round
                System.out.println(roundResult); //display the result
                i++;
            }  // end while..end of a game
            System.out.println("\nEND OF ROUNDS, GAME OVER --GAME STATS --");

            System.out.println(pd1.getScores()); // display the prison sentence for both user and computer

            GameStat gstat;
            gstat = pd1.getStats();  // get that Gamestat object from PDGame get methods
            hashMap1.put(currentTime, gstat);  // update the hashmap

            System.out.println("\n --Would you like to play another game (y/n)? \n"); // ask the user if he wants to continue playing
            String playAgain = sc.next();
            while ((!playAgain.equalsIgnoreCase("y")) && (!playAgain.equalsIgnoreCase("n"))) { // if a character is not y or n
                System.out.println("Choose either y or n"); // accept a valid input
                playAgain = sc.next();
            } // end new game while
            if (playAgain.equalsIgnoreCase("n")) { // if the input is n, quit the Game loop
                play = false;
            }

        }  // end of game loop

        System.out.println("The summary of all the games are : \n");  // display the summary of all games
        Set<String> keySet = hashMap1.keySet();  // get the keyset of the hashmap in a Set
        for (String searchKey : keySet) {  // use foreach to get every key and display the GameStat values
            GameStat resultStat = hashMap1.get(searchKey);
            System.out.println(searchKey + " : Winner is " + resultStat.getWinner() + ". The computer used " + resultStat.getCompStrategy());
        }
    } // end main

}  // end class PDGame
