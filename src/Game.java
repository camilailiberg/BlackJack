
public interface Game {
	
	//This method will return the goal of the game. For example, the goal of BlackJack is //to get 21.
		String goal();

	//This method will set up the game.  For example, if the game is poker, then the //player and the dealer will each be dealt five cards. 
		void setUp();

	//This method will run the game.
		void playGame();

	//This method will be passed the name of the winner and print a message telling //who won the game.
		void gameOver(String winner);
	
}
