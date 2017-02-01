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
//Keeps track of the player and computer sentence and the number of rounds played. 
//Also has a string to record the strategy the computer used during the game. 

public class GameStat {

    String compStrategy;   // declare the required variables
    int userSentence, compSentence;

    // constructor initializes the variables
    public GameStat() {
        userSentence = 0;
        compSentence = 0;
    }

    // update method is used to keep a track of total prison sentence .
    //user and computer prison sentences are incremented
    public void update(int userSentence, int compSentence) {
        this.userSentence += userSentence;
        this.compSentence += compSentence;
    }

    // getters are used to access the private variables of the class
    public String getWinner() {
        if (userSentence < compSentence) {
            return "Player";
        } else if (compSentence < userSentence) {
            return "Computer";
        } else {
            return "None";
        }

    }

    public int getUserSentence() {
        return userSentence;
    }

    public int getCompSentence() {
        return compSentence;
    }
    // setters are used to set the private variables of the class

    public void setCompStrategy(String compStrategy) {
        this.compStrategy = compStrategy;
    }

    public String getCompStrategy() {
        return compStrategy;
    }
}  // end of class
