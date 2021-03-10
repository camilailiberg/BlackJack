import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackJack implements Game {

	private ArrayList<Card> player_cards;  //an arrayList of cards
	private ArrayList<Card> dealer_cards; //an arrayList of cards
	private Deck deck;  //a deck of cards
	
	public BlackJack() {
		
		player_cards=new ArrayList<Card>();
		dealer_cards=new ArrayList<Card>();
		deck=new Deck();
		playGame();
		
	}
	
	
	//This method will return the goal of the game. For example, the goal of BlackJack is //to get 21.
	public String goal() {
		
		return "There are two goals in this game. One is to get 21 points. The other one is to beat the Dealer by gathering the "
				+ "more points possible, without exceeding 21. If your hand gains more points than the Dealer's does or the Dealer’s "
				+ "hand has more than 21 points, you win. If your hand counts more than 21 points, you go bankrupt and lose your bet.";
		
	}
			

	//This method will set up the game.  For example, if the game is poker, then the //player and the dealer will each be dealt five cards. 
			public void setUp() {
			
				Card[] cardsToDeal= deck.deal(2);
				for(int i=0;i<2;i++)
					player_cards.add(cardsToDeal[i]);
				if(deck.isEmpty()==true) {
					System.out.println("There are no more cards in the deck. End of the game.");
					return;
				}
				cardsToDeal=deck.deal(2);
				for(int i=0;i<2;i++)
					dealer_cards.add(cardsToDeal[i]);
				
		}	
			
			
		//Create showCards(ArrayList<Card> cards) that shows the cards in cards.  
		public void showCards(ArrayList<Card> cards) {
			
			for(Card c:cards)
				System.out.println(c);
	
		}
			
			
		//Create sortCards(ArrayList<Card> cards) that puts the cards in descending order according to value.  
		//USE Collections.sort(cards) to sort your cards.
		public void sortCards(ArrayList<Card> cards) { Collections.sort(cards, Collections.reverseOrder()); }
			
			
		//Create totalCards(ArrayList<Card> cards) that returns the point value of the cards passed to it.  
		//You should count an ace as 11 points as long as the total value of the cards isn’t over 21.  
		public int totalCards(ArrayList<Card> cards) {
			
			int totSum=0;
			int aces=0;
			
			for(int i=0;i<cards.size();i++) {
				
				String rank=cards.get(i).getRank();
				if(rank.equals("Jack")||rank.equals("Queen")||rank.equals("King"))
					totSum=totSum+10;
				else if (rank.equals("Ace")) {
					totSum=totSum+11;
					aces++;
				}//closing else if statement.
				else {
					int value=cards.get(i).getValue();
					totSum=totSum+value;
				}
			}//closing for loop.
			
			while(totSum>21&&aces>0) {
					aces--;
					totSum=totSum-10;
				}
			
			return totSum;
			
		}
			
			
		//Create playerTurn().  This method shows the player his cards. Then, prompts the player to see if he wants another card, and if so 
		//deals the player another card and then shows him his cards again. Continue prompting and dealing cards to the player until his cards add 
		//up to more than 21 or he doesn't want any more cards.   
		public void playerTurn() {
			
			Scanner kb=new Scanner(System.in);
			String responce ="";
			boolean conti=true;
			
			while(conti==true) {
				System.out.println("Your cards are:");
				showCards(player_cards);
				System.out.println("Would you like to have another card? (Ansewr yes or no).");
				responce=kb.nextLine().toLowerCase();
				
				while(!(responce.equals("yes"))&& !(responce.equals("no"))) {
					System.out.println("Please answer only yes or no.\nWould you like to have another card?");
					responce=kb.nextLine();
				}
				
				if(responce.equals("yes")) {
					
					Card[]  c=deck.deal(1);
					player_cards.add(c[0]);
					
				}//closing if statement
				else
					conti=false;
				
				if(totalCards(player_cards)>21) {
					System.out.println("Your cards are:");
					showCards(player_cards);
					conti=false;
				}
				
			}//closing while loop
			
		}//closing playerTurn().
			
			
		//Create dealerTurn() that gives the dealer his cards. It should keep dealing cards to the dealer until he has at least 17 points.  
		//As soon as he has at least 17 points, he cannot have any more cards.
		public void dealerTurn() {
			
			Card[] d=deck.deal(1);
			dealer_cards.add(d[0]);
			
		}//closing dealerTurn().
			
			
		//This method will be passed the name of the winner and print a message telling who won
		public void gameOver(String winner) {
		
			if(winner.equals("player"))
				System.out.println( "Congratulations! You, the player, won!");
			else
			System.out.println( "Sorry, the dealer won" );
			
		}
		
		//METHOD CREATED BY ME
		public void gameOverOver(String winner) {
			gameOver(winner);
			System.out.println("The dealer's cards are:");
			showCards(dealer_cards);
			System.out.println("Do you want to play again?");
		}
		
		
		//Create playGame() that  deals two cards to both the player and the computer.  Since the player is allowed to see the dealer's top card, 
		//display ONLY one of the dealer his cards.  Then give the player his turn (be sure to show the player his cards). After the player has his turn, 
		//determine whether or not he has already lost.  If his cards add up to more than 21, then the game is over and the player has lost.  Otherwise, 
		//give the dealer his turn.  In case of a tie, the dealer wins.  Then print a message that telling who won the game.  At the end of the game show the 
		//dealer his cards.   
		public void playGame() {
			
			
			Scanner kb=new Scanner(System.in);
			boolean keepPlaying=true;

			while(keepPlaying==true) {
			
				System.out.println(goal());
				setUp();
				System.out.println("One of the dealer's card is:");
				System.out.println(dealer_cards.get(0));
				playerTurn();
				if(totalCards(player_cards)>21) {
					gameOverOver("dealer");
				}//closing if.
				else{
					dealerTurn();
					if(totalCards(dealer_cards)==21) {
						gameOverOver("dealer");
					}//closing if.
					else if(totalCards(dealer_cards)>21) {
						gameOverOver("player");
					}//closing else if.
					else if(totalCards(player_cards)>totalCards(dealer_cards)) {
						gameOverOver("player");
					}//closing else if.
					else{
						gameOverOver("dealer");
					}//closing little else.
					
				}//closing big else.
				String responce=kb.nextLine();
				while(!responce.toLowerCase().equals("yes")&&!responce.toLowerCase().equals("no")) {
					System.out.println("Please answer whit a yes or a no.");
					responce=kb.nextLine();
				}
				if(responce.toLowerCase().equals("no"))
					keepPlaying=false;
				else {
					empty(player_cards);
					empty(dealer_cards);
				}//closing else.
				
			}//closing while loop.
						
		}//closing playGame().
			
		//METHOD CREATED BY ME.
		public void empty(ArrayList<Card> cards) {
			for(int i=cards.size()-1;i>=0;i--)
				cards.remove(i);
		}
	}
			
			
		//Ask the user if he wants to play again.  If so, continue playing using the same deck of cards (remember, the deck isn’t full any more) until there 
		//are no more cards left in the deck or the player doesn’t want to play anymore.
		
			
			
		//Add any additional methods that you need to complete the class.  Then create runner class to play Black Jack.  Play your game several times 
		//to make sure that it is fully functional.  You don't need to worry about splits BUT, you do need to take into account that an ace can count as a
		//1 or 11 for the player.  
